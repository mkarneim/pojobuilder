package net.karneim.pojobuilder.processor.with.includeproperties;

import java.net.URL;
import java.util.List;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class Pojo1Builder
    implements Cloneable {
  protected Pojo1Builder self;
  protected String value$firstname$java$lang$String;
  protected boolean isSet$firstname$java$lang$String;
  protected String value$surname$java$lang$String;
  protected boolean isSet$surname$java$lang$String;
  protected String value$street$java$lang$String;
  protected boolean isSet$street$java$lang$String;
  protected String[] value$hobbies$java$lang$String$L;
  protected boolean isSet$hobbies$java$lang$String$L;
  protected List<String> value$skills$java$util$List;
  protected boolean isSet$skills$java$util$List;
  protected int value$someIntNumber$int;
  protected boolean isSet$someIntNumber$int;
  protected String value$country$java$lang$String;
  protected boolean isSet$country$java$lang$String;
  protected URL value$url$java$net$URL;
  protected boolean isSet$url$java$net$URL;

  /**
   * Creates a new {@link Pojo1Builder}.
   */
  public Pojo1Builder() {
    self = (Pojo1Builder)this;
  }

  /**
   * Sets the default value for the {@link Pojo1#firstname} property.
   *
   * @param value the default value
   * @return this builder
   */
  public Pojo1Builder withFirstname(String value) {
    this.value$firstname$java$lang$String = value;
    this.isSet$firstname$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default value for the {@link Pojo1#surname} property.
   *
   * @param value the default value
   * @return this builder
   */
  public Pojo1Builder withSurname(String value) {
    this.value$surname$java$lang$String = value;
    this.isSet$surname$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default value for the {@link Pojo1#street} property.
   *
   * @param value the default value
   * @return this builder
   */
  public Pojo1Builder withStreet(String value) {
    this.value$street$java$lang$String = value;
    this.isSet$street$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default value for the {@link Pojo1#hobbies} property.
   *
   * @param value the default value
   * @return this builder
   */
  public Pojo1Builder withHobbies(String[] value) {
    this.value$hobbies$java$lang$String$L = value;
    this.isSet$hobbies$java$lang$String$L = true;
    return self;
  }

  /**
   * Sets the default value for the {@link Pojo1#skills} property.
   *
   * @param value the default value
   * @return this builder
   */
  public Pojo1Builder withSkills(List<String> value) {
    this.value$skills$java$util$List = value;
    this.isSet$skills$java$util$List = true;
    return self;
  }

  /**
   * Sets the default value for the {@link Pojo1#someIntNumber} property.
   *
   * @param value the default value
   * @return this builder
   */
  public Pojo1Builder withSomeIntNumber(int value) {
    this.value$someIntNumber$int = value;
    this.isSet$someIntNumber$int = true;
    return self;
  }

  /**
   * Sets the default value for the country property.
   *
   * @param value the default value
   * @return this builder
   */
  public Pojo1Builder withCountry(String value) {
    this.value$country$java$lang$String = value;
    this.isSet$country$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default value for the url property.
   *
   * @param value the default value
   * @return this builder
   */
  public Pojo1Builder withUrl(URL value) {
    this.value$url$java$net$URL = value;
    this.isSet$url$java$net$URL = true;
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
      if (isSet$country$java$lang$String) {
        result.setCountry(value$country$java$lang$String);
      }
      if (isSet$url$java$net$URL) {
        result.setUrl(value$url$java$net$URL);
      }
      if (isSet$firstname$java$lang$String) {
        result.firstname = value$firstname$java$lang$String;
      }
      if (isSet$surname$java$lang$String) {
        result.surname = value$surname$java$lang$String;
      }
      if (isSet$street$java$lang$String) {
        result.street = value$street$java$lang$String;
      }
      if (isSet$hobbies$java$lang$String$L) {
        result.hobbies = value$hobbies$java$lang$String$L;
      }
      if (isSet$skills$java$util$List) {
        result.skills = value$skills$java$util$List;
      }
      if (isSet$someIntNumber$int) {
        result.someIntNumber = value$someIntNumber$int;
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
