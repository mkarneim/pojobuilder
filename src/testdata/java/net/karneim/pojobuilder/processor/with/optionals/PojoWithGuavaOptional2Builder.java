package net.karneim.pojobuilder.processor.with.optionals;

import com.google.common.base.Optional;
import javax.annotation.Generated;

@Generated("PojoBuilder")
public class PojoWithGuavaOptional2Builder
    implements Cloneable {
  protected PojoWithGuavaOptional2Builder self;
  protected Optional<Integer> value$optionalValue$com$google$common$base$Optional;
  protected boolean isSet$optionalValue$com$google$common$base$Optional;

  /**
   * Creates a new {@link PojoWithGuavaOptional2Builder}.
   */
  public PojoWithGuavaOptional2Builder() {
    self = (PojoWithGuavaOptional2Builder)this;
  }

  /**
   * Sets the default value for the {@link PojoWithGuavaOptional2#optionalValue} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptional2Builder withOptionalValue(Optional<Integer> value) {
    this.value$optionalValue$com$google$common$base$Optional = value;
    this.isSet$optionalValue$com$google$common$base$Optional = true;
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
      PojoWithGuavaOptional2Builder result = (PojoWithGuavaOptional2Builder)super.clone();
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
  public PojoWithGuavaOptional2Builder but() {
    return (PojoWithGuavaOptional2Builder)clone();
  }

  /**
   * Creates a new {@link PojoWithGuavaOptional2} based on this builder's settings.
   *
   * @return the created PojoWithGuavaOptional2
   */
  public PojoWithGuavaOptional2 build() {
    try {
      PojoWithGuavaOptional2 result = new PojoWithGuavaOptional2();
      if (isSet$optionalValue$com$google$common$base$Optional) {
        result.optionalValue = value$optionalValue$com$google$common$base$Optional;
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new java.lang.reflect.UndeclaredThrowableException(ex);
    }
  }
}
