package net.karneim.pojobuilder.processor.with.settername;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class Pojo2Builder
    implements Cloneable {
  protected Pojo2Builder self;
  protected String value$name$java$lang$String;
  protected boolean isSet$name$java$lang$String;
  protected int value$number$int;
  protected boolean isSet$number$int;

  /**
   * Creates a new {@link Pojo2Builder}.
   */
  public Pojo2Builder() {
    self = (Pojo2Builder)this;
  }

  /**
   * Sets the default value for the {@link Pojo2#name} property.
   *
   * @param value the default value
   * @return this builder
   */
  public Pojo2Builder name(String value) {
    this.value$name$java$lang$String = value;
    this.isSet$name$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default value for the {@link Pojo2#number} property.
   *
   * @param value the default value
   * @return this builder
   */
  public Pojo2Builder number(int value) {
    this.value$number$int = value;
    this.isSet$number$int = true;
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
      Pojo2Builder result = (Pojo2Builder)super.clone();
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
  public Pojo2Builder but() {
    return (Pojo2Builder)clone();
  }

  /**
   * Creates a new {@link Pojo2} based on this builder's settings.
   *
   * @return the created Pojo2
   */
  public Pojo2 build() {
    try {
      Pojo2 result = new Pojo2();
      if (isSet$name$java$lang$String) {
        result.name = value$name$java$lang$String;
      }
      if (isSet$number$int) {
        result.number = value$number$int;
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
