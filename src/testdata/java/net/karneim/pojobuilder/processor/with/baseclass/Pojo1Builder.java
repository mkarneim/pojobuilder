package net.karneim.pojobuilder.processor.with.baseclass;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class Pojo1Builder extends SimpleBaseBuilder
    implements Cloneable {
  protected Pojo1Builder self;
  protected String value$name$java$lang$String;
  protected boolean isSet$name$java$lang$String;

  /**
   * Creates a new {@link Pojo1Builder}.
   */
  public Pojo1Builder() {
    self = (Pojo1Builder)this;
  }

  /**
   * Sets the default value for the {@link Pojo1#name} property.
   *
   * @param value the default value
   * @return this builder
   */
  public Pojo1Builder withName(String value) {
    this.value$name$java$lang$String = value;
    this.isSet$name$java$lang$String = true;
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
      Pojo1Builder result = (Pojo1Builder)super.clone();
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
  public Pojo1Builder but() {
    return (Pojo1Builder)clone();
  }

  /**
   * Creates a new {@link Pojo1} based on this builder's settings.
   *
   * @return the created Pojo1
   */
  public Pojo1 build() {
    try {
      Pojo1 result = new Pojo1();
      if (isSet$name$java$lang$String) {
        result.name = value$name$java$lang$String;
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
