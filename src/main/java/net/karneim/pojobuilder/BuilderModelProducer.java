package net.karneim.pojobuilder;

import static javax.lang.model.element.ElementKind.CLASS;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;
import static javax.lang.model.type.TypeKind.VOID;
import static javax.tools.Diagnostic.Kind.ERROR;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import net.karneim.pojobuilder.annotationlocation.AnnotationStrategy;
import net.karneim.pojobuilder.baseclass.BaseClassStrategy;
import net.karneim.pojobuilder.model.*;
import net.karneim.pojobuilder.name.NameStrategy;
import net.karneim.pojobuilder.packages.PackageStrategy;

import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;

public class BuilderModelProducer {

    private static final String IS = "is";
    private static final String GET = "get";
    private static final String SET = "set";
    private final ProcessingEnvironment env;
    private final TypeMUtils typeMUtils;
    private final AnnotationStrategy annotationStrategy;
    private final NameStrategy nameStrategy;
    private final PackageStrategy packageStrategy;
    private final BaseClassStrategy baseClassStrategy;

    public BuilderModelProducer(
            ProcessingEnvironment env,
            TypeMUtils typeMUtils,
            AnnotationStrategy annotationStrategy, NameStrategy nameStrategy,
            PackageStrategy packageStrategy,
            BaseClassStrategy baseClassStrategy) {
        super();
        this.env = env;
        this.typeMUtils = typeMUtils;
        this.annotationStrategy = annotationStrategy;
        this.nameStrategy = nameStrategy;
        this.packageStrategy = packageStrategy;
        this.baseClassStrategy = baseClassStrategy;
    }

    public Output produce() {
        Output result = new Output();

        TypeElement pojoTypeElement = checkNotNull(annotationStrategy.getPojoType(), "input.getAnnotationStrategy()==null");

        GeneratePojoBuilder annotation = annotationStrategy.getAnnotation();

        BuilderM builderModel = new BuilderM();
        result.setBuilder(builderModel);

        builderModel.setProductType(typeMUtils.getTypeM(annotationStrategy.getPojoType()));
        builderModel.setSuperType(baseClassStrategy.getBaseClass());

        if (annotation.withGenerationGap()) {
            ManualBuilderM manualBuilderModel = new ManualBuilderM();
            result.setManualBuilder(manualBuilderModel);

            builderModel.setAbstractClass(true);
            TypeM builderType = computeBuilderType(pojoClassEl, annotation);
            builderModel.setType(builderType);
            manualBuilderModel.setSuperType(builderType);
            manualBuilderModel.setProductType(builderModel.getProductType());

            TypeM manualBuilderType = computeManualBuilderType(pojoClassEl, annotation);
            manualBuilderModel.setType(manualBuilderType);
            builderModel.setSelfType(manualBuilderType);
        } else {
            TypeM builderImplType = computeBuilderType(pojoClassEl, annotation);
            builderModel.setType(builderImplType);
            builderModel.setSelfType(builderImplType);
        }

        builderModel.setIsImplementingCopyMethod(annotation.withCopyMethod());

        computePropertyModels(builderModel);

        return result;
    }

    private TypeM computeBuilderType(TypeElement pojoTypeElement, GeneratePojoBuilder annotation) {
        String typeName = nameStrategy.getName(annotation, pojoTypeElement);
        // FIXME Strategy
        if (annotation.withGenerationGap()) {
            typeName = "Abstract" + typeName;
        }
        String packageName = packageStrategy.getPackage(annotation, pojoTypeEl);
        TypeM result = deriveTypeM(packageName, typeName);
        result.getTypeParameters().addAll(typeMUtils.getTypeParameters(pojoClassEl));
        return result;
    }

    private TypeM computeManualBuilderType(TypeElement pojoClassEl, GeneratePojoBuilder annotation) {
        String typeName = nameStrategy.getName(annotation, pojoTypeEl);
        String packageName = packageStrategy.getPackage(annotation, pojoTypeEl);
        TypeM result = deriveTypeM(packageName, typeName);
        result.getTypeParameters().addAll(typeMUtils.getTypeParameters(pojoClassEl));
        return result;
    }

    //
    // HELPER METHODS: these are candidates for separate components
    private void computePropertyModels(BuilderM builderModel) {
        TypeElement pojoTypeElement = annotationStrategy.getPojoType();
        annotationStrategy.addPropertyModelsForAnnotatedElement(builderModel);

        addPropertyModelsForSetterMethods(pojoTypeElement, builderModel);
        addPropertyModelsForAccessibleFields(pojoTypeElement, builderModel);
        addPropertyModelsForGetterMethods(pojoTypeElement, builderModel);

    }

    private void addPropertyModelsForSetterMethods(TypeElement pojoClassEl, BuilderM builderModel) {
        DeclaredType declType = (DeclaredType) pojoClassEl.asType();
        List<? extends Element> memberEls = env.getElementUtils().getAllMembers(pojoClassEl);
            // loop over all setter methods
        List<ExecutableElement> methodEls = ElementFilter.methodsIn(memberEls);
        for (ExecutableElement methodEl : methodEls) {
            if (!isStatic(methodEl) && isSetterMethod(methodEl) && !isDeclaredInObject(methodEl)
                    && isAccessibleForBuilder(methodEl, builderModel.getType())) {
                String propertyName = getPropertyName(methodEl);

                ExecutableType execType = (ExecutableType) env.getTypeUtils().asMemberOf(declType, methodEl);
                    TypeMirror propertyType = execType.getParameterTypes().get(0);

                    TypeM propertyTypeM = typeMUtils.getTypeM(propertyType);

                    PropertyM propM = builderModel.getOrCreateProperty(propertyName, propertyTypeM);
                propM.setSetter(methodEl.getSimpleName().toString());
                    propM.setAccessible(true);

                }
        }
    }

