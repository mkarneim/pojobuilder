package net.karneim.pojobuilder.analysis.with.excludeproperties;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(excludeProperties = "street:java.lang.String")
public class Pojo2 {
  public String street;
  public String city;

}
