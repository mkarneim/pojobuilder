package samples;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class TextEmailBuilder
    implements Cloneable {
  protected TextEmailBuilder self;
  protected String value$from$java$lang$String;
  protected boolean isSet$from$java$lang$String;
  protected String[] value$recipients$java$lang$String$L;
  protected boolean isSet$recipients$java$lang$String$L;
  protected String value$subject$java$lang$String;
  protected boolean isSet$subject$java$lang$String;
  protected String value$body$java$lang$String;
  protected boolean isSet$body$java$lang$String;

  /**
   * Creates a new {@link TextEmailBuilder}.
   */
  public TextEmailBuilder() {
    self = (TextEmailBuilder)this;
  }

  /**
   * Sets the default value for the from property.
   *
   * @param value the default value
   * @return this builder
   */
  public TextEmailBuilder withFrom(String value) {
    this.value$from$java$lang$String = value;
    this.isSet$from$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default value for the recipients property.
   *
   * @param value the default value
   * @return this builder
   */
  public TextEmailBuilder withRecipients(String... value) {
    this.value$recipients$java$lang$String$L = value;
    this.isSet$recipients$java$lang$String$L = true;
    return self;
  }

  /**
   * Sets the default value for the subject property.
   *
   * @param value the default value
   * @return this builder
   */
  public TextEmailBuilder withSubject(String value) {
    this.value$subject$java$lang$String = value;
    this.isSet$subject$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default value for the body property.
   *
   * @param value the default value
   * @return this builder
   */
  public TextEmailBuilder withBody(String value) {
    this.value$body$java$lang$String = value;
    this.isSet$body$java$lang$String = true;
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
      TextEmailBuilder result = (TextEmailBuilder)super.clone();
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
  public TextEmailBuilder but() {
    return (TextEmailBuilder)clone();
  }

  /**
   * Copies the values from the given pojo into this builder.
   *
   * @param pojo
   * @return this builder
   */
  public TextEmailBuilder copy(TextEmail pojo) {
    withFrom(pojo.getFrom());
    withRecipients(pojo.getRecipients());
    withSubject(pojo.getSubject());
    withBody(pojo.getBody());
    return self;
  }

  /**
   * Creates a new {@link TextEmail} based on this builder's settings.
   *
   * @return the created TextEmail
   */
  public TextEmail build() {
    try {
      TextEmail result = new TextEmail();
      if (isSet$from$java$lang$String) {
        result.setFrom(value$from$java$lang$String);
      }
      if (isSet$recipients$java$lang$String$L) {
        result.setRecipients(value$recipients$java$lang$String$L);
      }
      if (isSet$subject$java$lang$String) {
        result.setSubject(value$subject$java$lang$String);
      }
      if (isSet$body$java$lang$String) {
        result.setBody(value$body$java$lang$String);
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
