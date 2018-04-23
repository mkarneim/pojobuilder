package net.karneim.pojobuilder.processor.with.varargs;

import java.io.File;
import java.math.BigDecimal;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class OtherPojoBuilder
    implements Cloneable {
  protected OtherPojoBuilder self;
  protected BigDecimal[] value$someNumbers$java$math$BigDecimal$L;
  protected boolean isSet$someNumbers$java$math$BigDecimal$L;
  protected String[] value$someStrings$java$lang$String$L;
  protected boolean isSet$someStrings$java$lang$String$L;
  protected File[] value$someFiles$java$io$File$L;
  protected boolean isSet$someFiles$java$io$File$L;

  /**
   * Creates a new {@link OtherPojoBuilder}.
   */
  public OtherPojoBuilder() {
    self = (OtherPojoBuilder)this;
  }

  /**
   * Sets the default value for the someNumbers property.
   *
   * @param value the default value
   * @return this builder
   */
  public OtherPojoBuilder withSomeNumbers(BigDecimal... value) {
    this.value$someNumbers$java$math$BigDecimal$L = value;
    this.isSet$someNumbers$java$math$BigDecimal$L = true;
    return self;
  }

  /**
   * Sets the default value for the someStrings property.
   *
   * @param value the default value
   * @return this builder
   */
  public OtherPojoBuilder withSomeStrings(String... value) {
    this.value$someStrings$java$lang$String$L = value;
    this.isSet$someStrings$java$lang$String$L = true;
    return self;
  }

  /**
   * Sets the default value for the someFiles property.
   *
   * @param value the default value
   * @return this builder
   */
  public OtherPojoBuilder withSomeFiles(File... value) {
    this.value$someFiles$java$io$File$L = value;
    this.isSet$someFiles$java$io$File$L = true;
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
      OtherPojoBuilder result = (OtherPojoBuilder)super.clone();
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
  public OtherPojoBuilder but() {
    return (OtherPojoBuilder)clone();
  }

  /**
   * Creates a new {@link Pojo} based on this builder's settings.
   *
   * @return the created Pojo
   */
  public Pojo build() {
    try {
      Pojo result = PojoFactory.createPojo(value$someNumbers$java$math$BigDecimal$L);
      if (isSet$someStrings$java$lang$String$L) {
        result.setSomeStrings(value$someStrings$java$lang$String$L);
      }
      if (isSet$someFiles$java$io$File$L) {
        result.setSomeFiles(value$someFiles$java$io$File$L);
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
