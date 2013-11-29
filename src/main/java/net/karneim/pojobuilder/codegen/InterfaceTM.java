package net.karneim.pojobuilder.codegen;

/**
 * @since 3.0
 * @author karneim
 */
public class InterfaceTM {
    private String text;

    public InterfaceTM() {
    }

    public InterfaceTM(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
