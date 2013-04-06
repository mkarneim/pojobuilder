package net.karneim.pojobuilder;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

public class Input {
	private TypeElement pojoType;
	private ExecutableElement factoryMethod;

	public Input() {
	}

	public Input(TypeElement pojoType) {
		super();
		this.pojoType = pojoType;
	}

	public Input(TypeElement pojoType, ExecutableElement factoryMetod) {
		this.pojoType = pojoType;
		this.factoryMethod = factoryMetod;
	}

	public TypeElement getPojoType() {
		return pojoType;
	}

	public ExecutableElement getFactoryMethod() {
		return factoryMethod;
	}

	public boolean hasFactoryMethod() {
		return factoryMethod != null;
	}

	public GeneratePojoBuilder getGeneratePojoBuilderAnnotation() {
		if (hasFactoryMethod()) {
			return factoryMethod.getAnnotation(GeneratePojoBuilder.class);
		} else {
			return pojoType.getAnnotation(GeneratePojoBuilder.class);
		}
	}

	@Override
	public String toString() {
		return "Input [pojoType=" + pojoType + ", factoryMethod=" + factoryMethod + "]";
	}

}
