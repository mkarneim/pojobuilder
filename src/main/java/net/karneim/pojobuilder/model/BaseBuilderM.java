package net.karneim.pojobuilder.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BaseBuilderM extends ClassM {
	private TypeM productType;
	private final List<TypeM> buildExceptions = new ArrayList<TypeM>();

	public BaseBuilderM() {
	}

	public TypeM getProductType() {
		return productType;
	}

	public void setProductType(TypeM productType) {
		this.productType = productType;
	}
	
	public List<TypeM> getBuildExceptions() {
		return buildExceptions;
	}
	
	@Override
	public void addToImportTypes(Set<String> result) {
		super.addToImportTypes(result);
		for (TypeM exception : buildExceptions) {
			exception.addToImportTypes(result);
		}
		productType.addToImportTypes(result);
	}

	@Override
	public String toString() {
		return "BaseBuilderM [productType=" + productType + ", getType()=" + getType() + ", getSuperType()="
				+ getSuperType() + ", isAbstractClass()=" + isAbstractClass() + "]";
	}

}
