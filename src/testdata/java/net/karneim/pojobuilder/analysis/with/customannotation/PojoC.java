package net.karneim.pojobuilder.analysis.with.customannotation;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@MyCustomAnnotationC
@GeneratePojoBuilder(intoPackage = "builder")
public class PojoC {
  public String name;
}
