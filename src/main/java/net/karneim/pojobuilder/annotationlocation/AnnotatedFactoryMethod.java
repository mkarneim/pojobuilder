package net.karneim.pojobuilder.annotationlocation;

import net.karneim.pojobuilder.BuildException;
import net.karneim.pojobuilder.GeneratePojoBuilder;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;

public class AnnotatedFactoryMethod implements AnnotationStrategy {

    private ProcessingEnvironment env;
    private final ExecutableElement methodElement;

    public AnnotatedFactoryMethod(ProcessingEnvironment env, ExecutableElement methodElement) {
        this.env = env;
        this.methodElement = methodElement;

        // Static validation
        TypeMirror returnType = methodElement.getReturnType();
        if ( returnType.getKind()==TypeKind.VOID ) {
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


}
