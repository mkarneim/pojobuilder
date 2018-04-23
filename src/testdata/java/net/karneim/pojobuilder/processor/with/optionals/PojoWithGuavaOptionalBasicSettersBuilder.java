package net.karneim.pojobuilder.processor.with.optionals;

import com.google.common.base.Optional;
import java.util.List;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class PojoWithGuavaOptionalBasicSettersBuilder
    implements Cloneable {
  protected PojoWithGuavaOptionalBasicSettersBuilder self;
  protected Optional<? extends Integer> value$primitiveInt$int = Optional.absent();
  protected Optional<? extends Integer> value$boxedInt$java$lang$Integer = Optional.absent();
  protected Optional<? extends int[]> value$array$int$L = Optional.absent();
  protected Optional<? extends List<Integer>> value$list$java$util$List = Optional.absent();

  /**
   * Creates a new {@link PojoWithGuavaOptionalBasicSettersBuilder}.
   */
  public PojoWithGuavaOptionalBasicSettersBuilder() {
    self = (PojoWithGuavaOptionalBasicSettersBuilder)this;
  }

  /**
   * Sets the default value for the primitiveInt property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicSettersBuilder withPrimitiveInt(int value) {
    this.value$primitiveInt$int = Optional.of(value);
    return self;
  }

  /**
   * Optionally sets the default value for the primitiveInt property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicSettersBuilder withPrimitiveInt(Optional<? extends Integer> optionalValue) {
    if (optionalValue.isPresent()) {
      this.value$primitiveInt$int = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default value for the boxedInt property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicSettersBuilder withBoxedInt(Integer value) {
    if (value == null) {
      this.value$boxedInt$java$lang$Integer = null;
    } else {
      this.value$boxedInt$java$lang$Integer = Optional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the boxedInt property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicSettersBuilder withBoxedInt(Optional<? extends Integer> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$boxedInt$java$lang$Integer = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default value for the array property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicSettersBuilder withArray(int[] value) {
    if (value == null) {
      this.value$array$int$L = null;
    } else {
      this.value$array$int$L = Optional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the array property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicSettersBuilder withArray(Optional<? extends int[]> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$array$int$L = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default value for the list property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicSettersBuilder withList(List<Integer> value) {
    if (value == null) {
      this.value$list$java$util$List = null;
    } else {
      this.value$list$java$util$List = Optional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the list property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicSettersBuilder withList(Optional<? extends List<Integer>> optionalValue) {
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
      PojoWithGuavaOptionalBasicSettersBuilder result = (PojoWithGuavaOptionalBasicSettersBuilder)super.clone();
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
  public PojoWithGuavaOptionalBasicSettersBuilder but() {
    return (PojoWithGuavaOptionalBasicSettersBuilder)clone();
  }

  /**
   * Creates a new {@link PojoWithGuavaOptionalBasicSetters} based on this builder's settings.
   *
   * @return the created PojoWithGuavaOptionalBasicSetters
   */
  public PojoWithGuavaOptionalBasicSetters build() {
    try {
      PojoWithGuavaOptionalBasicSetters result = new PojoWithGuavaOptionalBasicSetters();
      if (value$primitiveInt$int.isPresent()) {
        result.setPrimitiveInt(value$primitiveInt$int.get());
      }
      if (value$boxedInt$java$lang$Integer == null) {
        result.setBoxedInt((Integer) null);
      } else if (value$boxedInt$java$lang$Integer.isPresent()) {
        result.setBoxedInt(value$boxedInt$java$lang$Integer.get());
      }
      if (value$array$int$L == null) {
        result.setArray((int[]) null);
      } else if (value$array$int$L.isPresent()) {
        result.setArray(value$array$int$L.get());
      }
      if (value$list$java$util$List == null) {
        result.setList((List<Integer>) null);
      } else if (value$list$java$util$List.isPresent()) {
        result.setList(value$list$java$util$List.get());
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
