package net.karneim.pojobuilder.codegen;

/**
 * @since 3.0
 * @author karneim
 */
public class FieldTM {
    private String name;
    private String type;
    private boolean mandatory;

    public FieldTM() {
    }

    public FieldTM(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public FieldTM(String name, String type, boolean mandatory) {
        super();
        this.name = name;
        this.type = type;
        this.mandatory = mandatory;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

}
