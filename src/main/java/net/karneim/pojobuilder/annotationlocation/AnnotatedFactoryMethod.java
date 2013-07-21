package net.karneim.pojobuilder.annotationlocation;

import net.karneim.pojobuilder.*;
import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.FactoryM;
import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.model.TypeM;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;

public class AnnotatedFactoryMethod implements AnnotationStrategy {

    private ProcessingEnvironment env;
    private final ExecutableElement methodElement;
    private TypeMUtils typeMUtils;

    public AnnotatedFactoryMethod(ProcessingEnvironment env, ExecutableElement methodElement, TypeMUtils typeMUtils) {
        this.env = env;
        this.methodElement = methodElement;
        this.typeMUtils = typeMUtils;

        // Static validation
        TypeMirror returnType = methodElement.getReturnType();
        if (returnType.getKind() == TypeKind.VOID) {
            throw new BuildException(Diagnostic.Kind.ERROR, "void return type cannot be constructed", methodElement);
        }
        // TODO check for primitives?

    }

    /**
     * Pojo is defined as the return type of the factory method
     */
    @Override
    public TypeElement getPojoType() {
        return (TypeElement) env.getTypeUtils().asElement(methodElement.getReturnType());
    }

    @Override
    public GeneratePojoBuilder getAnnotation() {
        return methodElement.getAnnotation(GeneratePojoBuilder.class);
    }

    @Override
    public ExecutableElement getFactoryMethod() {
        return methodElement;
    }

    @Override
    public void addPropertyModelsForAnnotatedElement(BuilderM model) {
        addPropertyModelsForFactoryMethodParameters(model);

        // TODO This action is not meant to happen from this method call
        // It was put here as a quick way of removing knowledge of the factory-method from the producer
        model.setFactory(computeFactoryModel());
    }

    private FactoryM computeFactoryModel() {
        TypeElement ownerType = (TypeElement) methodElement.getEnclosingElement();
        TypeM ownerTypeM = typeMUtils.getTypeM(ownerType);
        FactoryM result = new FactoryM(ownerTypeM, methodElement.getSimpleName().toString());
        return result;
    }

    @SuppressWarnings("deprecation")
    private void addPropertyModelsForFactoryMethodParameters(BuilderM builderModel) {
        if (methodElement.getParameters().isEmpty()) {
            return;
        }

        // This method can be simplified when we only have one annotation to handle in future
        PropertyNames propertyNamesAnno = methodElement.getAnnotation(PropertyNames.class);
        FactoryProperties factoryPropertiesAnno = methodElement.getAnnotation(FactoryProperties.class);
        if (propertyNamesAnno == null && factoryPropertiesAnno == null) {
            // ... add some kind of NamingStrategy and extract commonality
            addPropertyModelsForImplicitMethodParameters(builderModel);
            return;
        }

        if (propertyNamesAnno != null && factoryPropertiesAnno != null) {
            throw new BuildException(
                    Diagnostic.Kind.ERROR,
                    String.format(
                            "Cannot specify both %s and %s on factory method %s of class %s!",
                            FactoryProperties.class.getSimpleName(),
                            PropertyNames.class.getSimpleName(),
                            methodElement.toString(),
                            methodElement.getEnclosingElement().getSimpleName()),
                    methodElement);
        }

        String[] propertyNames;
        String annotationName;
        if (factoryPropertiesAnno != null) {
            propertyNames = factoryPropertiesAnno.value();
            annotationName = FactoryProperties.class.getSimpleName();
        } else {
            propertyNames = propertyNamesAnno.value();
            annotationName = PropertyNames.class.getSimpleName();
        }

        if (propertyNames.length != methodElement.getParameters().size()) {
            throw new BuildException(
                    Diagnostic.Kind.ERROR,
                    String.format(
                            "Incorrect number of values in annotation %s on method %s! Expected %d, but was %d.",
                            annotationName,
                            methodElement,
                            methodElement.getParameters().size(),
                            propertyNames.length),
                    methodElement);
        }

        // loop over all method parameters
        for (int i = 0; i < propertyNames.length; ++i) {
            String propertyName = propertyNames[i];
            TypeMirror propertyType = methodElement.getParameters().get(i).asType();
            TypeM propertyTypeM = typeMUtils.getTypeM(propertyType);

            PropertyM propM = builderModel.getOrCreateProperty(propertyName, propertyTypeM);
            propM.setParameterPos(i);
        }
    }

    private void addPropertyModelsForImplicitMethodParameters(BuilderM builderModel) {
        // loop over all method parameters
        int i = 0;
        for (VariableElement param : methodElement.getParameters()) {
            String propertyName = param.getSimpleName().toString();
            TypeMirror propertyType = param.asType();
            TypeM propertyTypeM = typeMUtils.getTypeM(propertyType);
            PropertyM propM = builderModel.getOrCreateProperty(propertyName, propertyTypeM);
            propM.setParameterPos(i++);
        }
    }

}
