package net.karneim.pojobuilder.processor.with.optionals;

import com.google.common.base.Optional;
import java.util.List;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class PojoWithGuavaOptionalBasicFieldAccessBuilder
    implements Cloneable {
  protected PojoWithGuavaOptionalBasicFieldAccessBuilder self;
  protected Optional<? extends Integer> value$primitiveInt$int = Optional.absent();
  protected Optional<? extends Integer> value$boxedInt$java$lang$Integer = Optional.absent();
  protected Optional<? extends int[]> value$array$int$L = Optional.absent();
  protected Optional<? extends List<Integer>> value$list$java$util$List = Optional.absent();

  /**
   * Creates a new {@link PojoWithGuavaOptionalBasicFieldAccessBuilder}.
   */
  public PojoWithGuavaOptionalBasicFieldAccessBuilder() {
    self = (PojoWithGuavaOptionalBasicFieldAccessBuilder)this;
  }

  /**
   * Sets the default value for the {@link PojoWithGuavaOptionalBasicFieldAccess#primitiveInt} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicFieldAccessBuilder withPrimitiveInt(int value) {
    this.value$primitiveInt$int = Optional.of(value);
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithGuavaOptionalBasicFieldAccess#primitiveInt} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicFieldAccessBuilder withPrimitiveInt(Optional<? extends Integer> optionalValue) {
    if (optionalValue.isPresent()) {
      this.value$primitiveInt$int = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default value for the {@link PojoWithGuavaOptionalBasicFieldAccess#boxedInt} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicFieldAccessBuilder withBoxedInt(Integer value) {
    if (value == null) {
      this.value$boxedInt$java$lang$Integer = null;
    } else {
      this.value$boxedInt$java$lang$Integer = Optional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithGuavaOptionalBasicFieldAccess#boxedInt} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicFieldAccessBuilder withBoxedInt(Optional<? extends Integer> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$boxedInt$java$lang$Integer = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default value for the {@link PojoWithGuavaOptionalBasicFieldAccess#array} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicFieldAccessBuilder withArray(int[] value) {
    if (value == null) {
      this.value$array$int$L = null;
    } else {
      this.value$array$int$L = Optional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithGuavaOptionalBasicFieldAccess#array} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicFieldAccessBuilder withArray(Optional<? extends int[]> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$array$int$L = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default value for the {@link PojoWithGuavaOptionalBasicFieldAccess#list} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicFieldAccessBuilder withList(List<Integer> value) {
    if (value == null) {
      this.value$list$java$util$List = null;
    } else {
      this.value$list$java$util$List = Optional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithGuavaOptionalBasicFieldAccess#list} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicFieldAccessBuilder withList(Optional<? extends List<Integer>> optionalValue) {
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
      PojoWithGuavaOptionalBasicFieldAccessBuilder result = (PojoWithGuavaOptionalBasicFieldAccessBuilder)super.clone();
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
  public PojoWithGuavaOptionalBasicFieldAccessBuilder but() {
    return (PojoWithGuavaOptionalBasicFieldAccessBuilder)clone();
  }

  /**
   * Creates a new {@link PojoWithGuavaOptionalBasicFieldAccess} based on this builder's settings.
   *
   * @return the created PojoWithGuavaOptionalBasicFieldAccess
   */
  public PojoWithGuavaOptionalBasicFieldAccess build() {
    try {
      PojoWithGuavaOptionalBasicFieldAccess result = new PojoWithGuavaOptionalBasicFieldAccess();
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
