package net.karneim.pojobuilder.processor.with.utf8;

import java.util.Date;
import javax.annotation.Generated;

@Generated("PojoBuilder")
public class ÄnderungBuilder
    implements Cloneable {
  protected ÄnderungBuilder self;
  protected Date value$gültigAb$java$util$Date;
  protected boolean isSet$gültigAb$java$util$Date;

  /**
   * Creates a new {@link ÄnderungBuilder}.
   */
  public ÄnderungBuilder() {
    self = (ÄnderungBuilder)this;
  }

  /**
   * Sets the default value for the {@link Änderung#gültigAb} property.
   *
   * @param value the default value
   * @return this builder
   */
  public ÄnderungBuilder withGültigAb(Date value) {
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
  public Object clone() {
    try {
      ÄnderungBuilder result = (ÄnderungBuilder)super.clone();
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
  public ÄnderungBuilder but() {
    return (ÄnderungBuilder)clone();
  }

  /**
   * Creates a new {@link Änderung} based on this builder's settings.
   *
   * @return the created Änderung
   */
  public Änderung build() {
    try {
      Änderung result = new Änderung();
      if (isSet$gültigAb$java$util$Date) {
        result.gültigAb = value$gültigAb$java$util$Date;
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new java.lang.reflect.UndeclaredThrowableException(ex);
    }
  }
}
