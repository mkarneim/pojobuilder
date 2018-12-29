package net.karneim.pojobuilder.processor.with.ambiguousimports.optional;

import com.google.common.base.Optional;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class PojoWithAmbiguousOptionalImportsBuilder
    implements Cloneable {
  protected PojoWithAmbiguousOptionalImportsBuilder self;
  protected java.util.Optional<? extends Optional<String>> value$field$com$google$common$base$Optional = java.util.Optional.empty();

  /**
   * Creates a new {@link PojoWithAmbiguousOptionalImportsBuilder}.
   */
  public PojoWithAmbiguousOptionalImportsBuilder() {
    self = (PojoWithAmbiguousOptionalImportsBuilder)this;
  }

  /**
   * Sets the default value for the {@link PojoWithAmbiguousOptionalImports#field} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithAmbiguousOptionalImportsBuilder withField(Optional<String> value) {
    if (value == null) {
      this.value$field$com$google$common$base$Optional = null;
    } else {
      this.value$field$com$google$common$base$Optional = java.util.Optional.of(value);
    }
    return self;
  }

  /**
   * Optionally sets the default value for the {@link PojoWithAmbiguousOptionalImports#field} property.
   *
   * @param optionalValue the optional default value
   * @return this builder
   */
  public PojoWithAmbiguousOptionalImportsBuilder withField(java.util.Optional<? extends Optional<String>> optionalValue) {
    if (optionalValue == null || optionalValue.isPresent()) {
      this.value$field$com$google$common$base$Optional = optionalValue;
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
      PojoWithAmbiguousOptionalImportsBuilder result = (PojoWithAmbiguousOptionalImportsBuilder)super.clone();
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
  public PojoWithAmbiguousOptionalImportsBuilder but() {
    return (PojoWithAmbiguousOptionalImportsBuilder)clone();
  }

  /**
   * Creates a new {@link PojoWithAmbiguousOptionalImports} based on this builder's settings.
   *
   * @return the created PojoWithAmbiguousOptionalImports
   */
  public PojoWithAmbiguousOptionalImports build() {
    try {
      PojoWithAmbiguousOptionalImports result = new PojoWithAmbiguousOptionalImports();
      if (value$field$com$google$common$base$Optional == null) {
        result.field = (Optional<String>) null;
      } else if (value$field$com$google$common$base$Optional.isPresent()) {
        result.field = value$field$com$google$common$base$Optional.get();
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
