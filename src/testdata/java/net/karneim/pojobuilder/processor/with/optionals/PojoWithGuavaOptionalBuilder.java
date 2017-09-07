package net.karneim.pojobuilder.processor.with.optionals;

import com.google.common.base.Optional;
import javax.annotation.Generated;

@Generated("PojoBuilder")
public class PojoWithGuavaOptionalBuilder
    implements Cloneable {
  protected PojoWithGuavaOptionalBuilder self;
  protected Optional<? extends Integer> value$primitiveInt$int = Optional.absent();
  protected Optional<? extends Integer> value$boxedInt$java$lang$Integer = Optional.absent();
  protected Optional<? extends int[]> value$array$int$L = Optional.absent();

  /**
   * Creates a new {@link PojoWithGuavaOptionalBuilder}.
   */
  public PojoWithGuavaOptionalBuilder() {
    self = (PojoWithGuavaOptionalBuilder)this;
  }

  /**
   * Sets the default value for the {@link PojoWithGuavaOptional#primitiveInt} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBuilder withPrimitiveInt(int value) {
    this.value$primitiveInt$int = Optional.of(value);
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithGuavaOptional#primitiveInt} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBuilder withPrimitiveInt(Optional<? extends Integer> optionalValue) {
    if (optionalValue.isPresent()) {
      this.value$primitiveInt$int = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default value for the {@link PojoWithGuavaOptional#boxedInt} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBuilder withBoxedInt(Integer value) {
    if (value == null) {
      this.value$boxedInt$java$lang$Integer = null;
    } else {
      this.value$boxedInt$java$lang$Integer = Optional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithGuavaOptional#boxedInt} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBuilder withBoxedInt(Optional<? extends Integer> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$boxedInt$java$lang$Integer = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default value for the {@link PojoWithGuavaOptional#array} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBuilder withArray(int[] value) {
    if (value == null) {
      this.value$array$int$L = null;
    } else {
      this.value$array$int$L = Optional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithGuavaOptional#array} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBuilder withArray(Optional<? extends int[]> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$array$int$L = optionalValue;
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
      PojoWithGuavaOptionalBuilder result = (PojoWithGuavaOptionalBuilder)super.clone();
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
  public PojoWithGuavaOptionalBuilder but() {
    return (PojoWithGuavaOptionalBuilder)clone();
  }

  /**
   * Creates a new {@link PojoWithGuavaOptional} based on this builder's settings.
   *
   * @return the created PojoWithGuavaOptional
   */
  public PojoWithGuavaOptional build() {
    try {
      PojoWithGuavaOptional result = new PojoWithGuavaOptional();
      if (value$primitiveInt$int.isPresent()) {
        result.primitiveInt = value$primitiveInt$int.get();
      }
      if (value$boxedInt$java$lang$Integer == null) {
        result.boxedInt = null;
      } else if (value$boxedInt$java$lang$Integer.isPresent()) {
        result.boxedInt = value$boxedInt$java$lang$Integer.get();
      }
      if (value$array$int$L == null) {
        result.array = null;
      } else if (value$array$int$L.isPresent()) {
        result.array = value$array$int$L.get();
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new java.lang.reflect.UndeclaredThrowableException(ex);
    }
  }
}
