package net.karneim.pojobuilder.processor.with.optionals;

import com.google.common.base.Optional;
import java.util.List;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;
import net.karneim.pojobuilder.processor.with.builderinterface.Builder;

@Generated("PojoBuilder")
public class PojoWithGuavaOptionalBasicSettersWithBuilderPropertiesBuilder
    implements Builder<PojoWithGuavaOptionalBasicSettersWithBuilderProperties>, Cloneable {
  protected PojoWithGuavaOptionalBasicSettersWithBuilderPropertiesBuilder self;
  protected Optional<? extends Integer> value$primitiveInt$int = Optional.absent();
  protected Builder<? extends Integer> builder$primitiveInt$int;
  protected Optional<? extends Integer> value$boxedInt$java$lang$Integer = Optional.absent();
  protected Builder<? extends Integer> builder$boxedInt$java$lang$Integer;
  protected Optional<? extends int[]> value$array$int$L = Optional.absent();
  protected Builder<? extends int[]> builder$array$int$L;
  protected Optional<? extends List<Integer>> value$list$java$util$List = Optional.absent();
  protected Builder<? extends List<Integer>> builder$list$java$util$List;

  /**
   * Creates a new {@link PojoWithGuavaOptionalBasicSettersWithBuilderPropertiesBuilder}.
   */
  public PojoWithGuavaOptionalBasicSettersWithBuilderPropertiesBuilder() {
    self = (PojoWithGuavaOptionalBasicSettersWithBuilderPropertiesBuilder)this;
  }

  /**
   * Sets the default value for the primitiveInt property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicSettersWithBuilderPropertiesBuilder withPrimitiveInt(int value) {
    this.value$primitiveInt$int = Optional.of(value);
    return self;
  }

  /**
   * Optionally sets the default value for the primitiveInt property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicSettersWithBuilderPropertiesBuilder withPrimitiveInt(Optional<? extends Integer> optionalValue) {
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
  public PojoWithGuavaOptionalBasicSettersWithBuilderPropertiesBuilder withPrimitiveInt(Builder<? extends Integer> builder) {
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
  public PojoWithGuavaOptionalBasicSettersWithBuilderPropertiesBuilder withBoxedInt(Integer value) {
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
  public PojoWithGuavaOptionalBasicSettersWithBuilderPropertiesBuilder withBoxedInt(Optional<? extends Integer> optionalValue) {
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
  public PojoWithGuavaOptionalBasicSettersWithBuilderPropertiesBuilder withBoxedInt(Builder<? extends Integer> builder) {
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
  public PojoWithGuavaOptionalBasicSettersWithBuilderPropertiesBuilder withArray(int[] value) {
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
  public PojoWithGuavaOptionalBasicSettersWithBuilderPropertiesBuilder withArray(Optional<? extends int[]> optionalValue) {
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
  public PojoWithGuavaOptionalBasicSettersWithBuilderPropertiesBuilder withArray(Builder<? extends int[]> builder) {
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
  public PojoWithGuavaOptionalBasicSettersWithBuilderPropertiesBuilder withList(List<Integer> value) {
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
  public PojoWithGuavaOptionalBasicSettersWithBuilderPropertiesBuilder withList(Optional<? extends List<Integer>> optionalValue) {
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
  public PojoWithGuavaOptionalBasicSettersWithBuilderPropertiesBuilder withList(Builder<? extends List<Integer>> builder) {
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
      PojoWithGuavaOptionalBasicSettersWithBuilderPropertiesBuilder result = (PojoWithGuavaOptionalBasicSettersWithBuilderPropertiesBuilder)super.clone();
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
  public PojoWithGuavaOptionalBasicSettersWithBuilderPropertiesBuilder but() {
    return (PojoWithGuavaOptionalBasicSettersWithBuilderPropertiesBuilder)clone();
  }

  /**
   * Creates a new {@link PojoWithGuavaOptionalBasicSettersWithBuilderProperties} based on this builder's settings.
   *
   * @return the created PojoWithGuavaOptionalBasicSettersWithBuilderProperties
   */
  @Override
  public PojoWithGuavaOptionalBasicSettersWithBuilderProperties build() {
    try {
      PojoWithGuavaOptionalBasicSettersWithBuilderProperties result = new PojoWithGuavaOptionalBasicSettersWithBuilderProperties();
      if (value$primitiveInt$int.isPresent()) {
        result.setPrimitiveInt(value$primitiveInt$int.get());
      } else if (builder$primitiveInt$int != null) {
        result.setPrimitiveInt(builder$primitiveInt$int.build());
      }
      if (value$boxedInt$java$lang$Integer == null) {
        result.setBoxedInt((Integer) null);
      } else if (value$boxedInt$java$lang$Integer.isPresent()) {
        result.setBoxedInt(value$boxedInt$java$lang$Integer.get());
      } else if (builder$boxedInt$java$lang$Integer != null) {
        result.setBoxedInt(builder$boxedInt$java$lang$Integer.build());
      }
      if (value$array$int$L == null) {
        result.setArray((int[]) null);
      } else if (value$array$int$L.isPresent()) {
        result.setArray(value$array$int$L.get());
      } else if (builder$array$int$L != null) {
        result.setArray(builder$array$int$L.build());
      }
      if (value$list$java$util$List == null) {
        result.setList((List<Integer>) null);
      } else if (value$list$java$util$List.isPresent()) {
        result.setList(value$list$java$util$List.get());
      } else if (builder$list$java$util$List != null) {
        result.setList(builder$list$java$util$List.build());
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
