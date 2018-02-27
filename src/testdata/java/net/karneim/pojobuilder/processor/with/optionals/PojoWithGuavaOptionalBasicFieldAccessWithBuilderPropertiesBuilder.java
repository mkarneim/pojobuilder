package net.karneim.pojobuilder.processor.with.optionals;

import com.google.common.base.Optional;
import java.util.List;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;
import net.karneim.pojobuilder.processor.with.builderinterface.Builder;

@Generated("PojoBuilder")
public class PojoWithGuavaOptionalBasicFieldAccessWithBuilderPropertiesBuilder
    implements Builder<PojoWithGuavaOptionalBasicFieldAccessWithBuilderProperties>, Cloneable {
  protected PojoWithGuavaOptionalBasicFieldAccessWithBuilderPropertiesBuilder self;
  protected Optional<? extends Integer> value$primitiveInt$int = Optional.absent();
  protected Builder<? extends Integer> builder$primitiveInt$int;
  protected Optional<? extends Integer> value$boxedInt$java$lang$Integer = Optional.absent();
  protected Builder<? extends Integer> builder$boxedInt$java$lang$Integer;
  protected Optional<? extends int[]> value$array$int$L = Optional.absent();
  protected Builder<? extends int[]> builder$array$int$L;
  protected Optional<? extends List<Integer>> value$list$java$util$List = Optional.absent();
  protected Builder<? extends List<Integer>> builder$list$java$util$List;

  /**
   * Creates a new {@link PojoWithGuavaOptionalBasicFieldAccessWithBuilderPropertiesBuilder}.
   */
  public PojoWithGuavaOptionalBasicFieldAccessWithBuilderPropertiesBuilder() {
    self = (PojoWithGuavaOptionalBasicFieldAccessWithBuilderPropertiesBuilder)this;
  }

  /**
   * Sets the default value for the {@link PojoWithGuavaOptionalBasicFieldAccessWithBuilderProperties#primitiveInt} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicFieldAccessWithBuilderPropertiesBuilder withPrimitiveInt(int value) {
    this.value$primitiveInt$int = Optional.of(value);
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithGuavaOptionalBasicFieldAccessWithBuilderProperties#primitiveInt} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicFieldAccessWithBuilderPropertiesBuilder withPrimitiveInt(Optional<? extends Integer> optionalValue) {
    if (optionalValue.isPresent()) {
      this.value$primitiveInt$int = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default builder for the {@link PojoWithGuavaOptionalBasicFieldAccessWithBuilderProperties#primitiveInt} property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicFieldAccessWithBuilderPropertiesBuilder withPrimitiveInt(Builder<? extends Integer> builder) {
    this.builder$primitiveInt$int = builder;
    this.value$primitiveInt$int = Optional.absent();
    return self;
  }

  /**
   * Sets the default value for the {@link PojoWithGuavaOptionalBasicFieldAccessWithBuilderProperties#boxedInt} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicFieldAccessWithBuilderPropertiesBuilder withBoxedInt(Integer value) {
    if (value == null) {
      this.value$boxedInt$java$lang$Integer = null;
    } else {
      this.value$boxedInt$java$lang$Integer = Optional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithGuavaOptionalBasicFieldAccessWithBuilderProperties#boxedInt} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicFieldAccessWithBuilderPropertiesBuilder withBoxedInt(Optional<? extends Integer> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$boxedInt$java$lang$Integer = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default builder for the {@link PojoWithGuavaOptionalBasicFieldAccessWithBuilderProperties#boxedInt} property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicFieldAccessWithBuilderPropertiesBuilder withBoxedInt(Builder<? extends Integer> builder) {
    this.builder$boxedInt$java$lang$Integer = builder;
    this.value$boxedInt$java$lang$Integer = Optional.absent();
    return self;
  }

  /**
   * Sets the default value for the {@link PojoWithGuavaOptionalBasicFieldAccessWithBuilderProperties#array} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicFieldAccessWithBuilderPropertiesBuilder withArray(int[] value) {
    if (value == null) {
      this.value$array$int$L = null;
    } else {
      this.value$array$int$L = Optional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithGuavaOptionalBasicFieldAccessWithBuilderProperties#array} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicFieldAccessWithBuilderPropertiesBuilder withArray(Optional<? extends int[]> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$array$int$L = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default builder for the {@link PojoWithGuavaOptionalBasicFieldAccessWithBuilderProperties#array} property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicFieldAccessWithBuilderPropertiesBuilder withArray(Builder<? extends int[]> builder) {
    this.builder$array$int$L = builder;
    this.value$array$int$L = Optional.absent();
    return self;
  }

  /**
   * Sets the default value for the {@link PojoWithGuavaOptionalBasicFieldAccessWithBuilderProperties#list} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicFieldAccessWithBuilderPropertiesBuilder withList(List<Integer> value) {
    if (value == null) {
      this.value$list$java$util$List = null;
    } else {
      this.value$list$java$util$List = Optional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithGuavaOptionalBasicFieldAccessWithBuilderProperties#list} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicFieldAccessWithBuilderPropertiesBuilder withList(Optional<? extends List<Integer>> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$list$java$util$List = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default builder for the {@link PojoWithGuavaOptionalBasicFieldAccessWithBuilderProperties#list} property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public PojoWithGuavaOptionalBasicFieldAccessWithBuilderPropertiesBuilder withList(Builder<? extends List<Integer>> builder) {
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
      PojoWithGuavaOptionalBasicFieldAccessWithBuilderPropertiesBuilder result = (PojoWithGuavaOptionalBasicFieldAccessWithBuilderPropertiesBuilder)super.clone();
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
  public PojoWithGuavaOptionalBasicFieldAccessWithBuilderPropertiesBuilder but() {
    return (PojoWithGuavaOptionalBasicFieldAccessWithBuilderPropertiesBuilder)clone();
  }

  /**
   * Creates a new {@link PojoWithGuavaOptionalBasicFieldAccessWithBuilderProperties} based on this builder's settings.
   *
   * @return the created PojoWithGuavaOptionalBasicFieldAccessWithBuilderProperties
   */
  @Override
  public PojoWithGuavaOptionalBasicFieldAccessWithBuilderProperties build() {
    try {
      PojoWithGuavaOptionalBasicFieldAccessWithBuilderProperties result = new PojoWithGuavaOptionalBasicFieldAccessWithBuilderProperties();
      if (value$primitiveInt$int.isPresent()) {
        result.primitiveInt = value$primitiveInt$int.get();
      } else if (builder$primitiveInt$int != null) {
        result.primitiveInt = builder$primitiveInt$int.build();
      }
      if (value$boxedInt$java$lang$Integer == null) {
        result.boxedInt = (Integer) null;
      } else if (value$boxedInt$java$lang$Integer.isPresent()) {
        result.boxedInt = value$boxedInt$java$lang$Integer.get();
      } else if (builder$boxedInt$java$lang$Integer != null) {
        result.boxedInt = builder$boxedInt$java$lang$Integer.build();
      }
      if (value$array$int$L == null) {
        result.array = (int[]) null;
      } else if (value$array$int$L.isPresent()) {
        result.array = value$array$int$L.get();
      } else if (builder$array$int$L != null) {
        result.array = builder$array$int$L.build();
      }
      if (value$list$java$util$List == null) {
        result.list = (List<Integer>) null;
      } else if (value$list$java$util$List.isPresent()) {
        result.list = value$list$java$util$List.get();
      } else if (builder$list$java$util$List != null) {
        result.list = builder$list$java$util$List.build();
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
