package net.karneim.pojobuilder.analysis.with.excludeproperties;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(excludeProperties = "hobbies:java.lang.String[]")
public class Pojo4 {
  public String firstname;
  public String surname;
  public String[] hobbies;

}
