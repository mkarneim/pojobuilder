package net.karneim.pojobuilder.processor.with.optionals;

import com.google.common.base.Optional;
import java.util.List;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;
import net.karneim.pojobuilder.processor.with.builderinterface.Builder;

@Generated("PojoBuilder")
public class PojoWithGuavaOptionalOptionalFieldAccessWithBuilderPropertiesBuilder
    implements Builder<PojoWithGuavaOptionalOptionalFieldAccessWithBuilderProperties>, Cloneable {
  protected PojoWithGuavaOptionalOptionalFieldAccessWithBuilderPropertiesBuilder self;
  protected Optional<Integer> value$boxedInt$com$google$common$base$Optional = Optional.absent();
  protected Builder<? extends Integer> builder$boxedInt$com$google$common$base$Optional;
  protected Optional<int[]> value$array$com$google$common$base$Optional = Optional.absent();
  protected Builder<? extends int[]> builder$array$com$google$common$base$Optional;
  protected Optional<List<Integer>> value$list$com$google$common$base$Optional = Optional.absent();
  protected Builder<? extends List<Integer>> builder$list$com$google$common$base$Optional;

  /**
   * Creates a new {@link PojoWithGuavaOptionalOptionalFieldAccessWithBuilderPropertiesBuilder}.
   */
  public PojoWithGuavaOptionalOptionalFieldAccessWithBuilderPropertiesBuilder() {
    self = (PojoWithGuavaOptionalOptionalFieldAccessWithBuilderPropertiesBuilder)this;
  }

  /**
   * Sets the default value for the {@link PojoWithGuavaOptionalOptionalFieldAccessWithBuilderProperties#boxedInt} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalFieldAccessWithBuilderPropertiesBuilder withBoxedInt(Integer value) {
    if (value == null) {
      this.value$boxedInt$com$google$common$base$Optional = null;
    } else {
      this.value$boxedInt$com$google$common$base$Optional = Optional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithGuavaOptionalOptionalFieldAccessWithBuilderProperties#boxedInt} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalFieldAccessWithBuilderPropertiesBuilder withBoxedInt(Optional<Integer> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$boxedInt$com$google$common$base$Optional = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default builder for the {@link PojoWithGuavaOptionalOptionalFieldAccessWithBuilderProperties#boxedInt} property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalFieldAccessWithBuilderPropertiesBuilder withBoxedInt(Builder<? extends Integer> builder) {
    this.builder$boxedInt$com$google$common$base$Optional = builder;
    this.value$boxedInt$com$google$common$base$Optional = Optional.absent();
    return self;
  }

  /**
   * Sets the default value for the {@link PojoWithGuavaOptionalOptionalFieldAccessWithBuilderProperties#array} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalFieldAccessWithBuilderPropertiesBuilder withArray(int[] value) {
    if (value == null) {
      this.value$array$com$google$common$base$Optional = null;
    } else {
      this.value$array$com$google$common$base$Optional = Optional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithGuavaOptionalOptionalFieldAccessWithBuilderProperties#array} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalFieldAccessWithBuilderPropertiesBuilder withArray(Optional<int[]> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$array$com$google$common$base$Optional = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default builder for the {@link PojoWithGuavaOptionalOptionalFieldAccessWithBuilderProperties#array} property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalFieldAccessWithBuilderPropertiesBuilder withArray(Builder<? extends int[]> builder) {
    this.builder$array$com$google$common$base$Optional = builder;
    this.value$array$com$google$common$base$Optional = Optional.absent();
    return self;
  }

  /**
   * Sets the default value for the {@link PojoWithGuavaOptionalOptionalFieldAccessWithBuilderProperties#list} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalFieldAccessWithBuilderPropertiesBuilder withList(List<Integer> value) {
    if (value == null) {
      this.value$list$com$google$common$base$Optional = null;
    } else {
      this.value$list$com$google$common$base$Optional = Optional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithGuavaOptionalOptionalFieldAccessWithBuilderProperties#list} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalFieldAccessWithBuilderPropertiesBuilder withList(Optional<List<Integer>> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$list$com$google$common$base$Optional = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default builder for the {@link PojoWithGuavaOptionalOptionalFieldAccessWithBuilderProperties#list} property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalFieldAccessWithBuilderPropertiesBuilder withList(Builder<? extends List<Integer>> builder) {
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
      PojoWithGuavaOptionalOptionalFieldAccessWithBuilderPropertiesBuilder result = (PojoWithGuavaOptionalOptionalFieldAccessWithBuilderPropertiesBuilder)super.clone();
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
  public PojoWithGuavaOptionalOptionalFieldAccessWithBuilderPropertiesBuilder but() {
    return (PojoWithGuavaOptionalOptionalFieldAccessWithBuilderPropertiesBuilder)clone();
  }

  /**
   * Creates a new {@link PojoWithGuavaOptionalOptionalFieldAccessWithBuilderProperties} based on this builder's settings.
   *
   * @return the created PojoWithGuavaOptionalOptionalFieldAccessWithBuilderProperties
   */
  @Override
  public PojoWithGuavaOptionalOptionalFieldAccessWithBuilderProperties build() {
    try {
      PojoWithGuavaOptionalOptionalFieldAccessWithBuilderProperties result = new PojoWithGuavaOptionalOptionalFieldAccessWithBuilderProperties();
      if (value$boxedInt$com$google$common$base$Optional == null || value$boxedInt$com$google$common$base$Optional.isPresent()) {
        result.boxedInt = value$boxedInt$com$google$common$base$Optional;
      } else if (builder$boxedInt$com$google$common$base$Optional != null) {
        Integer builtValue = builder$boxedInt$com$google$common$base$Optional.build();
        if (builtValue == null) {
          result.boxedInt = (Optional<Integer>) null;
        } else {
          result.boxedInt = Optional.of(builtValue);
        }
      }
      if (value$array$com$google$common$base$Optional == null || value$array$com$google$common$base$Optional.isPresent()) {
        result.array = value$array$com$google$common$base$Optional;
      } else if (builder$array$com$google$common$base$Optional != null) {
        int[] builtValue = builder$array$com$google$common$base$Optional.build();
        if (builtValue == null) {
          result.array = (Optional<int[]>) null;
        } else {
          result.array = Optional.of(builtValue);
        }
      }
      if (value$list$com$google$common$base$Optional == null || value$list$com$google$common$base$Optional.isPresent()) {
        result.list = value$list$com$google$common$base$Optional;
      } else if (builder$list$com$google$common$base$Optional != null) {
        List<Integer> builtValue = builder$list$com$google$common$base$Optional.build();
        if (builtValue == null) {
          result.list = (Optional<List<Integer>>) null;
        } else {
          result.list = Optional.of(builtValue);
        }
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
