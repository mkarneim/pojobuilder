package net.karneim.pojobuilder.model;

import java.util.Set;

public class FactoryM {
    private final TypeM ownerType;
    private final String methodName;

    public FactoryM(TypeM ownerType, String methodName) {
        super();
        this.ownerType = ownerType;
        this.methodName = methodName;
    }

    public TypeM getOwnerType() {
        return ownerType;
    }

    public String getMethodName() {
        return methodName;
    }

	public void addToImportTypes(Set<String> result) {
		ownerType.exportImportTypes(result);
	}

}
