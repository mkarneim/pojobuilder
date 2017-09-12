package net.karneim.pojobuilder.processor.with.optionals;

import com.google.common.base.Optional;
import java.util.List;
import javax.annotation.Generated;
import net.karneim.pojobuilder.processor.with.builderinterface.Builder;

@Generated("PojoBuilder")
public class PojoWithGuavaOptionalOptionalSettersWithBuilderPropertiesBuilder
    implements Builder<PojoWithGuavaOptionalOptionalSettersWithBuilderProperties>, Cloneable {
  protected PojoWithGuavaOptionalOptionalSettersWithBuilderPropertiesBuilder self;
  protected Optional<Integer> value$boxedInt$com$google$common$base$Optional = Optional.absent();
  protected Builder<? extends Integer> builder$boxedInt$com$google$common$base$Optional;
  protected Optional<int[]> value$array$com$google$common$base$Optional = Optional.absent();
  protected Builder<? extends int[]> builder$array$com$google$common$base$Optional;
  protected Optional<List<Integer>> value$list$com$google$common$base$Optional = Optional.absent();
  protected Builder<? extends List<Integer>> builder$list$com$google$common$base$Optional;

  /**
   * Creates a new {@link PojoWithGuavaOptionalOptionalSettersWithBuilderPropertiesBuilder}.
   */
  public PojoWithGuavaOptionalOptionalSettersWithBuilderPropertiesBuilder() {
    self = (PojoWithGuavaOptionalOptionalSettersWithBuilderPropertiesBuilder)this;
  }

  /**
   * Sets the default value for the {@link PojoWithGuavaOptionalOptionalSettersWithBuilderProperties#boxedInt} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalSettersWithBuilderPropertiesBuilder withBoxedInt(Integer value) {
    if (value == null) {
      this.value$boxedInt$com$google$common$base$Optional = null;
    } else {
      this.value$boxedInt$com$google$common$base$Optional = Optional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithGuavaOptionalOptionalSettersWithBuilderProperties#boxedInt} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalSettersWithBuilderPropertiesBuilder withBoxedInt(Optional<Integer> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$boxedInt$com$google$common$base$Optional = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default builder for the {@link PojoWithGuavaOptionalOptionalSettersWithBuilderProperties#boxedInt} property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalSettersWithBuilderPropertiesBuilder withBoxedInt(Builder<? extends Integer> builder) {
    this.builder$boxedInt$com$google$common$base$Optional = builder;
    this.value$boxedInt$com$google$common$base$Optional = Optional.absent();
    return self;
  }

  /**
   * Sets the default value for the {@link PojoWithGuavaOptionalOptionalSettersWithBuilderProperties#array} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalSettersWithBuilderPropertiesBuilder withArray(int[] value) {
    if (value == null) {
      this.value$array$com$google$common$base$Optional = null;
    } else {
      this.value$array$com$google$common$base$Optional = Optional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithGuavaOptionalOptionalSettersWithBuilderProperties#array} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalSettersWithBuilderPropertiesBuilder withArray(Optional<int[]> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$array$com$google$common$base$Optional = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default builder for the {@link PojoWithGuavaOptionalOptionalSettersWithBuilderProperties#array} property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalSettersWithBuilderPropertiesBuilder withArray(Builder<? extends int[]> builder) {
    this.builder$array$com$google$common$base$Optional = builder;
    this.value$array$com$google$common$base$Optional = Optional.absent();
    return self;
  }

  /**
   * Sets the default value for the {@link PojoWithGuavaOptionalOptionalSettersWithBuilderProperties#list} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalSettersWithBuilderPropertiesBuilder withList(List<Integer> value) {
    if (value == null) {
      this.value$list$com$google$common$base$Optional = null;
    } else {
      this.value$list$com$google$common$base$Optional = Optional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithGuavaOptionalOptionalSettersWithBuilderProperties#list} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalSettersWithBuilderPropertiesBuilder withList(Optional<List<Integer>> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$list$com$google$common$base$Optional = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default builder for the {@link PojoWithGuavaOptionalOptionalSettersWithBuilderProperties#list} property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalSettersWithBuilderPropertiesBuilder withList(Builder<? extends List<Integer>> builder) {
    this.builder$list$com$google$common$base$Optional = builder;
    this.value$list$com$google$common$base$Optional = Optional.absent();
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
      PojoWithGuavaOptionalOptionalSettersWithBuilderPropertiesBuilder result = (PojoWithGuavaOptionalOptionalSettersWithBuilderPropertiesBuilder)super.clone();
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
  public PojoWithGuavaOptionalOptionalSettersWithBuilderPropertiesBuilder but() {
    return (PojoWithGuavaOptionalOptionalSettersWithBuilderPropertiesBuilder)clone();
  }

  /**
   * Creates a new {@link PojoWithGuavaOptionalOptionalSettersWithBuilderProperties} based on this builder's settings.
   *
   * @return the created PojoWithGuavaOptionalOptionalSettersWithBuilderProperties
   */
  @Override
  public PojoWithGuavaOptionalOptionalSettersWithBuilderProperties build() {
    try {
      PojoWithGuavaOptionalOptionalSettersWithBuilderProperties result = new PojoWithGuavaOptionalOptionalSettersWithBuilderProperties();
      if (value$boxedInt$com$google$common$base$Optional == null || value$boxedInt$com$google$common$base$Optional.isPresent()) {
        result.setBoxedInt(value$boxedInt$com$google$common$base$Optional);
      } else if (builder$boxedInt$com$google$common$base$Optional != null) {
        Integer _boxedInt = builder$boxedInt$com$google$common$base$Optional.build();
        if (_boxedInt == null) {
          result.setBoxedInt((Optional<Integer>) null);
        } else {
          result.setBoxedInt(Optional.of(_boxedInt));
        }
      }
      if (value$array$com$google$common$base$Optional == null || value$array$com$google$common$base$Optional.isPresent()) {
        result.setArray(value$array$com$google$common$base$Optional);
      } else if (builder$array$com$google$common$base$Optional != null) {
        int[] _array = builder$array$com$google$common$base$Optional.build();
        if (_array == null) {
          result.setArray((Optional<int[]>) null);
        } else {
          result.setArray(Optional.of(_array));
        }
      }
      if (value$list$com$google$common$base$Optional == null || value$list$com$google$common$base$Optional.isPresent()) {
        result.setList(value$list$com$google$common$base$Optional);
      } else if (builder$list$com$google$common$base$Optional != null) {
        List<Integer> _list = builder$list$com$google$common$base$Optional.build();
        if (_list == null) {
          result.setList((Optional<List<Integer>>) null);
        } else {
          result.setList(Optional.of(_list));
        }
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new java.lang.reflect.UndeclaredThrowableException(ex);
    }
  }
}
