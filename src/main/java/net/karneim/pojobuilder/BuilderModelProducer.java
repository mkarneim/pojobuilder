package net.karneim.pojobuilder;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;
import javax.tools.Diagnostic.Kind;

import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.FactoryM;
import net.karneim.pojobuilder.model.ManualBuilderM;
import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.model.TypeM;

public class BuilderModelProducer {

	private final ProcessingEnvironment env;
	private final TypeMUtils typeMUtils;

	public BuilderModelProducer(ProcessingEnvironment env, TypeMUtils typeMUtils) {
		super();
		this.env = env;
		this.typeMUtils = typeMUtils;
	}

	public Output produce(Input input) {
		Output result = new Output();

		TypeElement pojoTypeElement = checkNotNull(input.getPojoType(), "input.getPojoType()==null");

		GeneratePojoBuilder annotation = input.getGeneratePojoBuilderAnnotation();
		if (annotation == null) {
			throw new IllegalStateException(String.format("missing annotation GeneratePojoBuilder for input %s", input));
		}

		BuilderM builderModel = new BuilderM();
		result.setBuilder(builderModel);

		builderModel.setProductType(computeProductType(input));
		builderModel.setSuperType(computeBuilderSuperType(input));

		if (annotation.withGenerationGap()) {
			ManualBuilderM manualBuilderModel = new ManualBuilderM();
			result.setManualBuilder(manualBuilderModel);

			builderModel.setAbstractClass(true);
			TypeM builderType = computeBuilderType(pojoTypeElement, annotation);
			builderModel.setType(builderType);
			manualBuilderModel.setSuperType(builderType);
			manualBuilderModel.setProductType(builderModel.getProductType());

			TypeM manualBuilderType = computeManualBuilderType(pojoTypeElement, annotation);
			manualBuilderModel.setType(manualBuilderType);
			builderModel.setSelfType(manualBuilderType);
		} else {
			TypeM builderImplType = computeBuilderType(pojoTypeElement, annotation);
			builderModel.setType(builderImplType);
			builderModel.setSelfType(builderImplType);
		}
		
		builderModel.setIsImplementingCopyMethod(annotation.withCopyMethod());

		computePropertyModels(input, builderModel);

		if (input.hasFactoryMethod()) {
			builderModel.setFactory(computeFactoryModel(input));
		}

		return result;
	}

	private FactoryM computeFactoryModel(Input input) {
		ExecutableElement factoryMethod = input.getFactoryMethod();
		if (!(factoryMethod.getEnclosingElement() instanceof TypeElement)) {
			throw new BuildException(Kind.ERROR, String.format(
					"Unexpected owner of method %s! Expected class but was %s.", factoryMethod,
					factoryMethod.getEnclosingElement()), factoryMethod);
		}
		TypeElement ownerType = (TypeElement) factoryMethod.getEnclosingElement();

		TypeM ownerTypeM = typeMUtils.getTypeM(ownerType);
		FactoryM result = new FactoryM(ownerTypeM, factoryMethod.getSimpleName().toString());
		return result;
	}

	private TypeM computeBuilderSuperType(Input input) {
		if (input.hasFactoryMethod()) {
			return getAnnotationClassAttributeValue(input.getFactoryMethod(), GeneratePojoBuilder.class.getName(),
					"withBaseclass");
		} else {
			return getAnnotationClassAttributeValue(input.getPojoType(), GeneratePojoBuilder.class.getName(),
					"withBaseclass");
		}
	}

