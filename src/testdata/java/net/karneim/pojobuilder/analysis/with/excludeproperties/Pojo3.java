package net.karneim.pojobuilder.analysis.with.excludeproperties;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(excludeProperties = "country")
public class Pojo3 {
  public String street;
  public String city;

  public void setPostCode(String value) {

  }

  public void setCountry(String value) {

  }

}
