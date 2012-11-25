package study.buildermodelproducer;

import javax.lang.model.element.TypeElement;

public class Input {
	private TypeElement pojoTypeElement;
	
	public Input() {
	}

	public Input(TypeElement pojoTypeElement) {
		super();
		this.pojoTypeElement = pojoTypeElement;
	}

	public TypeElement getPojoTypeElement() {
		return pojoTypeElement;
	}

	public void setPojoTypeElement(TypeElement pojoTypeElement) {
		this.pojoTypeElement = pojoTypeElement;
	}
	

}
