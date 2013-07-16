package net.karneim.pojobuilder.baseclass;

import net.karneim.pojobuilder.model.TypeM;

public class WithoutBaseClass implements BaseClassStrategy {

    @Override
    public TypeM getBaseClass() {
        return null;
    }

}
