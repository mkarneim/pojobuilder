package net.karneim.pojobuilder.processor.with.excludeproperties;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class Pojo3Factory {
  @GeneratePojoBuilder(excludeProperties = "login")
  public static Pojo3 createPojo(String login) {
    return new Pojo3(login);
  }
}
