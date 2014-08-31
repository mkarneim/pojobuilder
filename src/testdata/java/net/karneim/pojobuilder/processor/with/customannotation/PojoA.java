package net.karneim.pojobuilder.processor.with.customannotation;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@MyCustomAnnotationA
@GeneratePojoBuilder(intoPackage="*.builder")
public class PojoA {
  public String name;
}
