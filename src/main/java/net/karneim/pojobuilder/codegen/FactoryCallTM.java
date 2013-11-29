package net.karneim.pojobuilder.codegen;

/**
 * @since 3.0
 * @author karneim
 */
public class FactoryCallTM extends ConstructionTM {

    @Override
    public boolean isConstructorCall() {
        return false;
    }

}
