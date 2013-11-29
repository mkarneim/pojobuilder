package study;

import javax.lang.model.element.*;
import javax.lang.model.util.ElementScanner6;
import java.util.ArrayList;
import java.util.List;

public class CountingElementVisitor extends ElementScanner6<Void, Void> {
    private int packageCount = 0;
    private int typeCount = 0;
    private int variableCount = 0;
    private int executableCount = 0;
    private int typeParameterCount = 0;
    private List<Element> visited = new ArrayList<Element>();

    public int getPackageCount() {
        return packageCount;
    }

    public int getTypeCount() {
        return typeCount;
    }

    public int getVariableCount() {
        return variableCount;
    }

    public int getExecutableCount() {
        return executableCount;
    }

    public int getTypeParameterCount() {
        return typeParameterCount;
    }

    public List<Element> getVisited() {
        return visited;
    }

    @Override
    public Void visitPackage(PackageElement e, Void p) {
        packageCount++;
        visited.add(e);
        return super.visitPackage(e, p);
    }

    @Override
    public Void visitType(TypeElement e, Void p) {
        typeCount++;
        visited.add(e);
        return super.visitType(e, p);
    }

    @Override
    public Void visitVariable(VariableElement e, Void p) {
        variableCount++;
        visited.add(e);
        return super.visitVariable(e, p);
    }

    @Override
    public Void visitExecutable(ExecutableElement e, Void p) {
        executableCount++;
        visited.add(e);
        return super.visitExecutable(e, p);
    }

    @Override
    public Void visitTypeParameter(TypeParameterElement e, Void p) {
        typeParameterCount++;
        visited.add(e);
        return super.visitTypeParameter(e, p);
    }

}
