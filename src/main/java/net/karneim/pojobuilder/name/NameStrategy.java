package net.karneim.pojobuilder.name;

import net.karneim.pojobuilder.GeneratePojoBuilder;

import javax.lang.model.element.TypeElement;

/**
 * Pluggable strategy to handle {@link net.karneim.pojobuilder.GeneratePojoBuilder#withName()}
 */
public interface NameStrategy {

    /**
     * The class name (without package) for this builder.
     *
     * @param annotation The active {@link net.karneim.pojobuilder.GeneratePojoBuilder} annotation
     * @param pojoType   The builder's produced Pojo type
     * @return a non-null, statically-sound class name
     */
    String getName(GeneratePojoBuilder annotation, TypeElement pojoType);

    /**
     * If this builder is abstract.
     */
    boolean isAbstract();

}
