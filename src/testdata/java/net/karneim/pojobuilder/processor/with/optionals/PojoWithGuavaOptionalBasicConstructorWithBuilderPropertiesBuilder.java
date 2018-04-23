package net.karneim.pojobuilder.processor.with.optionals;

import com.google.common.base.Optional;
import java.util.List;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;
import net.karneim.pojobuilder.processor.with.builderinterface.Builder;

@Generated("PojoBuilder")
public class PojoWithGuavaOptionalBasicConstructorWithBuilderPropertiesBuilder
    implements Builder<PojoWithGuavaOptionalBasicConstructorWithBuilderProperties>, Cloneable {
  protected PojoWithGuavaOptionalBasicConstructorWithBuilderPropertiesBuilder self;
  protected Optional<? extends Integer> value$primitiveInt$int = Optional.absent();
  protected Builder<? extends Integer> builder$primitiveInt$int;
  protected Optional<? extends Integer> value$boxedInt$java$lang$Integer = Optional.absent();
  protected Builder<? extends Integer> builder$boxedInt$java$lang$Integer;
  protected Optional<? extends int[]> value$array$int$L = Optional.absent();
  protected Builder<? extends int[]> builder$array$int$L;
  protected Optional<? extends List<Integer>> value$list$java$util$List = Optional.absent();
  protected Builder<? extends List<Integer>> builder$list$java$util$List;

  /**
   * Creates a new {@link PojoWithGuavaOptionalBasicConstructorWithBuilderPropertiesBuilder}.
   */
  public PojoWithGuavaOptionalBasicConstructorWithBuilderPropertiesBuilder() {
    self = (PojoWithGuavaOptionalBasicConstructorWithBuilderPropertiesBuilder)this;
  }

  /**
   * Sets the default value for the primitiveInt property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicConstructorWithBuilderPropertiesBuilder withPrimitiveInt(int value) {
    this.value$primitiveInt$int = Optional.of(value);
    return self;
  }

  /**
   * Optionally sets the default value for the primitiveInt property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicConstructorWithBuilderPropertiesBuilder withPrimitiveInt(Optional<? extends Integer> optionalValue) {
    if (optionalValue.isPresent()) {
      this.value$primitiveInt$int = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default builder for the primitiveInt property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicConstructorWithBuilderPropertiesBuilder withPrimitiveInt(Builder<? extends Integer> builder) {
    this.builder$primitiveInt$int = builder;
    this.value$primitiveInt$int = Optional.absent();
    return self;
  }

  /**
   * Sets the default value for the boxedInt property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicConstructorWithBuilderPropertiesBuilder withBoxedInt(Integer value) {
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
  public PojoWithGuavaOptionalBasicConstructorWithBuilderPropertiesBuilder withBoxedInt(Optional<? extends Integer> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$boxedInt$java$lang$Integer = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default builder for the boxedInt property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicConstructorWithBuilderPropertiesBuilder withBoxedInt(Builder<? extends Integer> builder) {
    this.builder$boxedInt$java$lang$Integer = builder;
    this.value$boxedInt$java$lang$Integer = Optional.absent();
    return self;
  }

  /**
   * Sets the default value for the array property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicConstructorWithBuilderPropertiesBuilder withArray(int[] value) {
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
  public PojoWithGuavaOptionalBasicConstructorWithBuilderPropertiesBuilder withArray(Optional<? extends int[]> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$array$int$L = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default builder for the array property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicConstructorWithBuilderPropertiesBuilder withArray(Builder<? extends int[]> builder) {
    this.builder$array$int$L = builder;
    this.value$array$int$L = Optional.absent();
    return self;
  }

  /**
   * Sets the default value for the list property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicConstructorWithBuilderPropertiesBuilder withList(List<Integer> value) {
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
  public PojoWithGuavaOptionalBasicConstructorWithBuilderPropertiesBuilder withList(Optional<? extends List<Integer>> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$list$java$util$List = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default builder for the list property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicConstructorWithBuilderPropertiesBuilder withList(Builder<? extends List<Integer>> builder) {
    this.builder$list$java$util$List = builder;
    this.value$list$java$util$List = Optional.absent();
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
      PojoWithGuavaOptionalBasicConstructorWithBuilderPropertiesBuilder result = (PojoWithGuavaOptionalBasicConstructorWithBuilderPropertiesBuilder)super.clone();
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
  public PojoWithGuavaOptionalBasicConstructorWithBuilderPropertiesBuilder but() {
    return (PojoWithGuavaOptionalBasicConstructorWithBuilderPropertiesBuilder)clone();
  }

  /**
   * Creates a new {@link PojoWithGuavaOptionalBasicConstructorWithBuilderProperties} based on this builder's settings.
   *
   * @return the created PojoWithGuavaOptionalBasicConstructorWithBuilderProperties
   */
  @Override
  public PojoWithGuavaOptionalBasicConstructorWithBuilderProperties build() {
    try {
      int _primitiveInt = 0;
      if (value$primitiveInt$int.isPresent()) {
        _primitiveInt = value$primitiveInt$int.get();
      } else if (builder$primitiveInt$int != null) {
        _primitiveInt = builder$primitiveInt$int.build();
      }
      Integer _boxedInt = null;
      if (value$boxedInt$java$lang$Integer != null) {
        if (value$boxedInt$java$lang$Integer.isPresent()) {
          _boxedInt = value$boxedInt$java$lang$Integer.get();
        } else if (builder$boxedInt$java$lang$Integer != null) {
          _boxedInt = builder$boxedInt$java$lang$Integer.build();
        }
      }
      int[] _array = null;
      if (value$array$int$L != null) {
        if (value$array$int$L.isPresent()) {
          _array = value$array$int$L.get();
        } else if (builder$array$int$L != null) {
          _array = builder$array$int$L.build();
        }
      }
      List<Integer> _list = null;
      if (value$list$java$util$List != null) {
        if (value$list$java$util$List.isPresent()) {
          _list = value$list$java$util$List.get();
        } else if (builder$list$java$util$List != null) {
          _list = builder$list$java$util$List.build();
        }
      }
      PojoWithGuavaOptionalBasicConstructorWithBuilderProperties result = new PojoWithGuavaOptionalBasicConstructorWithBuilderProperties(_primitiveInt, _boxedInt, _array, _list);
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
