package study.buildermodelproducer;

import java.beans.ConstructorProperties;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.HashMap;
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

import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.model.BuilderM;
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

		TypeElement pojoTypeElement = checkNotNull(input.getPojoTypeElement(), "input.getPojoTypeElement()==null");
		GeneratePojoBuilder annotation = checkAnnotated(pojoTypeElement, GeneratePojoBuilder.class);

		BuilderM builderModel = new BuilderM();
		result.setBuilderModel(builderModel);
		
		builderModel.setProductType(computeProductType(pojoTypeElement));
		builderModel.setSuperType(computeBuilderSuperType(pojoTypeElement));
		builderModel.getProperties().addAll(getPropertyModels(pojoTypeElement));

		if (annotation.withGenerationGap()) {
			ManualBuilderM manualBuilderModel = new ManualBuilderM();
			result.setManualBuilderModel(manualBuilderModel);

			builderModel.setAbstractClass(true);
			TypeM builderType = computeBuilderType(pojoTypeElement, annotation);
			builderModel.setType(builderType);
			manualBuilderModel.setSuperType(builderType);

			TypeM manualBuilderType = computeManualBuilderType(pojoTypeElement, annotation);
			manualBuilderModel.setType(manualBuilderType);
			builderModel.setSelfType(manualBuilderType);
		} else {
			TypeM builderImplType = computeBuilderType(pojoTypeElement, annotation);
			builderModel.setType(builderImplType);
			builderModel.setSelfType(builderImplType);
		}

		return result;
	}

	private TypeM computeBuilderSuperType(TypeElement pojoTypeElement) {
		String annotationClassname = GeneratePojoBuilder.class.getCanonicalName();
		TypeElement superTypeElement = getAnnotationClassAttributeValue(pojoTypeElement, annotationClassname,
				"withBaseclass");
		TypeM superType = TypeM.get(superTypeElement.getQualifiedName().toString());
		return superType;
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

	private TypeM computeProductType(TypeElement pojoTypeElement) {
		String productTypeName = pojoTypeElement.getQualifiedName().toString();
		TypeM productType = TypeM.get(productTypeName);
		return productType;
	}

	//
	// HELPER METHODS: these are candidates for separate components
	private Collection<? extends PropertyM> getPropertyModels(TypeElement pojoTypeElement) {
		Map<String, PropertyM> resultMap = new HashMap<String, PropertyM>();
		addPropertyModelsForConstructor(resultMap, pojoTypeElement);
		addPropertyModelsForSetterMethods(resultMap, pojoTypeElement);
		addPropertyModelsForAccessibleFields(resultMap, pojoTypeElement);
		return resultMap.values();
	}

	private void addPropertyModelsForConstructor(Map<String, PropertyM> resultMap, TypeElement pojoTypeElement) {
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

				String fieldName = computeBuilderFieldname(propertyName, propertyTypeM.getQualifiedName());
				PropertyM propM = new PropertyM(propertyName, fieldName, propertyTypeM);
				propM.setParameterPos(i);
				resultMap.put(fieldName, propM);
			}
		}
	}

	private void addPropertyModelsForSetterMethods(Map<String, PropertyM> resultMap, TypeElement pojoTypeElement) {
		TypeElement currentTypeElement = pojoTypeElement;
		while (!currentTypeElement.getQualifiedName().toString().equals("java.lang.Object")) {
			List<? extends Element> members = env.getElementUtils().getAllMembers(currentTypeElement);
			// loop over all setter methods
			List<ExecutableElement> methods = ElementFilter.methodsIn(members);
			for (ExecutableElement method : methods) {
				if (!isStatic(method) && isSetterMethod(method) && isAccessibleForBuilder(method)) {
					String propertyName = getPropertyName(method);

					DeclaredType declType = (DeclaredType) pojoTypeElement.asType();
					ExecutableType execType = (ExecutableType) env.getTypeUtils().asMemberOf(declType, method);
					TypeMirror propertyType = execType.getParameterTypes().get(0);

					TypeM propertyTypeM = typeMUtils.getTypeM(propertyType);

					String fieldName = computeBuilderFieldname(propertyName, propertyTypeM.getQualifiedName());
					PropertyM propM = resultMap.get(fieldName);
					if (propM == null) {
						propM = new PropertyM(propertyName, fieldName, propertyTypeM);
						propM.setSetter(method.getSimpleName().toString());
						resultMap.put(fieldName, propM);
					}
				}
			}
			currentTypeElement = (TypeElement) env.getTypeUtils().asElement(currentTypeElement.getSuperclass());
		}
	}

	private void addPropertyModelsForAccessibleFields(Map<String, PropertyM> resultMap, TypeElement pojoTypeElement) {
		TypeElement currentTypeElement = pojoTypeElement;
		while (!currentTypeElement.getQualifiedName().toString().equals("java.lang.Object")) {
			List<? extends Element> members = env.getElementUtils().getAllMembers(currentTypeElement);
			// loop over all fields
			List<VariableElement> accessibleFields = ElementFilter.fieldsIn(members);
			for (VariableElement property : accessibleFields) {
				if (!isStatic(property) && isMutable(property)) {
					DeclaredType declType = (DeclaredType) pojoTypeElement.asType();
					TypeMirror propertyType = env.getTypeUtils().asMemberOf(declType, property);
					TypeM propertyTypeM = typeMUtils.getTypeM(propertyType);

					String propertyName = property.getSimpleName().toString();
					String fieldName = computeBuilderFieldname(propertyName, propertyTypeM.getQualifiedName());
					PropertyM propM = resultMap.get(fieldName);
					if (propM == null) {
						propM = new PropertyM(propertyName, fieldName, propertyTypeM);
						resultMap.put(fieldName, propM);
					}
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

	private String getPropertyName(ExecutableElement setterMethod) {
		String name = setterMethod.getSimpleName().toString();
		if (name.startsWith("set")) {
			name = name.substring("set".length());
			name = firstCharToLowerCase(name);
			return name;
		} else {
			throw new IllegalArgumentException(String.format("Not a setter method name: %s!", name));
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

	private static String computeBuilderFieldname(String propertyName, String propertyType) {
		String typeString = propertyType.replaceAll("\\.", "\\$");
		typeString = typeString.replaceAll("\\[\\]", "\\$");
		return propertyName + "$" + typeString;
	}

	private boolean isMutable(VariableElement field) {
		return !field.getModifiers().contains(Modifier.FINAL) && isAccessibleForBuilder(field);
	}

	private boolean isAccessibleForBuilder(Element field) {
		// TODO check package private and protected also
		return field.getModifiers().contains(Modifier.PUBLIC);
	}

	private <T> T checkNotNull(T obj, String message) {
		if (obj == null) {
			throw new IllegalArgumentException(message);
		}
		return obj;
	}

	private <A extends Annotation> A checkAnnotated(TypeElement typeElement, Class<A> annoClass) {
		A result = typeElement.getAnnotation(annoClass);
		if (result == null) {
			throw new IllegalArgumentException(String.format("Missing required annotation %s on class %s!",
					annoClass.getCanonicalName(), typeElement.getQualifiedName()));
		}
		return result;
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

	private TypeElement getAnnotationClassAttributeValue(Element annotatedElement, final String annotationName,
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
							TypeElement result = env.getElementUtils().getTypeElement(valueStr);
							return result;
						}
					}
				}
				return null;
			}
		}
		throw new IllegalArgumentException(String.format("Missing annotation %s on class %s!", annotationName,
				annotatedElement.toString()));
	}

}
