package net.karneim.pojobuilder.analysis.with.excludeproperties;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(excludeProperties = "*name")
public class Pojo1 {
  public String firstname;
  public String surname;
  public String email;

}
