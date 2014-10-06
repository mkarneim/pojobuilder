package samples;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withValidator=CredentialsValidator.class)
public class Credentials {
  public String name;
  public char[] password;
}
