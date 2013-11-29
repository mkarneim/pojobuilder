package net.karneim.pojobuilder.codegen;

/**
 * @since 3.0
 * @author karneim
 */
public class FieldAccessorTM extends AccessorTM {

    public FieldAccessorTM() {
    }

    public FieldAccessorTM(String fieldname) {
        super(fieldname);
    }

    @Override
    public boolean isMethod() {
        return false;
    }

}
