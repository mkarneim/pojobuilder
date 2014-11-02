package net.karneim.pojobuilder.processor.with.copymethod;

import javax.annotation.Generated;

@Generated("PojoBuilder")
public class PojoBuilder
    implements Cloneable {
  protected PojoBuilder self;
  protected String value$firstname$java$lang$String;
  protected boolean isSet$firstname$java$lang$String;
  protected String value$surname$java$lang$String;
  protected boolean isSet$surname$java$lang$String;
  protected int value$age$int;
  protected boolean isSet$age$int;

  /**
   * Creates a new {@link PojoBuilder}.
   */
  public PojoBuilder() {
    self = (PojoBuilder)this;
  }

  /**
   * Sets the default value for the {@link Pojo#firstname} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoBuilder withFirstname(String value) {
    this.value$firstname$java$lang$String = value;
    this.isSet$firstname$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default value for the {@link Pojo#surname} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoBuilder withSurname(String value) {
    this.value$surname$java$lang$String = value;
    this.isSet$surname$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default value for the {@link Pojo#age} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoBuilder withAge(int value) {
    this.value$age$int = value;
    this.isSet$age$int = true;
    return self;
  }

  /**
   * Returns a clone of this builder.
   *
   * @return the clone
   */
  @Override
  public Object clone() {
    try {
      PojoBuilder result = (PojoBuilder)super.clone();
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
  public PojoBuilder but() {
    return (PojoBuilder)clone();
  }

  /**
   * Copies the values from the given pojo into this builder.
   *
   * @param pojo
   * @return this builder
   */
  public PojoBuilder copy(Pojo pojo) {
    withFirstname(pojo.getFirstname());
    withSurname(pojo.getSurname());
    withAge(pojo.age);
    return self;
  }

  /**
   * Creates a new {@link Pojo} based on this builder's settings.
   *
   * @return the created Pojo
   */
  public Pojo build() {
    try {
      Pojo result = new Pojo(value$firstname$java$lang$String, value$surname$java$lang$String);
      if (isSet$age$int) {
        result.age = value$age$int;
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new java.lang.reflect.UndeclaredThrowableException(ex);
    }
  }
}
