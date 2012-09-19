package net.karneim.pojobuilder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Use this annotation for generating a fluent pojo builder.
 * @author karneim
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface GeneratePojoBuilder {
	/**
	 * Specifies the base class of the generated builder.
	 * @return the base class of the generated builder
	 */
	Class withBaseclass() default Object.class;
	/**
	 * Specifies the name of the generated builder.
	 * Any asterisk will be replaced with the pojos simple name.
	 * Default is "*Builder".
	 * @return the name of the generated builder
	 */
	String withName() default "*Builder";
	/**
	 * Specifies the package of the generated builder.
	 * Any asterisk will be replaced with the pojos package.
	 * Default is "*".
	 * @return  the package of the generated builder
	 */
	String intoPackage() default "*";
}
