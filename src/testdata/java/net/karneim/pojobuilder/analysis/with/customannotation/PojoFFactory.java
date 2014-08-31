package net.karneim.pojobuilder.analysis.with.customannotation;

import net.karneim.pojobuilder.GeneratePojoBuilder;


public class PojoFFactory {

  @MyCustomAnnotationF
  @GeneratePojoBuilder
  public static PojoF createPojoF() {
    return new PojoF();
  }
}
