package net.karneim.pojobuilder.processor.with.customannotation;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withName="Fluent*BuilderA",intoPackage = "*.builder")
public @interface MyCustomAnnotationA {

}
