package net.karneim.pojobuilder.codegen;

/**
 * @since 3.0
 * @author karneim
 */
public class CloneMethodTM {
    private String selfType;

    public CloneMethodTM() {
    }

    public CloneMethodTM(String selfType) {
        this.selfType = selfType;
    }

    public String getSelfType() {
        return selfType;
    }

    public void setSelfType(String selfType) {
        this.selfType = selfType;
    }

}
