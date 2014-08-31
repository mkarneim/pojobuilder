package net.karneim.pojobuilder.processor.with.copymethod;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class Pojo {
  private String firstname;
  private String surname;
  public int age;

  @GeneratePojoBuilder(withCopyMethod=true)
  public Pojo(String firstname, String surname) {
    this.firstname = firstname;
    this.surname = surname;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getSurname() {
    return surname;
  }

}
