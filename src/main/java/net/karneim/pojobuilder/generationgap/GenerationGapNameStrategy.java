package net.karneim.pojobuilder.generationgap;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.name.NameStrategy;

import javax.lang.model.element.TypeElement;

/**
 * Implement naming for the abstract builder in Generation Gap pattern
 */
public class GenerationGapNameStrategy implements NameStrategy {

    private static final String PREPEND = "Abstract";
    private NameStrategy defaultNameStrategy;

    public GenerationGapNameStrategy(NameStrategy defaultNameStrategy) {
        this.defaultNameStrategy = defaultNameStrategy;
    }

    @Override
    public String getName(GeneratePojoBuilder annotation, TypeElement pojoType) {
        return PREPEND + defaultNameStrategy.getName(annotation, pojoType);
    }

    @Override
    public boolean isAbstract() {
        return true;
    }

}
