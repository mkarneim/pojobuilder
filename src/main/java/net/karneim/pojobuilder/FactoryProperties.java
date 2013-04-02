package net.karneim.pojobuilder;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * The {@link FactoryProperties} annotation is used on a factory method in order to
 * map the parameters to the property names of the pojo. It is exactly comparable to {@link java.beans.ConstructorProperties}.
 */
@Target(METHOD)
@Retention(SOURCE)
public @interface FactoryProperties {
    /**
     <p>The getter names.</p>
     @return the getter names corresponding to the parameters in the
     annotated method.
     */
    String[] value();
}