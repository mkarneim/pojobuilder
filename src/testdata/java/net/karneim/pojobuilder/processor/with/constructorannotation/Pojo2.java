package net.karneim.pojobuilder.processor.with.constructorannotation;

import java.beans.ConstructorProperties;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class Pojo2 {
  private String firstname;
  private String surname;

  @GeneratePojoBuilder
  @ConstructorProperties({"firstname", "surname"})
  public Pojo2(String arg1, String arg2) {
    super();
    this.firstname = arg1;
    this.surname = arg2;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getSurname() {
    return surname;
  }


}
