package net.karneim.pojobuilder.processor.with.validator;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class PojoBuilder
    implements Cloneable {
  protected PojoBuilder self;
  protected PojoValidator validator = new PojoValidator();
  protected String value$name$java$lang$String;
  protected boolean isSet$name$java$lang$String;

  /**
   * Creates a new {@link PojoBuilder}.
   */
  public PojoBuilder() {
    self = (PojoBuilder)this;
  }

  /**
   * Sets the default value for the {@link Pojo#name} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoBuilder withName(String value) {
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
      PojoBuilder result = (PojoBuilder)super.clone();
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
  public PojoBuilder but() {
    return (PojoBuilder)clone();
  }

  /**
   * Creates a new {@link Pojo} based on this builder's settings.
   *
   * @return the created Pojo
   */
  public Pojo build() {
    try {
      Pojo result = new Pojo();
      if (isSet$name$java$lang$String) {
        result.name = value$name$java$lang$String;
      }
      validator.validate(result);
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
