package samples;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class CredentialsBuilder
    implements Cloneable {
  protected CredentialsBuilder self;
  protected CredentialsValidator validator = new CredentialsValidator();
  protected String value$name$java$lang$String;
  protected boolean isSet$name$java$lang$String;
  protected char[] value$password$char$L;
  protected boolean isSet$password$char$L;

  /**
   * Creates a new {@link CredentialsBuilder}.
   */
  public CredentialsBuilder() {
    self = (CredentialsBuilder)this;
  }

  /**
   * Sets the default value for the {@link Credentials#name} property.
   *
   * @param value the default value
   * @return this builder
   */
  public CredentialsBuilder withName(String value) {
    this.value$name$java$lang$String = value;
    this.isSet$name$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default value for the {@link Credentials#password} property.
   *
   * @param value the default value
   * @return this builder
   */
  public CredentialsBuilder withPassword(char[] value) {
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
      CredentialsBuilder result = (CredentialsBuilder)super.clone();
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
  public CredentialsBuilder but() {
    return (CredentialsBuilder)clone();
  }

  /**
   * Creates a new {@link Credentials} based on this builder's settings.
   *
   * @return the created Credentials
   */
  public Credentials build() {
    try {
      Credentials result = new Credentials();
      if (isSet$name$java$lang$String) {
        result.name = value$name$java$lang$String;
      }
      if (isSet$password$char$L) {
        result.password = value$password$char$L;
      }
      validator.validate(result);
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
