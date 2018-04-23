package net.karneim.pojobuilder.processor.with.optionals;

import com.google.common.base.Optional;
import java.util.List;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;
import net.karneim.pojobuilder.processor.with.builderinterface.Builder;

@Generated("PojoBuilder")
public class PojoWithGuavaOptionalOptionalConstructorWithBuilderPropertiesBuilder
    implements Builder<PojoWithGuavaOptionalOptionalConstructorWithBuilderProperties>, Cloneable {
  protected PojoWithGuavaOptionalOptionalConstructorWithBuilderPropertiesBuilder self;
  protected Optional<Integer> value$boxedInt$com$google$common$base$Optional = Optional.absent();
  protected Builder<? extends Integer> builder$boxedInt$com$google$common$base$Optional;
  protected Optional<int[]> value$array$com$google$common$base$Optional = Optional.absent();
  protected Builder<? extends int[]> builder$array$com$google$common$base$Optional;
  protected Optional<List<Integer>> value$list$com$google$common$base$Optional = Optional.absent();
  protected Builder<? extends List<Integer>> builder$list$com$google$common$base$Optional;

  /**
   * Creates a new {@link PojoWithGuavaOptionalOptionalConstructorWithBuilderPropertiesBuilder}.
   */
  public PojoWithGuavaOptionalOptionalConstructorWithBuilderPropertiesBuilder() {
    self = (PojoWithGuavaOptionalOptionalConstructorWithBuilderPropertiesBuilder)this;
  }

  /**
   * Sets the default value for the boxedInt property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalConstructorWithBuilderPropertiesBuilder withBoxedInt(Integer value) {
    if (value == null) {
      this.value$boxedInt$com$google$common$base$Optional = null;
    } else {
      this.value$boxedInt$com$google$common$base$Optional = Optional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the boxedInt property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalConstructorWithBuilderPropertiesBuilder withBoxedInt(Optional<Integer> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$boxedInt$com$google$common$base$Optional = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default builder for the boxedInt property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalConstructorWithBuilderPropertiesBuilder withBoxedInt(Builder<? extends Integer> builder) {
    this.builder$boxedInt$com$google$common$base$Optional = builder;
    this.value$boxedInt$com$google$common$base$Optional = Optional.absent();
    return self;
  }

  /**
   * Sets the default value for the array property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalConstructorWithBuilderPropertiesBuilder withArray(int[] value) {
    if (value == null) {
      this.value$array$com$google$common$base$Optional = null;
    } else {
      this.value$array$com$google$common$base$Optional = Optional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the array property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalConstructorWithBuilderPropertiesBuilder withArray(Optional<int[]> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$array$com$google$common$base$Optional = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default builder for the array property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalConstructorWithBuilderPropertiesBuilder withArray(Builder<? extends int[]> builder) {
    this.builder$array$com$google$common$base$Optional = builder;
    this.value$array$com$google$common$base$Optional = Optional.absent();
    return self;
  }

  /**
   * Sets the default value for the list property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalConstructorWithBuilderPropertiesBuilder withList(List<Integer> value) {
    if (value == null) {
      this.value$list$com$google$common$base$Optional = null;
    } else {
      this.value$list$com$google$common$base$Optional = Optional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the list property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalConstructorWithBuilderPropertiesBuilder withList(Optional<List<Integer>> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$list$com$google$common$base$Optional = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default builder for the list property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalConstructorWithBuilderPropertiesBuilder withList(Builder<? extends List<Integer>> builder) {
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
  @GwtIncompatible
  public Object clone() {
    try {
      PojoWithGuavaOptionalOptionalConstructorWithBuilderPropertiesBuilder result = (PojoWithGuavaOptionalOptionalConstructorWithBuilderPropertiesBuilder)super.clone();
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
  public PojoWithGuavaOptionalOptionalConstructorWithBuilderPropertiesBuilder but() {
    return (PojoWithGuavaOptionalOptionalConstructorWithBuilderPropertiesBuilder)clone();
  }

  /**
   * Creates a new {@link PojoWithGuavaOptionalOptionalConstructorWithBuilderProperties} based on this builder's settings.
   *
   * @return the created PojoWithGuavaOptionalOptionalConstructorWithBuilderProperties
   */
  @Override
  public PojoWithGuavaOptionalOptionalConstructorWithBuilderProperties build() {
    try {
      Optional<Integer> _boxedInt = value$boxedInt$com$google$common$base$Optional;
      if (value$boxedInt$com$google$common$base$Optional != null && !value$boxedInt$com$google$common$base$Optional.isPresent() && builder$boxedInt$com$google$common$base$Optional != null) {
        Integer builtValue = builder$boxedInt$com$google$common$base$Optional.build();
        if (builtValue != null) {
          _boxedInt = Optional.of(builtValue);
        }
      }
      Optional<int[]> _array = value$array$com$google$common$base$Optional;
      if (value$array$com$google$common$base$Optional != null && !value$array$com$google$common$base$Optional.isPresent() && builder$array$com$google$common$base$Optional != null) {
        int[] builtValue = builder$array$com$google$common$base$Optional.build();
        if (builtValue != null) {
          _array = Optional.of(builtValue);
        }
      }
      Optional<List<Integer>> _list = value$list$com$google$common$base$Optional;
      if (value$list$com$google$common$base$Optional != null && !value$list$com$google$common$base$Optional.isPresent() && builder$list$com$google$common$base$Optional != null) {
        List<Integer> builtValue = builder$list$com$google$common$base$Optional.build();
        if (builtValue != null) {
          _list = Optional.of(builtValue);
        }
      }
      PojoWithGuavaOptionalOptionalConstructorWithBuilderProperties result = new PojoWithGuavaOptionalOptionalConstructorWithBuilderProperties(_boxedInt, _array, _list);
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
