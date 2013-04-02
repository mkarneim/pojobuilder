package net.karneim.pojobuilder;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The {@link PropertyNames} annotation is used on a factory method in order to
 * map the parameters to the property names of the pojo.
 * @deprecated Support for @PropertyNames will be removed in future. Please use {@link FactoryProperties}
 */
@Target(METHOD)
@Retention(SOURCE)
@Deprecated
public @interface PropertyNames {
	/**
	 * An array of property names that maps the method parameters to the pojo's
	 * property names by index.
	 */
	String[] value();
}