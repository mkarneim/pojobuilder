package net.karneim.pojobuilder;

import net.karneim.pojobuilder.annotationlocation.AnnotationStrategy;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

public class Input {

	private AnnotationStrategy annotationStrategy;

	public Input(AnnotationStrategy annotationStrategy) {
		this.annotationStrategy = annotationStrategy;
	}

	public TypeElement getPojoType() {
		return annotationStrategy.getPojoType();
	}

	public ExecutableElement getFactoryMethod() {
		return annotationStrategy.getFactoryMethod();
	}

	public boolean hasFactoryMethod() {
		return annotationStrategy.getFactoryMethod()!=null;
	}

	public GeneratePojoBuilder getGeneratePojoBuilderAnnotation() {
		return annotationStrategy.getAnnotation();
	}

	@Override
	public String toString() {
		return "Input";
	}

}
