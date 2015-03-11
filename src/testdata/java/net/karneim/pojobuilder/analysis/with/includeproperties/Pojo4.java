package net.karneim.pojobuilder.analysis.with.includeproperties;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(includeProperties = "hobbies:java.lang.String[]")
public class Pojo4 {
  public String firstname;
  public String surname;
  public String[] hobbies;

}
