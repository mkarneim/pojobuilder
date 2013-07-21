package net.karneim.pojobuilder.annotationlocation;

import net.karneim.pojobuilder.GeneratePojoBuilder;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

public class AnnotatedClass implements AnnotationStrategy {

    private final TypeElement classElement;

    public AnnotatedClass(TypeElement classElement) {
        this.classElement = classElement;
    }

    @Override
    public TypeElement getPojoType() {
        return classElement;
    }

    @Override
    public GeneratePojoBuilder getAnnotation() {
        return classElement.getAnnotation(GeneratePojoBuilder.class);
    }

    @Override
    public ExecutableElement getFactoryMethod() {
        return null;
    }

}
