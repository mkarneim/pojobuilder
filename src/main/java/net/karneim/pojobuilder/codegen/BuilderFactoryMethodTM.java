package net.karneim.pojobuilder.codegen;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 3.0
 * @author karneim
 */
public class BuilderFactoryMethodTM {

	private String name;
	private String returnType;
	private List<String> generics = new ArrayList<String>();

	public BuilderFactoryMethodTM() {
	}

	public BuilderFactoryMethodTM(String name, String returnType, String... generics) {
		this.name = name;
		this.returnType = returnType;
		for (String element : generics) {
			this.generics.add(element);
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getReturnType() {
		return returnType;
	}

	public List<String> getGenerics() {
		return generics;
	}

}
