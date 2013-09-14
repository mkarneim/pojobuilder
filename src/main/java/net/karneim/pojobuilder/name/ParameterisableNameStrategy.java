package net.karneim.pojobuilder.name;

import net.karneim.pojobuilder.BuildException;
import net.karneim.pojobuilder.GeneratePojoBuilder;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * Implements asterisk-expansion in the name
 */
public class ParameterisableNameStrategy implements NameStrategy {

    private final ProcessingEnvironment env;

    public ParameterisableNameStrategy(ProcessingEnvironment env) {
        this.env = env;
    }

    @Override
    public String getName(GeneratePojoBuilder annotation, TypeElement pojoType) {
        String withName = annotation.withName();
        String finalName = withName == null ? "" : withName.replace("*", pojoType.getSimpleName());

        // Static validation
        // TODO Find nice library which encapsulates defining a "legal" class name
        if (finalName.trim().isEmpty()) {
            throw new BuildException(Diagnostic.Kind.ERROR, String.format("Bad withName (%s)", withName), pojoType);
        }
        return finalName;
    }

    @Override
    public boolean isAbstract() {
        return false;
    }

}