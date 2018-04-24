package samples.dsl;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;
import samples.Builder;

@Generated("PojoBuilder")
public class StringBuilder
    implements Builder<String>, Cloneable {
  protected StringBuilder self;
  protected String value$format$java$lang$String;
  protected boolean isSet$format$java$lang$String;
  protected Builder<? extends String> builder$format$java$lang$String;
  protected long value$nextNumber$long;
  protected boolean isSet$nextNumber$long;
  protected Builder<? extends Long> builder$nextNumber$long;

  /**
   * Creates a new {@link StringBuilder}.
   */
  public StringBuilder() {
    self = (StringBuilder)this;
  }

  /**
   * Sets the default value for the format property.
   *
   * @param value the default value
   * @return this builder
   */
  public StringBuilder withFormat(String value) {
    this.value$format$java$lang$String = value;
    this.isSet$format$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default builder for the format property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public StringBuilder withFormat(Builder<? extends String> builder) {
    this.builder$format$java$lang$String = builder;
    this.isSet$format$java$lang$String = false;
    return self;
  }

  /**
   * Sets the default value for the nextNumber property.
   *
   * @param value the default value
   * @return this builder
   */
  public StringBuilder withNextNumber(long value) {
    this.value$nextNumber$long = value;
    this.isSet$nextNumber$long = true;
    return self;
  }

  /**
   * Sets the default builder for the nextNumber property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public StringBuilder withNextNumber(Builder<? extends Long> builder) {
    this.builder$nextNumber$long = builder;
    this.isSet$nextNumber$long = false;
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
      StringBuilder result = (StringBuilder)super.clone();
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
  public StringBuilder but() {
    return (StringBuilder)clone();
  }

  /**
   * Creates a new {@link String} based on this builder's settings.
   *
   * @return the created String
   */
  @Override
  public String build() {
    try {
      String _format;
      if (!isSet$format$java$lang$String && builder$format$java$lang$String != null) {
        _format = builder$format$java$lang$String.build();
      } else {
        _format = value$format$java$lang$String;
      }
      long _nextNumber;
      if (!isSet$nextNumber$long && builder$nextNumber$long != null) {
        _nextNumber = builder$nextNumber$long.build();
      } else {
        _nextNumber = value$nextNumber$long;
      }
      String result = TestDslBase.PojoFactory.createString(_format, _nextNumber);
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
