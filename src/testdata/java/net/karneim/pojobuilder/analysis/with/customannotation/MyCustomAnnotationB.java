package net.karneim.pojobuilder.analysis.with.customannotation;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withName = "Fluent*BuilderB", withCopyMethod = true)
public @interface MyCustomAnnotationB {

}
