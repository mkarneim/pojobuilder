package net.karneim.pojobuilder.codegen;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 3.0
 * @author karneim
 */
public class BuildMethodTM {

    private String returnType;
    private ConstructionTM construction;
    private List<SetterCallTM> setterCalls = new ArrayList<SetterCallTM>();
    private boolean override;

    public BuildMethodTM() {
    }

    public BuildMethodTM(String returnType, ConstructorCallTM construction) {
        this.returnType = returnType;
        this.construction = construction;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getReturnType() {
        return returnType;
    }

    public ConstructionTM getConstruction() {
        return construction;
    }

    public void setConstruction(ConstructionTM construction) {
        this.construction = construction;
    }

    public List<SetterCallTM> getSetterCalls() {
        return setterCalls;
    }

    public void setOverride(boolean override) {
        this.override = override;
    }

    public boolean isOverride() {
        return override;
    }

}
