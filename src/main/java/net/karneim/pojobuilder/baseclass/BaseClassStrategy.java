package net.karneim.pojobuilder.baseclass;

import net.karneim.pojobuilder.model.TypeM;

/**
 * Pluggable strategy to handle {@link net.karneim.pojobuilder.GeneratePojoBuilder#withBaseclass()}
 */
public interface BaseClassStrategy {

    /**
     * The fully-qualified base class name for this builder.
     * FIXME String, Class or TypeM - should really be CLassM but this is specialised for _builder_ classes
     *
     * @return a non-null, statically-sound class name
     */
    TypeM getBaseClass();

}
