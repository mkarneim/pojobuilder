package net.karneim.pojobuilder.processor.with.copymethod;

import javax.annotation.Generated;

@Generated("PojoBuilder")
public class PojoWithOptionalPropertyBuilder
    implements Cloneable {
  protected PojoWithOptionalPropertyBuilder self;
  protected String value$prop$java$lang$String;
  protected boolean isSet$prop$java$lang$String;

  /**
   * Creates a new {@link PojoWithOptionalPropertyBuilder}.
   */
  public PojoWithOptionalPropertyBuilder() {
    self = (PojoWithOptionalPropertyBuilder)this;
  }

  /**
   * Sets the default value for the {@link PojoWithOptionalProperty#prop} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithOptionalPropertyBuilder withProp(String value) {
    this.value$prop$java$lang$String = value;
    this.isSet$prop$java$lang$String = true;
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
      PojoWithOptionalPropertyBuilder result = (PojoWithOptionalPropertyBuilder)super.clone();
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
  public PojoWithOptionalPropertyBuilder but() {
    return (PojoWithOptionalPropertyBuilder)clone();
  }
  
  /**
   * Copies the values from the given pojo into this builder.
   *
   * @param pojo
   * @return this builder
   */
  public PojoWithOptionalPropertyBuilder copy(PojoWithOptionalProperty pojo) {
    withProp(pojo.prop.get());
    return self;
  }

  /**
   * Creates a new {@link PojoWithOptionalProperty} based on this builder's settings.
   *
   * @return the created PojoWithOptionalProperty
   */
  public PojoWithOptionalProperty build() {
    try {
      PojoWithOptionalProperty result = new PojoWithOptionalProperty(value$prop$java$lang$String);
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new java.lang.reflect.UndeclaredThrowableException(ex);
    }
  }
}
