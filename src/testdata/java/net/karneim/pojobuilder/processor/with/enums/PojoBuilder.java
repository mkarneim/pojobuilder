package net.karneim.pojobuilder.processor.with.enums;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class PojoBuilder
    implements Cloneable {
  protected PojoBuilder self;
  protected Status value$status$net$karneim$pojobuilder$processor$with$enums$Status;
  protected boolean isSet$status$net$karneim$pojobuilder$processor$with$enums$Status;

  /**
   * Creates a new {@link PojoBuilder}.
   */
  public PojoBuilder() {
    self = (PojoBuilder)this;
  }

  /**
   * Sets the default value for the {@link Pojo#status} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoBuilder withStatus(Status value) {
    this.value$status$net$karneim$pojobuilder$processor$with$enums$Status = value;
    this.isSet$status$net$karneim$pojobuilder$processor$with$enums$Status = true;
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
      PojoBuilder result = (PojoBuilder)super.clone();
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
  public PojoBuilder but() {
    return (PojoBuilder)clone();
  }

  /**
   * Creates a new {@link Pojo} based on this builder's settings.
   *
   * @return the created Pojo
   */
  public Pojo build() {
    try {
      Pojo result = new Pojo();
      if (isSet$status$net$karneim$pojobuilder$processor$with$enums$Status) {
        result.status = value$status$net$karneim$pojobuilder$processor$with$enums$Status;
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
