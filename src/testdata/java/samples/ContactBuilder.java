package samples;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class ContactBuilder
    implements Cloneable {
  protected ContactBuilder self;
  protected String value$surname$java$lang$String;
  protected boolean isSet$surname$java$lang$String;
  protected String value$firstname$java$lang$String;
  protected boolean isSet$firstname$java$lang$String;
  protected String value$email$java$lang$String;
  protected boolean isSet$email$java$lang$String;

  /**
   * Creates a new {@link ContactBuilder}.
   */
  public ContactBuilder() {
    self = (ContactBuilder)this;
  }

  /**
   * Sets the default value for the surname property.
   *
   * @param value the default value
   * @return this builder
   */
  public ContactBuilder withSurname(String value) {
    this.value$surname$java$lang$String = value;
    this.isSet$surname$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default value for the firstname property.
   *
   * @param value the default value
   * @return this builder
   */
  public ContactBuilder withFirstname(String value) {
    this.value$firstname$java$lang$String = value;
    this.isSet$firstname$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default value for the email property.
   *
   * @param value the default value
   * @return this builder
   */
  public ContactBuilder withEmail(String value) {
    this.value$email$java$lang$String = value;
    this.isSet$email$java$lang$String = true;
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
      ContactBuilder result = (ContactBuilder)super.clone();
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
  public ContactBuilder but() {
    return (ContactBuilder)clone();
  }

  /**
   * Creates a new {@link Contact} based on this builder's settings.
   *
   * @return the created Contact
   */
  public Contact build() {
    try {
      Contact result = new Contact(value$surname$java$lang$String, value$firstname$java$lang$String);
      if (isSet$email$java$lang$String) {
        result.setEmail(value$email$java$lang$String);
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
