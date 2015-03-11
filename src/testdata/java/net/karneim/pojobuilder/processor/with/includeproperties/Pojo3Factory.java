package net.karneim.pojobuilder.processor.with.includeproperties;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class Pojo3Factory {
  @GeneratePojoBuilder(includeProperties = "*name")
  public static Pojo3 createPojo(String login) {
    return new Pojo3(login);
  }
}