    private void addPropertyModelsForGetterMethods(TypeElement pojoClassEl, BuilderM builderModel) {
        DeclaredType pojoClassType = (DeclaredType) pojoClassEl.asType();
        List<? extends Element> memberEls = env.getElementUtils().getAllMembers(pojoClassEl);
            // loop over all setter methods
        List<ExecutableElement> methodsEls = ElementFilter.methodsIn(memberEls);
        for (ExecutableElement methodEl : methodsEls) {
            if (!isStatic(methodEl) && isGetterMethod(methodEl) && !isDeclaredInObject(methodEl)
                    && isAccessibleForBuilder(methodEl, builderModel.getType())) {
                String propertyName = getPropertyName(methodEl);
                    ExecutableType execType;
                    try {
                    execType = (ExecutableType) env.getTypeUtils().asMemberOf(pojoClassType, methodEl);
                    } catch (IllegalArgumentException e) {
                    String errorMessage = String.format("%s.%nElement=%s, pojoClassType=%s, pojoClassElement=%s",
                            e.getMessage(), methodEl, pojoClassType, pojoClassEl);
                    throw new BuildException(ERROR, errorMessage, pojoClassEl);
                    }
                    TypeMirror propertyType = execType.getReturnType();

                    TypeM propertyTypeM = typeMUtils.getTypeM(propertyType);

                    PropertyM propM = builderModel.getProperty(propertyName, propertyTypeM);// resultMap.get(fieldName);
                    if (propM != null) {
                    propM.setGetter(methodEl.getSimpleName().toString());
                    }
                }
            }

    }

    private boolean isDeclaredInObject(Element el) {
        Element ownerEl = el.getEnclosingElement();
        if (ownerEl.getKind() == CLASS) {
            TypeElement typeEl = (TypeElement) ownerEl;
            return typeEl.getQualifiedName().toString().equals(Object.class.getName());
        }
        return false;
    }

    private void addPropertyModelsForAccessibleFields(TypeElement pojoClassEl, BuilderM builderModel) {
        List<? extends Element> memberEls = env.getElementUtils().getAllMembers(pojoClassEl);
        // loop over all fields
        List<VariableElement> fieldEls = ElementFilter.fieldsIn(memberEls);
        for (VariableElement fieldEl : fieldEls) {
            if (!isStatic(fieldEl) && !isDeclaredInObject(fieldEl)
                    && isAccessibleForBuilder(fieldEl, builderModel.getType())) {
                DeclaredType declType = (DeclaredType) pojoClassEl.asType();
                TypeMirror propertyType = env.getTypeUtils().asMemberOf(declType, fieldEl);
                TypeM propertyTypeM = typeMUtils.getTypeM(propertyType);

                String propertyName = fieldEl.getSimpleName().toString();
                PropertyM propM = builderModel.getOrCreateProperty(propertyName, propertyTypeM);
                propM.setReadable(true);
                propM.setAccessible(true);
                if (isMutable(fieldEl)) {
                    propM.setWritable(true);
                }
            }
        }
    }

    private String getPropertyName(ExecutableElement methodEl) {
        String name = methodEl.getSimpleName().toString();
        int prefixLength = -1;
        if (name.startsWith(SET)) {
            prefixLength = SET.length();
        } else if (name.startsWith(GET)) {
            prefixLength = GET.length();
        } else if (name.startsWith(IS)) {
            prefixLength = IS.length();
        }

        if (prefixLength > 0) {
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

    private boolean isStatic(Element el) {
        return el.getModifiers().contains(STATIC);
    }

    private boolean isSetterMethod(ExecutableElement el) {
        String methodName = el.getSimpleName().toString();
        TypeMirror retType = el.getReturnType();
        return methodName.startsWith(SET) && methodName.length() > SET.length() && retType.getKind() == VOID
                && el.getParameters().size() == 1;
    }

    private boolean isGetterMethod(ExecutableElement el) {
        String methodName = el.getSimpleName().toString();
        TypeMirror retType = el.getReturnType();
        return ((methodName.startsWith(GET) && methodName.length() > GET.length()) || (methodName.startsWith(IS) && methodName
                .length() > IS.length())) && retType.getKind() != VOID && el.getParameters().size() == 0;
    }

    private boolean isMutable(VariableElement fieldEl) {
        return !fieldEl.getModifiers().contains(FINAL);
    }

    private boolean isAccessibleForBuilder(Element el, TypeM builderType) {
        if (el.getModifiers().contains(PUBLIC)) {
            return true;
        }
        if (el.getModifiers().contains(PRIVATE)) {
            return false;
        }
        PackageElement fieldPackage = env.getElementUtils().getPackageOf(el);
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

    private TypeM deriveTypeM(String packageName, String typeName) {
        String qualifiedName = "".equals(packageName) ? typeName : (packageName + '.' + typeName);
        return TypeM.get(qualifiedName);
    }

}
