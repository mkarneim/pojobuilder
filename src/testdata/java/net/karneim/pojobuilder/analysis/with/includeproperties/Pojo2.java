package net.karneim.pojobuilder.analysis.with.includeproperties;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(includeProperties = "street:java.lang.String")
public class Pojo2 {
  public String street;
  public String city;

}
