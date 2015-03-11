package net.karneim.pojobuilder.processor.with.excludeproperties;

import java.net.URL;
import java.util.List;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(excludeProperties = {"*name", "street:java.lang.String", "country", "url:java.net.URL",
    "hobbies:java.lang.String[]", "*:*.List<*.String>"})
public class Pojo1 {
  public String firstname;
  public String surname;
  public String email;
  public String street;
  public String city;
  public String[] hobbies;
  public List<String> skills;

  public void setPostCode(String value) {

  }

  public void setCountry(String value) {

  }

  public void setUrl(URL value) {

  }

  public void setUrl(String value) {

  }
}
