package net.karneim.pojobuilder.processor.with.innerclass;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class PojoFactory {
  @GeneratePojoBuilder
  public static OuterPojo.InnerPojo createInnerPojo() {
    return new OuterPojo.InnerPojo();
  }
}
