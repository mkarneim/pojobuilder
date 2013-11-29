package net.karneim.pojobuilder.codegen;

/**
 * @since 3.0
 * @author karneim
 */
import java.util.ArrayList;
import java.util.List;

public abstract class ConstructionTM {

    private String methodName;
    private List<ArgumentTM> arguments = new ArrayList<ArgumentTM>();

    public ConstructionTM() {
    }

    public ConstructionTM(String methodName, ArgumentTM... arguments) {
        this.methodName = methodName;
        for (ArgumentTM arg : arguments) {
            this.arguments.add(arg);
        }
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public List<ArgumentTM> getArguments() {
        return arguments;
    }

    public abstract boolean isConstructorCall();

}
