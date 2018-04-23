package net.karneim.pojobuilder.processor.with.excludeproperties;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class Pojo1Builder
    implements Cloneable {
  protected Pojo1Builder self;
  protected String value$email$java$lang$String;
  protected boolean isSet$email$java$lang$String;
  protected String value$city$java$lang$String;
  protected boolean isSet$city$java$lang$String;
  protected String value$postCode$java$lang$String;
  protected boolean isSet$postCode$java$lang$String;
  protected String value$url$java$lang$String;
  protected boolean isSet$url$java$lang$String;

  /**
   * Creates a new {@link Pojo1Builder}.
   */
  public Pojo1Builder() {
    self = (Pojo1Builder)this;
  }

  /**
   * Sets the default value for the {@link Pojo1#email} property.
   *
   * @param value the default value
   * @return this builder
   */
  public Pojo1Builder withEmail(String value) {
    this.value$email$java$lang$String = value;
    this.isSet$email$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default value for the {@link Pojo1#city} property.
   *
   * @param value the default value
   * @return this builder
   */
  public Pojo1Builder withCity(String value) {
    this.value$city$java$lang$String = value;
    this.isSet$city$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default value for the postCode property.
   *
   * @param value the default value
   * @return this builder
   */
  public Pojo1Builder withPostCode(String value) {
    this.value$postCode$java$lang$String = value;
    this.isSet$postCode$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default value for the url property.
   *
   * @param value the default value
   * @return this builder
   */
  public Pojo1Builder withUrl(String value) {
    this.value$url$java$lang$String = value;
    this.isSet$url$java$lang$String = true;
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
      Pojo1Builder result = (Pojo1Builder)super.clone();
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
  public Pojo1Builder but() {
    return (Pojo1Builder)clone();
  }

  /**
   * Creates a new {@link Pojo1} based on this builder's settings.
   *
   * @return the created Pojo1
   */
  public Pojo1 build() {
    try {
      Pojo1 result = new Pojo1();
      if (isSet$postCode$java$lang$String) {
        result.setPostCode(value$postCode$java$lang$String);
      }
      if (isSet$url$java$lang$String) {
        result.setUrl(value$url$java$lang$String);
      }
      if (isSet$email$java$lang$String) {
        result.email = value$email$java$lang$String;
      }
      if (isSet$city$java$lang$String) {
        result.city = value$city$java$lang$String;
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
