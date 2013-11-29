package net.karneim.pojobuilder.codegen;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 3.0
 * @author karneim
 */
public class CopyMethodTM {

    private String returnType;
    private String pojoType;
    private List<AssignmentTM> assignments = new ArrayList<AssignmentTM>();

    public CopyMethodTM() {
        super();
    }

    public CopyMethodTM(String returnType, String pojoType) {
        this.returnType = returnType;
        this.pojoType = pojoType;
    }

    public void setPojoType(String pojoType) {
        this.pojoType = pojoType;
    }

    public String getPojoType() {
        return pojoType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getReturnType() {
        return returnType;
    }

    public List<AssignmentTM> getAssignments() {
        return assignments;
    }

}
