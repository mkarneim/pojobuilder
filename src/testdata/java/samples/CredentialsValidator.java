package samples;

public class CredentialsValidator {
  public void validate(Credentials c) {
    if (c.name == null || c.name.isEmpty()) {
      throw new ValidationException("Missing name!");
    }
    if (c.password == null || c.password.length == 0) {
      throw new ValidationException("Missing password!");
    }
  }
}
