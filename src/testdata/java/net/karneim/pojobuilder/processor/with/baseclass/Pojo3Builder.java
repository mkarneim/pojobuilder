package net.karneim.pojobuilder.processor.with.baseclass;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class Pojo3Builder extends BaseBuilderWithRawBuildMethod
    implements Cloneable {
  protected Pojo3Builder self;
  protected String value$name$java$lang$String;
  protected boolean isSet$name$java$lang$String;

  /**
   * Creates a new {@link Pojo3Builder}.
   */
  public Pojo3Builder() {
    self = (Pojo3Builder)this;
  }

  /**
   * Sets the default value for the {@link Pojo3#name} property.
   *
   * @param value the default value
   * @return this builder
   */
  public Pojo3Builder withName(String value) {
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
      Pojo3Builder result = (Pojo3Builder)super.clone();
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
  public Pojo3Builder but() {
    return (Pojo3Builder)clone();
  }

  /**
   * Creates a new {@link Pojo3} based on this builder's settings.
   *
   * @return the created Pojo3
   */
  @Override
  public Pojo3 build() {
    try {
      Pojo3 result = new Pojo3();
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
