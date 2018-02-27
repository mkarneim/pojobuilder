package net.karneim.pojobuilder.processor.with.optionals;

import java.util.List;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class PojoWithOtherOptionalBasicFieldAccessBuilder
    implements Cloneable {
  protected PojoWithOtherOptionalBasicFieldAccessBuilder self;
  protected OtherOptional<? extends Integer> value$primitiveInt$int = OtherOptional.empty();
  protected OtherOptional<? extends Integer> value$boxedInt$java$lang$Integer = OtherOptional.empty();
  protected OtherOptional<? extends int[]> value$array$int$L = OtherOptional.empty();
  protected OtherOptional<? extends List<Integer>> value$list$java$util$List = OtherOptional.empty();

  /**
   * Creates a new {@link PojoWithOtherOptionalBasicFieldAccessBuilder}.
   */
  public PojoWithOtherOptionalBasicFieldAccessBuilder() {
    self = (PojoWithOtherOptionalBasicFieldAccessBuilder)this;
  }

  /**
   * Sets the default value for the {@link PojoWithOtherOptionalBasicFieldAccess#primitiveInt} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithOtherOptionalBasicFieldAccessBuilder withPrimitiveInt(int value) {
    this.value$primitiveInt$int = OtherOptional.of(value);
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithOtherOptionalBasicFieldAccess#primitiveInt} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithOtherOptionalBasicFieldAccessBuilder withPrimitiveInt(OtherOptional<? extends Integer> optionalValue) {
    if (optionalValue.isPresent()) {
      this.value$primitiveInt$int = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default value for the {@link PojoWithOtherOptionalBasicFieldAccess#boxedInt} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithOtherOptionalBasicFieldAccessBuilder withBoxedInt(Integer value) {
    if (value == null) {
      this.value$boxedInt$java$lang$Integer = null;
    } else {
      this.value$boxedInt$java$lang$Integer = OtherOptional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithOtherOptionalBasicFieldAccess#boxedInt} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithOtherOptionalBasicFieldAccessBuilder withBoxedInt(OtherOptional<? extends Integer> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$boxedInt$java$lang$Integer = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default value for the {@link PojoWithOtherOptionalBasicFieldAccess#array} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithOtherOptionalBasicFieldAccessBuilder withArray(int[] value) {
    if (value == null) {
      this.value$array$int$L = null;
    } else {
      this.value$array$int$L = OtherOptional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithOtherOptionalBasicFieldAccess#array} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithOtherOptionalBasicFieldAccessBuilder withArray(OtherOptional<? extends int[]> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$array$int$L = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default value for the {@link PojoWithOtherOptionalBasicFieldAccess#list} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithOtherOptionalBasicFieldAccessBuilder withList(List<Integer> value) {
    if (value == null) {
      this.value$list$java$util$List = null;
    } else {
      this.value$list$java$util$List = OtherOptional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithOtherOptionalBasicFieldAccess#list} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithOtherOptionalBasicFieldAccessBuilder withList(OtherOptional<? extends List<Integer>> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$list$java$util$List = optionalValue;
    }
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
      PojoWithOtherOptionalBasicFieldAccessBuilder result = (PojoWithOtherOptionalBasicFieldAccessBuilder)super.clone();
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
  public PojoWithOtherOptionalBasicFieldAccessBuilder but() {
    return (PojoWithOtherOptionalBasicFieldAccessBuilder)clone();
  }

  /**
   * Creates a new {@link PojoWithOtherOptionalBasicFieldAccess} based on this builder's settings.
   *
   * @return the created PojoWithOtherOptionalBasicFieldAccess
   */
  public PojoWithOtherOptionalBasicFieldAccess build() {
    try {
      PojoWithOtherOptionalBasicFieldAccess result = new PojoWithOtherOptionalBasicFieldAccess();
      if (value$primitiveInt$int.isPresent()) {
        result.primitiveInt = value$primitiveInt$int.get();
      }
      if (value$boxedInt$java$lang$Integer == null) {
        result.boxedInt = (Integer) null;
      } else if (value$boxedInt$java$lang$Integer.isPresent()) {
        result.boxedInt = value$boxedInt$java$lang$Integer.get();
      }
      if (value$array$int$L == null) {
        result.array = (int[]) null;
      } else if (value$array$int$L.isPresent()) {
        result.array = value$array$int$L.get();
      }
      if (value$list$java$util$List == null) {
        result.list = (List<Integer>) null;
      } else if (value$list$java$util$List.isPresent()) {
        result.list = value$list$java$util$List.get();
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
