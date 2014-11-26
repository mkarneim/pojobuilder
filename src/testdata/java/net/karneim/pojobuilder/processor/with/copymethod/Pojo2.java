package net.karneim.pojobuilder.processor.with.copymethod;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class Pojo2 {
  private final String firstname;
  private final String surname;

  @GeneratePojoBuilder(withCopyMethod = true)
  public Pojo2(String firstname, String surname) {
    this.firstname = firstname;
    this.surname = surname;
  }
  
  public String getName() {
    return firstname+" "+surname;
  }

}
