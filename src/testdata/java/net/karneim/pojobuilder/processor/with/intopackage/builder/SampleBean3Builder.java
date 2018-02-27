package net.karneim.pojobuilder.processor.with.intopackage.builder;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;
import net.karneim.pojobuilder.processor.with.intopackage.SampleBean3;

@Generated("PojoBuilder")
public class SampleBean3Builder
    implements Cloneable {
  protected SampleBean3Builder self;
  protected String value$name$java$lang$String;
  protected boolean isSet$name$java$lang$String;

  /**
   * Creates a new {@link SampleBean3Builder}.
   */
  public SampleBean3Builder() {
    self = (SampleBean3Builder)this;
  }

  /**
   * Sets the default value for the {@link SampleBean3#name} property.
   *
   * @param value the default value
   * @return this builder
   */
  public SampleBean3Builder withName(String value) {
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
      SampleBean3Builder result = (SampleBean3Builder)super.clone();
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
  public SampleBean3Builder but() {
    return (SampleBean3Builder)clone();
  }

  /**
   * Creates a new {@link SampleBean3} based on this builder's settings.
   *
   * @return the created SampleBean3
   */
  public SampleBean3 build() {
    try {
      SampleBean3 result = new SampleBean3(value$name$java$lang$String);
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
