package net.karneim.pojobuilder.codegen;

/**
 * @since 3.0
 * @author karneim
 */
public class MethodAccessorTM extends AccessorTM {

    public MethodAccessorTM() {
        super();
    }

    public MethodAccessorTM(String name) {
        super(name);
    }

    @Override
    public boolean isMethod() {
        return true;
    }

}
