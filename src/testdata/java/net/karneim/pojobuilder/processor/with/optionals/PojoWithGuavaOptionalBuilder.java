package net.karneim.pojobuilder.processor.with.optionals;

import com.google.common.base.Optional;
import javax.annotation.Generated;

@Generated("PojoBuilder")
public class PojoWithGuavaOptionalBuilder
    implements Cloneable {
  protected PojoWithGuavaOptionalBuilder self;
  protected int value$primitiveInt$int;
  protected boolean isSet$primitiveInt$int;
  protected Integer value$boxedInt$java$lang$Integer;
  protected boolean isSet$boxedInt$java$lang$Integer;
  protected int[] value$array$int$L;
  protected boolean isSet$array$int$L;

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
    this.value$primitiveInt$int = value;
    this.isSet$primitiveInt$int = true;
    return self;
  }

  /**
   * Sets the default value for the {@link PojoWithGuavaOptional#boxedInt} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBuilder withBoxedInt(Integer value) {
    this.value$boxedInt$java$lang$Integer = value;
    this.isSet$boxedInt$java$lang$Integer = true;
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithGuavaOptional#boxedInt} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBuilder withBoxedInt(Optional<Integer> optionalValue) {
    return optionalValue.isPresent()?withBoxedInt(optionalValue.get()):self;
  }

  /**
   * Sets the default value for the {@link PojoWithGuavaOptional#array} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBuilder withArray(int[] value) {
    this.value$array$int$L = value;
    this.isSet$array$int$L = true;
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithGuavaOptional#array} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBuilder withArray(Optional<int[]> optionalValue) {
    return optionalValue.isPresent()?withArray(optionalValue.get()):self;
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
      if (isSet$primitiveInt$int) {
        result.primitiveInt = value$primitiveInt$int;
      }
      if (isSet$boxedInt$java$lang$Integer) {
        result.boxedInt = value$boxedInt$java$lang$Integer;
      }
      if (isSet$array$int$L) {
        result.array = value$array$int$L;
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Throwable t) {
      throw new java.lang.reflect.UndeclaredThrowableException(t);
    }
  }
}
