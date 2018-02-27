package net.karneim.pojobuilder.processor.with.staticfactorymethod;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class TroubleBuilder
    implements Cloneable {
  protected TroubleBuilder self;
  protected char value$a$char;
  protected boolean isSet$a$char;

  /**
   * Factory Method to construct a TroubleBuilder
   *
   * @return a new TroubleBuilder
   */
  public static TroubleBuilder trouble() {
    return new TroubleBuilder();
  }

  /**
   * Creates a new {@link TroubleBuilder}.
   */
  private TroubleBuilder() {
    self = (TroubleBuilder)this;
  }

  /**
   * Sets the default value for the {@link Trouble#a} property.
   *
   * @param value the default value
   * @return this builder
   */
  public TroubleBuilder withA(char value) {
    this.value$a$char = value;
    this.isSet$a$char = true;
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
      TroubleBuilder result = (TroubleBuilder)super.clone();
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
  public TroubleBuilder but() {
    return (TroubleBuilder)clone();
  }

  /**
   * Creates a new {@link Trouble} based on this builder's settings.
   *
   * @return the created Trouble
   */
  public Trouble build() {
    try {
      Trouble result = new Trouble();
      if (isSet$a$char) {
        result.a = value$a$char;
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
