package net.karneim.pojobuilder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface GeneratePojoBuilder {
	Class withBaseclass() default Object.class;

	String intoPackage() default "#default";
}
