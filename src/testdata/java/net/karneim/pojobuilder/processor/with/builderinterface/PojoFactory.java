package net.karneim.pojobuilder.processor.with.builderinterface;

import java.io.File;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class PojoFactory {

  @GeneratePojoBuilder(withName = "AnotherPojoBuilder", withBuilderInterface = Builder.class, withBuilderProperties = true)
  public static Pojo createPojo(File file, int age) {
    Pojo result = new Pojo(file);
    result.setAge(age);
    return result;
  }
}
