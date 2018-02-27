package net.karneim.pojobuilder.processor.with.utf8;

import java.util.Date;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class PojoWithUmlautBuilder
    implements Cloneable {
  protected PojoWithUmlautBuilder self;
  protected Date value$gültigAb$java$util$Date;
  protected boolean isSet$gültigAb$java$util$Date;

  /**
   * Creates a new {@link PojoWithUmlautBuilder}.
   */
  public PojoWithUmlautBuilder() {
    self = (PojoWithUmlautBuilder)this;
  }

  /**
   * Sets the default value for the {@link PojoWithUmlaut#gültigAb} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoWithUmlautBuilder withGültigAb(Date value) {
    this.value$gültigAb$java$util$Date = value;
    this.isSet$gültigAb$java$util$Date = true;
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
      PojoWithUmlautBuilder result = (PojoWithUmlautBuilder)super.clone();
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
  public PojoWithUmlautBuilder but() {
    return (PojoWithUmlautBuilder)clone();
  }

  /**
   * Creates a new {@link PojoWithUmlaut} based on this builder's settings.
   *
   * @return the created PojoWithUmlaut
   */
  public PojoWithUmlaut build() {
    try {
      PojoWithUmlaut result = new PojoWithUmlaut();
      if (isSet$gültigAb$java$util$Date) {
        result.gültigAb = value$gültigAb$java$util$Date;
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
