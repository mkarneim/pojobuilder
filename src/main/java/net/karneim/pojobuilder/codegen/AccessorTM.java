package net.karneim.pojobuilder.codegen;

/**
 * @since 3.0
 * @author karneim
 */
public abstract class AccessorTM {
    private String name;

    public AccessorTM() {
    }

    public AccessorTM(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract boolean isMethod();
}
