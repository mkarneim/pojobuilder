package samples;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class Contact {
  private final String surname;
  private final String firstname;
  private String email;

  @GeneratePojoBuilder
  public Contact(String surname, String firstname) {
    this.surname = surname;
    this.firstname = firstname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSurname() {
    return surname;
  }

  public String getFirstname() {
    return firstname;
  }

  @Override
  public String toString() {
    return "Contact [surname=" + surname + ", firstname=" + firstname + ", email=" + email + "]";
  }
  
}
