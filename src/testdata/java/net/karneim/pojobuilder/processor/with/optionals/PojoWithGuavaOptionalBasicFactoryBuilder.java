package net.karneim.pojobuilder.processor.with.optionals;

import com.google.common.base.Optional;
import java.util.List;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class PojoWithGuavaOptionalBasicFactoryBuilder
    implements Cloneable {
  protected PojoWithGuavaOptionalBasicFactoryBuilder self;
  protected Optional<? extends Integer> value$primitiveInt$int = Optional.absent();
  protected Optional<? extends Integer> value$boxedInt$java$lang$Integer = Optional.absent();
  protected Optional<? extends int[]> value$array$int$L = Optional.absent();
  protected Optional<? extends List<Integer>> value$list$java$util$List = Optional.absent();

  /**
   * Creates a new {@link PojoWithGuavaOptionalBasicFactoryBuilder}.
   */
  public PojoWithGuavaOptionalBasicFactoryBuilder() {
    self = (PojoWithGuavaOptionalBasicFactoryBuilder)this;
  }

  /**
   * Sets the default value for the primitiveInt property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicFactoryBuilder withPrimitiveInt(int value) {
    this.value$primitiveInt$int = Optional.of(value);
    return self;
  }

  /**
   * Optionally sets the default value for the primitiveInt property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicFactoryBuilder withPrimitiveInt(Optional<? extends Integer> optionalValue) {
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
  public PojoWithGuavaOptionalBasicFactoryBuilder withBoxedInt(Integer value) {
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
  public PojoWithGuavaOptionalBasicFactoryBuilder withBoxedInt(Optional<? extends Integer> optionalValue) {
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
  public PojoWithGuavaOptionalBasicFactoryBuilder withArray(int[] value) {
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
  public PojoWithGuavaOptionalBasicFactoryBuilder withArray(Optional<? extends int[]> optionalValue) {
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
  public PojoWithGuavaOptionalBasicFactoryBuilder withList(List<Integer> value) {
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
  public PojoWithGuavaOptionalBasicFactoryBuilder withList(Optional<? extends List<Integer>> optionalValue) {
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
      PojoWithGuavaOptionalBasicFactoryBuilder result = (PojoWithGuavaOptionalBasicFactoryBuilder)super.clone();
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
  public PojoWithGuavaOptionalBasicFactoryBuilder but() {
    return (PojoWithGuavaOptionalBasicFactoryBuilder)clone();
  }

  /**
   * Creates a new {@link PojoWithGuavaOptionalBasicFactory} based on this builder's settings.
   *
   * @return the created PojoWithGuavaOptionalBasicFactory
   */
  public PojoWithGuavaOptionalBasicFactory build() {
    try {
      int _primitiveInt = 0;
      if (value$primitiveInt$int.isPresent()) {
        _primitiveInt = value$primitiveInt$int.get();
      }
      Integer _boxedInt = null;
      if (value$boxedInt$java$lang$Integer != null && value$boxedInt$java$lang$Integer.isPresent()) {
        _boxedInt = value$boxedInt$java$lang$Integer.get();
      }
      int[] _array = null;
      if (value$array$int$L != null && value$array$int$L.isPresent()) {
        _array = value$array$int$L.get();
      }
      List<Integer> _list = null;
      if (value$list$java$util$List != null && value$list$java$util$List.isPresent()) {
        _list = value$list$java$util$List.get();
      }
      PojoWithGuavaOptionalBasicFactory result = PojoWithGuavaOptionalBasicFactory.create(_primitiveInt, _boxedInt, _array, _list);
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
