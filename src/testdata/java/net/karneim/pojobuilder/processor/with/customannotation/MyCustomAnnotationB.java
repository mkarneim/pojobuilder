package net.karneim.pojobuilder.processor.with.customannotation;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withName = "Fluent*BuilderB", withCopyMethod = true)
public @interface MyCustomAnnotationB {

}
