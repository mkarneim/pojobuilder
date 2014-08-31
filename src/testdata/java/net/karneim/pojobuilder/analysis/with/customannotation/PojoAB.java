package net.karneim.pojobuilder.analysis.with.customannotation;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@MyCustomAnnotationA
@MyCustomAnnotationB
@GeneratePojoBuilder(intoPackage = "builder")
public class PojoAB {
  public String name;
}