	private TypeM getAnnotationClassAttributeValue(Element annotatedElement, final String annotationName,
			final String attributeName) {
		for (AnnotationMirror am : annotatedElement.getAnnotationMirrors()) {
			if (annotationName.equals(am.getAnnotationType().toString())) {
				Map<? extends ExecutableElement, ? extends AnnotationValue> valueMap = env.getElementUtils()
						.getElementValuesWithDefaults(am);
				for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : valueMap.entrySet()) {
					if (attributeName.equals(entry.getKey().getSimpleName().toString())) {
						AnnotationValue value = entry.getValue();
						if (value == null) {
							return null;
						} else {
							String valueStr = String.valueOf(value.getValue());
							return TypeM.get(valueStr);
						}
					}
				}
				return null;
			}
		}
		throw new IllegalArgumentException(String.format("Missing annotation %s on class %s!", annotationName,
				annotatedElement.toString()));
	}

	private TypeM computeBuilderType(TypeElement pojoTypeElement, GeneratePojoBuilder annotation) {
		String builderTypeNamePattern = annotation.withName();
		if (annotation.withGenerationGap()) {
			builderTypeNamePattern = "Abstract" + builderTypeNamePattern;
		}
		String packageNamePattern = annotation.intoPackage();
		TypeM result = deriveTypeM(pojoTypeElement, builderTypeNamePattern, packageNamePattern);
		result.getTypeParameters().addAll(typeMUtils.getTypeParameters(pojoTypeElement));
		return result;
	}

	private TypeM computeManualBuilderType(TypeElement pojoTypeElement, GeneratePojoBuilder annotation) {
		String builderTypeNamePattern = annotation.withName();
		String packageNamePattern = annotation.intoPackage();
		TypeM result = deriveTypeM(pojoTypeElement, builderTypeNamePattern, packageNamePattern);
		result.getTypeParameters().addAll(typeMUtils.getTypeParameters(pojoTypeElement));
		return result;
	}

	private TypeM computeProductType(Input input) {
		return typeMUtils.getTypeM(input.getPojoType());
	}

	//
	// HELPER METHODS: these are candidates for separate components
	private void computePropertyModels(Input input, BuilderM builderModel) {
		TypeElement pojoTypeElement = input.getPojoType();
		
		if (input.hasFactoryMethod()) {
			addPropertyModelsForFactoryMethodParameters(input.getFactoryMethod(), builderModel);
		} else {
			addPropertyModelsForConstructor(pojoTypeElement, builderModel);
		}
		addPropertyModelsForSetterMethods(pojoTypeElement, builderModel);
		addPropertyModelsForAccessibleFields(pojoTypeElement, builderModel);
		addPropertyModelsForGetterMethods(pojoTypeElement, builderModel);
		
	}

	private void addPropertyModelsForConstructor(TypeElement pojoTypeElement, BuilderM builderModel) {
		List<ExecutableElement> constructors = ElementFilter.constructorsIn(env.getElementUtils().getAllMembers(
				pojoTypeElement));
		ExecutableElement constr = findFirstAnnotatedConstructor(constructors, ConstructorProperties.class);
		if (constr != null) {
			ConstructorProperties constrProps = constr.getAnnotation(ConstructorProperties.class);
			String[] propertyNames = constrProps.value();
			List<? extends VariableElement> parameters = constr.getParameters();
			if (propertyNames.length != parameters.size()) {
				throw new BuildException(Diagnostic.Kind.ERROR,
						String.format("Incorrect number of values in annotation %s on constructor %s! "
								+ "Expected %d, but was %d.", ConstructorProperties.class.getCanonicalName(), constr,
								parameters.size(), propertyNames.length), constr);
			}

			// loop over all constructor parameters
			for (int i = 0; i < propertyNames.length; ++i) {
				String propertyName = propertyNames[i];
				TypeMirror propertyType = parameters.get(i).asType();
				TypeM propertyTypeM = typeMUtils.getTypeM(propertyType);

				PropertyM propM = builderModel.getOrCreateProperty(propertyName, propertyTypeM);
				propM.setParameterPos(i);
			}
		} else {
			constr = findDefaultConstructor(constructors);
		}

		if (constr != null) {
			// find all exceptions that can be thrown by this constructor
			List<? extends TypeMirror> throwTypes = constr.getThrownTypes();
			List<TypeM> exceptionTypes = new ArrayList<TypeM>();
			for (TypeMirror throwType : throwTypes) {
				TypeM exeptionType = typeMUtils.getTypeM(throwType);
				exceptionTypes.add(exeptionType);
			}
			builderModel.getBuildExceptions().addAll(exceptionTypes);
		} else {
			throw new BuildException(Diagnostic.Kind.ERROR, String.format(
					"Missing default constructor OR constructor annotated with %s in class %s!",
					ConstructorProperties.class.getCanonicalName(), pojoTypeElement.getQualifiedName()),
					pojoTypeElement);
		}
	}

	private void addPropertyModelsForFactoryMethodParameters(ExecutableElement factoryMethod, BuilderM builderModel) {
		if (factoryMethod.getParameters().isEmpty()) {
			return;
		}
		PropertyNames propertyNamesAnno = factoryMethod.getAnnotation(PropertyNames.class);
		if (propertyNamesAnno == null) {
			throw new BuildException(Diagnostic.Kind.ERROR, String.format(
					"Missing annotation %s on factory method %s of class %s!", PropertyNames.class.getSimpleName(),
					factoryMethod.toString(), factoryMethod.getEnclosingElement().getSimpleName()), factoryMethod);
		}
		String[] propertyNames = propertyNamesAnno.value();
		if (propertyNames.length != factoryMethod.getParameters().size()) {
			throw new BuildException(Diagnostic.Kind.ERROR, String.format(
					"Incorrect number of values in annotation %s on method %s! " + "Expected %d, but was %d.",
					PropertyNames.class.getSimpleName(), factoryMethod, factoryMethod.getParameters().size(),
					propertyNames.length), factoryMethod);
		}
		// loop over all method parameters
		for (int i = 0; i < propertyNames.length; ++i) {
			String propertyName = propertyNames[i];
			TypeMirror propertyType = factoryMethod.getParameters().get(i).asType();
			TypeM propertyTypeM = typeMUtils.getTypeM(propertyType);

			PropertyM propM = builderModel.getOrCreateProperty(propertyName, propertyTypeM);
			propM.setParameterPos(i);
		}
	}

	private void addPropertyModelsForSetterMethods(TypeElement pojoTypeElement, BuilderM builderModel) {
		TypeElement currentTypeElement = pojoTypeElement;
		while (!currentTypeElement.getQualifiedName().toString().equals(Object.class.getName())) {
			List<? extends Element> members = env.getElementUtils().getAllMembers(currentTypeElement);
			// loop over all setter methods
			List<ExecutableElement> methods = ElementFilter.methodsIn(members);
			for (ExecutableElement method : methods) {
				if (!isStatic(method) && isSetterMethod(method)
						&& isAccessibleForBuilder(method, builderModel.getType())) {
					String propertyName = getPropertyName(method);

					DeclaredType declType = (DeclaredType) pojoTypeElement.asType();
					ExecutableType execType = (ExecutableType) env.getTypeUtils().asMemberOf(declType, method);
					TypeMirror propertyType = execType.getParameterTypes().get(0);

					TypeM propertyTypeM = typeMUtils.getTypeM(propertyType);

					PropertyM propM = builderModel.getOrCreateProperty(propertyName, propertyTypeM);
					propM.setSetter(method.getSimpleName().toString());
					propM.setAccessible(true);

				}
			}
			currentTypeElement = (TypeElement) env.getTypeUtils().asElement(currentTypeElement.getSuperclass());
		}
	}
	
	private void addPropertyModelsForGetterMethods(TypeElement pojoTypeElement, BuilderM builderModel) {
        TypeElement currentTypeElement = pojoTypeElement;
        while (!currentTypeElement.getQualifiedName().toString().equals(Object.class.getName())) {
            List<? extends Element> members = env.getElementUtils().getAllMembers(currentTypeElement);
            // loop over all setter methods
            List<ExecutableElement> methods = ElementFilter.methodsIn(members);
            for (ExecutableElement method : methods) {
                if (!isStatic(method) && isGetterMethod(method) && isAccessibleForBuilder(method, builderModel.getType())) {
                    String propertyName = getPropertyName(method);

                    DeclaredType declType = (DeclaredType) pojoTypeElement.asType();
                    ExecutableType execType = (ExecutableType) env.getTypeUtils().asMemberOf(declType, method);
                    TypeMirror propertyType = execType.getReturnType();

                    TypeM propertyTypeM = typeMUtils.getTypeM(propertyType);

                    PropertyM propM = builderModel.getProperty(propertyName, propertyTypeM);//resultMap.get(fieldName);
                    if (propM != null) {
                        propM.setGetter(method.getSimpleName().toString());
                    }
                }
            }
            currentTypeElement = (TypeElement) env.getTypeUtils().asElement(currentTypeElement.getSuperclass());
        }
    }

	private void addPropertyModelsForAccessibleFields(TypeElement pojoTypeElement, BuilderM builderModel) {
		TypeElement currentTypeElement = pojoTypeElement;
		while (!currentTypeElement.getQualifiedName().toString().equals(Object.class.getName())) {
			List<? extends Element> members = env.getElementUtils().getAllMembers(currentTypeElement);
			// loop over all fields
			List<VariableElement> accessibleFields = ElementFilter.fieldsIn(members);
			for (VariableElement property : accessibleFields) {
				if (!isStatic(property) && isMutable(property)
						&& isAccessibleForBuilder(property, builderModel.getType())) {
					DeclaredType declType = (DeclaredType) pojoTypeElement.asType();
					TypeMirror propertyType = env.getTypeUtils().asMemberOf(declType, property);
					TypeM propertyTypeM = typeMUtils.getTypeM(propertyType);

					String propertyName = property.getSimpleName().toString();
					PropertyM propM = builderModel.getOrCreateProperty(propertyName, propertyTypeM);

					propM.setAccessible(true);
				}
			}
			currentTypeElement = (TypeElement) env.getTypeUtils().asElement(currentTypeElement.getSuperclass());
		}
	}

	private ExecutableElement findFirstAnnotatedConstructor(List<ExecutableElement> constructors,
			Class<ConstructorProperties> annoType) {
		for (ExecutableElement constr : constructors) {
			if (constr.getAnnotation(annoType) != null) {
				return constr;
			}
		}
		return null;
	}

	private ExecutableElement findDefaultConstructor(List<ExecutableElement> constructors) {
		for (ExecutableElement constr : constructors) {
			if (constr.getParameters().size() == 0) {
				return constr;
			}
		}
		return null;
	}

	private String getPropertyName(ExecutableElement setterMethod) {
		String name = setterMethod.getSimpleName().toString();
		int prefixLength = -1;
		if (name.startsWith("set")) {
		    prefixLength = "set".length();
		}
		else if(name.startsWith("get")) {
		    prefixLength = "get".length();
		}
		else if(name.startsWith("is")) {
            prefixLength = "is".length();
        }
		
		if(prefixLength > 0) {
			name = name.substring(prefixLength);
			name = firstCharToLowerCase(name);
			return name;
		} else {
			throw new IllegalArgumentException(String.format("Not a setter or getter method name: %s!", name));
		}
	}

	private String firstCharToLowerCase(String text) {
		char[] vals = text.toCharArray();
		vals[0] = Character.toLowerCase(vals[0]);
		return String.valueOf(vals);
	}

	private boolean isStatic(Element elem) {
		return elem.getModifiers().contains(Modifier.STATIC);
	}

	private boolean isSetterMethod(ExecutableElement elem) {
		String methodName = elem.getSimpleName().toString();
		TypeMirror retType = elem.getReturnType();
		return methodName.startsWith("set") && methodName.length() > "set".length()
				&& retType.getKind() == TypeKind.VOID && elem.getParameters().size() == 1;
	}
	
	private boolean isGetterMethod(ExecutableElement elem) {
        String methodName = elem.getSimpleName().toString();
        TypeMirror retType = elem.getReturnType();
        return (( methodName.startsWith("get") && methodName.length() > "set".length() ) ||
                ( methodName.startsWith("is") && methodName.length() > "is".length() ) )
                && retType.getKind() != TypeKind.VOID && elem.getParameters().size() == 0;
    }

	private static String computeBuilderFieldname(String propertyName, String propertyType) {
		String typeString = propertyType.replaceAll("\\.", "\\$");
		typeString = typeString.replaceAll("\\[\\]", "\\$");
		return propertyName + "$" + typeString;
	}

	private boolean isMutable(VariableElement field) {
		return !field.getModifiers().contains(Modifier.FINAL);
	}

	private boolean isAccessibleForBuilder(Element field, TypeM builderType) {
		if (field.getModifiers().contains(Modifier.PUBLIC)) {
			return true;
		}
		if (field.getModifiers().contains(Modifier.PRIVATE)) {
			return false;
		}
		PackageElement fieldPackage = env.getElementUtils().getPackageOf(field);
		String builderPackge = builderType.getPackage();
		if (fieldPackage.isUnnamed()) {
			return builderPackge == null;
		} else {
			return fieldPackage.getQualifiedName().toString().equals(builderPackge);
		}
	}

	private <T> T checkNotNull(T obj, String message) {
		if (obj == null) {
			throw new IllegalArgumentException(message);
		}
		return obj;
	}

	private TypeM deriveTypeM(TypeElement originalTypeElement, String derivedTypeNamePattern,
			String derivedPackageNamePattern) {
		String derivedTypeName = derivedTypeNamePattern.replace("*", originalTypeElement.getSimpleName());
		PackageElement packageElement = env.getElementUtils().getPackageOf(originalTypeElement);
		if (!packageElement.isUnnamed()) {
			String derivedPackage = derivedPackageNamePattern.replace("*", packageElement.getQualifiedName());
			if (!derivedPackage.isEmpty()) {
				derivedTypeName = derivedPackage + "." + derivedTypeName;
			}
		}
		TypeM result = TypeM.get(derivedTypeName);
		return result;
	}

}
