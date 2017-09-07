package net.karneim.pojobuilder.processor.with.optionals;

import com.google.common.base.Optional;
import javax.annotation.Generated;

@Generated("PojoBuilder")
public class PojoWithGuavaOptional2Builder
    implements Cloneable {
  protected PojoWithGuavaOptional2Builder self;
  protected Optional<Integer> value$boxedInt$com$google$common$base$Optional = Optional.absent();
  protected Optional<int[]> value$array$com$google$common$base$Optional = Optional.absent();

  /**
   * Creates a new {@link PojoWithGuavaOptional2Builder}.
   */
  public PojoWithGuavaOptional2Builder() {
    self = (PojoWithGuavaOptional2Builder)this;
  }

  /**
   * Sets the default value for the {@link PojoWithGuavaOptional2#boxedInt} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptional2Builder withBoxedInt(Integer value) {
    if (value == null) {
      this.value$boxedInt$com$google$common$base$Optional = null;
    } else {
      this.value$boxedInt$com$google$common$base$Optional = Optional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithGuavaOptional2#boxedInt} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptional2Builder withBoxedInt(Optional<Integer> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$boxedInt$com$google$common$base$Optional = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default value for the {@link PojoWithGuavaOptional2#array} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptional2Builder withArray(int[] value) {
    if (value == null) {
      this.value$array$com$google$common$base$Optional = null;
    } else {
      this.value$array$com$google$common$base$Optional = Optional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithGuavaOptional2#array} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptional2Builder withArray(Optional<int[]> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$array$com$google$common$base$Optional = optionalValue;
    }
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
      if (value$boxedInt$com$google$common$base$Optional == null || value$boxedInt$com$google$common$base$Optional.isPresent()) {
        result.boxedInt = value$boxedInt$com$google$common$base$Optional;
      }
      if (value$array$com$google$common$base$Optional == null || value$array$com$google$common$base$Optional.isPresent()) {
        result.array = value$array$com$google$common$base$Optional;
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new java.lang.reflect.UndeclaredThrowableException(ex);
    }
  }
}
