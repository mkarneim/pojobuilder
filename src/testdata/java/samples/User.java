package samples;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class User {
  private String name;
  private char[] password;
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public char[] getPassword() {
    return password;
  }
  public void setPassword(char[] password) {
    this.password = password;
  }
  
}
