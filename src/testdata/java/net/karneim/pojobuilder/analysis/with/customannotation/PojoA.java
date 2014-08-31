package net.karneim.pojobuilder.analysis.with.customannotation;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@MyCustomAnnotationA
@GeneratePojoBuilder(intoPackage="builder")
public class PojoA {
  public String name;
}
