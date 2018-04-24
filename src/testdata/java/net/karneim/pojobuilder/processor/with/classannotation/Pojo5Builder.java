package net.karneim.pojobuilder.processor.with.classannotation;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class Pojo5Builder
    implements Cloneable {
  protected Pojo5Builder self;
  protected String value$name$java$lang$String;
  protected boolean isSet$name$java$lang$String;

  /**
   * Creates a new {@link Pojo5Builder}.
   */
  public Pojo5Builder() {
    self = (Pojo5Builder)this;
  }

  /**
   * Sets the default value for the name property.
   *
   * @param value the default value
   * @return this builder
   */
  public Pojo5Builder withName(String value) {
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
      Pojo5Builder result = (Pojo5Builder)super.clone();
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
  public Pojo5Builder but() {
    return (Pojo5Builder)clone();
  }

  /**
   * Creates a new {@link Pojo5} based on this builder's settings.
   *
   * @return the created Pojo5
   */
  public Pojo5 build() {
    try {
      Pojo5 result = new Pojo5(value$name$java$lang$String);
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
