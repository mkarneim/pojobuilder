package net.karneim.pojobuilder.processor.with.optionals;

import com.google.common.base.Optional;
import java.util.List;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class PojoWithGuavaOptionalOptionalFieldAccessBuilder
    implements Cloneable {
  protected PojoWithGuavaOptionalOptionalFieldAccessBuilder self;
  protected Optional<Integer> value$boxedInt$com$google$common$base$Optional = Optional.absent();
  protected Optional<int[]> value$array$com$google$common$base$Optional = Optional.absent();
  protected Optional<List<Integer>> value$list$com$google$common$base$Optional = Optional.absent();

  /**
   * Creates a new {@link PojoWithGuavaOptionalOptionalFieldAccessBuilder}.
   */
  public PojoWithGuavaOptionalOptionalFieldAccessBuilder() {
    self = (PojoWithGuavaOptionalOptionalFieldAccessBuilder)this;
  }

  /**
   * Sets the default value for the {@link PojoWithGuavaOptionalOptionalFieldAccess#boxedInt} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalFieldAccessBuilder withBoxedInt(Integer value) {
    if (value == null) {
      this.value$boxedInt$com$google$common$base$Optional = null;
    } else {
      this.value$boxedInt$com$google$common$base$Optional = Optional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithGuavaOptionalOptionalFieldAccess#boxedInt} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalFieldAccessBuilder withBoxedInt(Optional<Integer> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$boxedInt$com$google$common$base$Optional = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default value for the {@link PojoWithGuavaOptionalOptionalFieldAccess#array} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalFieldAccessBuilder withArray(int[] value) {
    if (value == null) {
      this.value$array$com$google$common$base$Optional = null;
    } else {
      this.value$array$com$google$common$base$Optional = Optional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithGuavaOptionalOptionalFieldAccess#array} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalFieldAccessBuilder withArray(Optional<int[]> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$array$com$google$common$base$Optional = optionalValue;
    }
    return self;
  }

  /**
   * Sets the default value for the {@link PojoWithGuavaOptionalOptionalFieldAccess#list} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalFieldAccessBuilder withList(List<Integer> value) {
    if (value == null) {
      this.value$list$com$google$common$base$Optional = null;
    } else {
      this.value$list$com$google$common$base$Optional = Optional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithGuavaOptionalOptionalFieldAccess#list} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithGuavaOptionalOptionalFieldAccessBuilder withList(Optional<List<Integer>> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$list$com$google$common$base$Optional = optionalValue;
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
      PojoWithGuavaOptionalOptionalFieldAccessBuilder result = (PojoWithGuavaOptionalOptionalFieldAccessBuilder)super.clone();
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
  public PojoWithGuavaOptionalOptionalFieldAccessBuilder but() {
    return (PojoWithGuavaOptionalOptionalFieldAccessBuilder)clone();
  }

  /**
   * Creates a new {@link PojoWithGuavaOptionalOptionalFieldAccess} based on this builder's settings.
   *
   * @return the created PojoWithGuavaOptionalOptionalFieldAccess
   */
  public PojoWithGuavaOptionalOptionalFieldAccess build() {
    try {
      PojoWithGuavaOptionalOptionalFieldAccess result = new PojoWithGuavaOptionalOptionalFieldAccess();
      if (value$boxedInt$com$google$common$base$Optional == null || value$boxedInt$com$google$common$base$Optional.isPresent()) {
        result.boxedInt = value$boxedInt$com$google$common$base$Optional;
      }
      if (value$array$com$google$common$base$Optional == null || value$array$com$google$common$base$Optional.isPresent()) {
        result.array = value$array$com$google$common$base$Optional;
      }
      if (value$list$com$google$common$base$Optional == null || value$list$com$google$common$base$Optional.isPresent()) {
        result.list = value$list$com$google$common$base$Optional;
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
