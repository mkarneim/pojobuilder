package net.karneim.pojobuilder.processor.with.includeproperties;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class Pojo2 {
  private String firstname;
  private String surname;
  private String login;
  private String email;

  @GeneratePojoBuilder(includeProperties = "*name")
  public Pojo2(String login) {
    this.login = login;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
