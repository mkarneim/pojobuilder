package net.karneim.pojobuilder.baseclass;

import net.karneim.pojobuilder.model.TypeM;

public class WithBaseClass implements BaseClassStrategy {

    private final TypeM baseClass;

    public WithBaseClass(TypeM baseClass) {
        this.baseClass = baseClass;
    }

    @Override
    public TypeM getBaseClass() {
        return baseClass;
    }

}
