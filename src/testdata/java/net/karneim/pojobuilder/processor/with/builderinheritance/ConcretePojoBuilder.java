package net.karneim.pojobuilder.processor.with.builderinheritance;

import javax.annotation.Generated;

@Generated("PojoBuilder")
public class ConcretePojoBuilder
    implements Cloneable {
  protected ConcretePojoBuilder self;
  protected int value$field2$int;
  protected boolean isSet$field2$int;
  protected int value$field$int;
  protected boolean isSet$field$int;

  /**
   * Creates a new {@link ConcretePojoBuilder}.
   */
  public ConcretePojoBuilder() {
    self = (ConcretePojoBuilder)this;
  }

  /**
   * Sets the default value for the {@link AbstractPojo#field2} property.
   *
   * @param value the default value
   * @return this builder
   */
  public ConcretePojoBuilder withField2(int value) {
    this.value$field2$int = value;
    this.isSet$field2$int = true;
    return self;
  }

  /**
   * Sets the default value for the {@link AbstractPojo#field} property.
   *
   * @param value the default value
   * @return this builder
   */
  public ConcretePojoBuilder withField(int value) {
    this.value$field$int = value;
    this.isSet$field$int = true;
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
      ConcretePojoBuilder result = (ConcretePojoBuilder)super.clone();
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
  public ConcretePojoBuilder but() {
    return (ConcretePojoBuilder)clone();
  }

  /**
   * Creates a new {@link AbstractPojo} based on this builder's settings.
   *
   * @return the created AbstractPojo
   */
  public AbstractPojo build() {
    try {
      AbstractPojo result = AbstractPojo.instantiate(value$field2$int);
      if (isSet$field$int) {
        result.field = value$field$int;
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new java.lang.reflect.UndeclaredThrowableException(ex);
    }
  }
}
