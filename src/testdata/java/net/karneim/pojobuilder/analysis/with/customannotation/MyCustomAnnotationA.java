package net.karneim.pojobuilder.analysis.with.customannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withName="Fluent*BuilderA")
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.ANNOTATION_TYPE})
public @interface MyCustomAnnotationA {

}
