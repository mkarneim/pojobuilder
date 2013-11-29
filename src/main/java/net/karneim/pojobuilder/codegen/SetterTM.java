package net.karneim.pojobuilder.codegen;

/**
 * @since 3.0
 * @author karneim
 */
public class SetterTM {
    private String name;
    private String fieldname;
    private String type;
    private String returnType;

    public SetterTM() {
    }

    public SetterTM(String name, String fieldname, String type, String returnType) {
        this.name = name;
        this.fieldname = fieldname;
        this.type = type;
        this.returnType = returnType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

}
