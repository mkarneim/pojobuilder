package net.karneim.pojobuilder.processor.with.includeproperties;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class Pojo3Builder
    implements Cloneable {
  protected Pojo3Builder self;
  protected String value$login$java$lang$String;
  protected boolean isSet$login$java$lang$String;
  protected String value$firstname$java$lang$String;
  protected boolean isSet$firstname$java$lang$String;
  protected String value$surname$java$lang$String;
  protected boolean isSet$surname$java$lang$String;

  /**
   * Creates a new {@link Pojo3Builder}.
   */
  public Pojo3Builder() {
    self = (Pojo3Builder)this;
  }

  /**
   * Sets the default value for the login property.
   *
   * @param value the default value
   * @return this builder
   */
  public Pojo3Builder withLogin(String value) {
    this.value$login$java$lang$String = value;
    this.isSet$login$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default value for the firstname property.
   *
   * @param value the default value
   * @return this builder
   */
  public Pojo3Builder withFirstname(String value) {
    this.value$firstname$java$lang$String = value;
    this.isSet$firstname$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default value for the surname property.
   *
   * @param value the default value
   * @return this builder
   */
  public Pojo3Builder withSurname(String value) {
    this.value$surname$java$lang$String = value;
    this.isSet$surname$java$lang$String = true;
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
      Pojo3Builder result = (Pojo3Builder)super.clone();
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
  public Pojo3Builder but() {
    return (Pojo3Builder)clone();
  }

  /**
   * Creates a new {@link Pojo3} based on this builder's settings.
   *
   * @return the created Pojo3
   */
  public Pojo3 build() {
    try {
      Pojo3 result = Pojo3Factory.createPojo(value$login$java$lang$String);
      if (isSet$firstname$java$lang$String) {
        result.setFirstname(value$firstname$java$lang$String);
      }
      if (isSet$surname$java$lang$String) {
        result.setSurname(value$surname$java$lang$String);
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
