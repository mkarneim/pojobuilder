package net.karneim.pojobuilder.model;


public class ManualBuilderM extends ClassM {
	private TypeM productType;

	public TypeM getProductType() {
		return productType;
	}

	public void setProductType(TypeM productType) {
		this.productType = productType;
	}

	@Override
	public String toString() {
		return "ManualBuilderM [productType=" + productType + ", getType()=" + getType() + ", getSuperType()="
				+ getSuperType() + ", isAbstractClass()=" + isAbstractClass() + "]";
	}
	
}
