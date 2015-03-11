package net.karneim.pojobuilder.processor.with.excludeproperties;

public class Pojo3 {
  private String firstname;
  private String surname;
  private String login;

  public Pojo3(String login) {
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

}
