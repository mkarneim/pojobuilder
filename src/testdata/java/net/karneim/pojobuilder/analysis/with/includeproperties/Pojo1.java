package net.karneim.pojobuilder.analysis.with.includeproperties;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(includeProperties = "*name")
public class Pojo1 {
  public String firstname;
  public String surname;
  public String email;

}
