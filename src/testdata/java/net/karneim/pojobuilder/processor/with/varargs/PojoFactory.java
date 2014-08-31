package net.karneim.pojobuilder.processor.with.varargs;

import java.math.BigDecimal;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class PojoFactory {

  @GeneratePojoBuilder(withName = "Other*Builder")
  public static Pojo createPojo(BigDecimal... someNumbers) {
    return new Pojo(someNumbers);
  }
}
