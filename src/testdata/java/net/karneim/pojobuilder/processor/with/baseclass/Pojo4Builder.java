package net.karneim.pojobuilder.processor.with.baseclass;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class Pojo4Builder extends BaseBuilderWithCloneMethod
    implements Cloneable {
  protected Pojo4Builder self;
  protected String value$name$java$lang$String;
  protected boolean isSet$name$java$lang$String;

  /**
   * Creates a new {@link Pojo4Builder}.
   */
  public Pojo4Builder() {
    self = (Pojo4Builder)this;
  }

  /**
   * Sets the default value for the {@link Pojo4#name} property.
   *
   * @param value the default value
   * @return this builder
   */
  public Pojo4Builder withName(String value) {
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
    Pojo4Builder result = (Pojo4Builder)super.clone();
    result.self = result;
    return result;
  }

  /**
   * Returns a clone of this builder.
   *
   * @return the clone
   */
  @GwtIncompatible
  public Pojo4Builder but() {
    return (Pojo4Builder)clone();
  }

  /**
   * Creates a new {@link Pojo4} based on this builder's settings.
   *
   * @return the created Pojo4
   */
  public Pojo4 build() {
    try {
      Pojo4 result = new Pojo4();
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
