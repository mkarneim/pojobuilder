package net.karneim.pojobuilder.packages;

import net.karneim.pojobuilder.GeneratePojoBuilder;

import javax.lang.model.element.TypeElement;

/**
 * Pluggable strategy to handle {@link net.karneim.pojobuilder.GeneratePojoBuilder#intoPackage()}
 */
public interface PackageStrategy {

    /**
     * The fully-qualified package name for this builder.
     *
     * @param annotation The active {@link net.karneim.pojobuilder.GeneratePojoBuilder} annotation
     * @param pojoType   The builder's produced Pojo type
     * @return a non-null, statically-sound package name. Empty string if default package
     */
    String getPackage(GeneratePojoBuilder annotation, TypeElement pojoType);

}
