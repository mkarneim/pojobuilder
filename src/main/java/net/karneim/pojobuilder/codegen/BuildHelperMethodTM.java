package net.karneim.pojobuilder.codegen;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 3.0
 * @author karneim
 */
public class BuildHelperMethodTM {

    private String name;
    private String returnType;
    private String parameterType;
    private List<String> generics = new ArrayList<String>();

    public BuildHelperMethodTM() {
    }

    public BuildHelperMethodTM(String name, String returnType, String parameterType, String... generics) {
        super();
        this.name = name;
        this.returnType = returnType;
        this.parameterType = parameterType;
        for (String element : generics) {
            this.generics.add(element);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getParameterType() {
        return parameterType;
    }

    public List<String> getGenerics() {
        return generics;
    }

}
