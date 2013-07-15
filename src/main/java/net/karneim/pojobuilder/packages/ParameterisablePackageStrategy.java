package net.karneim.pojobuilder.packages;

import net.karneim.pojobuilder.BuildException;
import net.karneim.pojobuilder.GeneratePojoBuilder;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * Implements asterisk-expansion in the package name. Handles a pojo in default package as well as an explicit
 * {@link GeneratePojoBuilder#intoPackage()} of default.
 */
public class ParameterisablePackageStrategy implements PackageStrategy {

    private final ProcessingEnvironment env;

    public ParameterisablePackageStrategy(ProcessingEnvironment env) {
        this.env = env;
    }

    @Override
    public String getPackage(GeneratePojoBuilder annotation, TypeElement pojoType) {
        String intoPackage = annotation.intoPackage();
        // Accepts null as that is what Class.getPackage() returns for the unnamed package so precedent is set
        // (although it is not possible to use class.getPackage() in an annotation definition)
        if (intoPackage == null || intoPackage.trim().isEmpty()) {
            return "";
        }
        PackageElement packageElement = env.getElementUtils().getPackageOf(pojoType);
        String pojoPackage = packageElement.getQualifiedName().toString();
        String finalPackage = intoPackage.replace("*", pojoPackage);
        if ("".equals(pojoPackage) && finalPackage.startsWith(".")) {
            // Occurs if pattern is *.builder and the pojo is in unnamed package (bad coder!)
            finalPackage = finalPackage.substring(1);
        }

        // Static validation
        // TODO Find nice library which encapsulates defining a "legal" package name
        if (finalPackage.startsWith(".")) {
            throw new BuildException(Diagnostic.Kind.ERROR, String.format("Bad intoPackage (%s)", intoPackage), pojoType);
        }
        return finalPackage;
    }

}