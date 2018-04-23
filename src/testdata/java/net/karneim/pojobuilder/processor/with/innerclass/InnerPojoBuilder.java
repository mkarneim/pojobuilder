package net.karneim.pojobuilder.processor.with.innerclass;

import java.util.List;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class InnerPojoBuilder
    implements Cloneable {
  protected InnerPojoBuilder self;
  protected int value$number$int;
  protected boolean isSet$number$int;
  protected List<String> value$strings$java$util$List;
  protected boolean isSet$strings$java$util$List;
  protected String value$name$java$lang$String;
  protected boolean isSet$name$java$lang$String;

  /**
   * Creates a new {@link InnerPojoBuilder}.
   */
  public InnerPojoBuilder() {
    self = (InnerPojoBuilder)this;
  }

  /**
   * Sets the default value for the {@link OuterPojo.InnerPojo#number} property.
   *
   * @param value the default value
   * @return this builder
   */
  public InnerPojoBuilder withNumber(int value) {
    this.value$number$int = value;
    this.isSet$number$int = true;
    return self;
  }

  /**
   * Sets the default value for the {@link OuterPojo.InnerPojo#strings} property.
   *
   * @param value the default value
   * @return this builder
   */
  public InnerPojoBuilder withStrings(List<String> value) {
    this.value$strings$java$util$List = value;
    this.isSet$strings$java$util$List = true;
    return self;
  }

  /**
   * Sets the default value for the name property.
   *
   * @param value the default value
   * @return this builder
   */
  public InnerPojoBuilder withName(String value) {
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
      InnerPojoBuilder result = (InnerPojoBuilder)super.clone();
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
  public InnerPojoBuilder but() {
    return (InnerPojoBuilder)clone();
  }

  /**
   * Creates a new {@link OuterPojo.InnerPojo} based on this builder's settings.
   *
   * @return the created OuterPojo.InnerPojo
   */
  public OuterPojo.InnerPojo build() {
    try {
      OuterPojo.InnerPojo result = PojoFactory.createInnerPojo();
      if (isSet$name$java$lang$String) {
        result.setName(value$name$java$lang$String);
      }
      if (isSet$number$int) {
        result.number = value$number$int;
      }
      if (isSet$strings$java$util$List) {
        result.strings = value$strings$java$util$List;
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
