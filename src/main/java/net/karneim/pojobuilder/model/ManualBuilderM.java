package net.karneim.pojobuilder.model;

public class ManualBuilderM extends BaseBuilderM {

	@Override
	public String toString() {
		return "ManualBuilderM[pojoType=" + getPojoType() + ",type=" + getType()
				+ ",superType=" + getSuperType() + ",abstract=" + isAbstract() + "]";
	}

}
