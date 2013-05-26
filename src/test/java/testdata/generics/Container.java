package testdata.generics;

import net.karneim.pojobuilder.GeneratePojoBuilder;

import java.io.Serializable;

@GeneratePojoBuilder
public class Container<T extends Item & Serializable> {

	private T element;

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

}
