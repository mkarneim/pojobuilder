package net.karneim.pojobuilder.processor.with.name;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class FluentSampleBean4Builder
    implements Cloneable {
  protected FluentSampleBean4Builder self;
  protected String value$name$java$lang$String;
  protected boolean isSet$name$java$lang$String;

  /**
   * Creates a new {@link FluentSampleBean4Builder}.
   */
  public FluentSampleBean4Builder() {
    self = (FluentSampleBean4Builder)this;
  }

  /**
   * Sets the default value for the {@link SampleBean4#name} property.
   *
   * @param value the default value
   * @return this builder
   */
  public FluentSampleBean4Builder withName(String value) {
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
      FluentSampleBean4Builder result = (FluentSampleBean4Builder)super.clone();
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
  public FluentSampleBean4Builder but() {
    return (FluentSampleBean4Builder)clone();
  }

  /**
   * Creates a new {@link SampleBean4} based on this builder's settings.
   *
   * @return the created SampleBean4
   */
  public SampleBean4 build() {
    try {
      SampleBean4 result = new SampleBean4(value$name$java$lang$String);
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
