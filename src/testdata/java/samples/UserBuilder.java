package samples;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class UserBuilder
    implements Cloneable {
  protected UserBuilder self;
  protected String value$name$java$lang$String;
  protected boolean isSet$name$java$lang$String;
  protected char[] value$password$char$L;
  protected boolean isSet$password$char$L;

  /**
   * Creates a new {@link UserBuilder}.
   */
  public UserBuilder() {
    self = (UserBuilder)this;
  }

  /**
   * Sets the default value for the name property.
   *
   * @param value the default value
   * @return this builder
   */
  public UserBuilder withName(String value) {
    this.value$name$java$lang$String = value;
    this.isSet$name$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default value for the password property.
   *
   * @param value the default value
   * @return this builder
   */
  public UserBuilder withPassword(char[] value) {
    this.value$password$char$L = value;
    this.isSet$password$char$L = true;
    return self;
  }

  /**
   * Returns a clone of this builder.
   *
   * @return the clone
   */
  @Override
  @GwtIncompatible
  public Object clone() {
    try {
      UserBuilder result = (UserBuilder)super.clone();
      result.self = result;
      return result;
    } catch (CloneNotSupportedException e) {
      throw new InternalError(e.getMessage());
    }
  }

  /**
   * Returns a clone of this builder.
   *
   * @return the clone
   */
  @GwtIncompatible
  public UserBuilder but() {
    return (UserBuilder)clone();
  }

  /**
   * Creates a new {@link User} based on this builder's settings.
   *
   * @return the created User
   */
  public User build() {
    try {
      User result = new User();
      if (isSet$name$java$lang$String) {
        result.setName(value$name$java$lang$String);
      }
      if (isSet$password$char$L) {
        result.setPassword(value$password$char$L);
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
