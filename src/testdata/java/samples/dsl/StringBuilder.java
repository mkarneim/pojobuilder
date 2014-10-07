package samples.dsl;

import javax.annotation.Generated;
import samples.Builder;

@Generated("PojoBuilder")
public class StringBuilder
    implements Builder<String>, Cloneable {
  protected StringBuilder self;
  protected String value$format$java$lang$String;
  protected boolean isSet$format$java$lang$String;
  protected Builder<String> builder$format$java$lang$String;
  protected long value$nextNumber$long;
  protected boolean isSet$nextNumber$long;
  protected Builder<Long> builder$nextNumber$long;

  /**
   * Creates a new {@link StringBuilder}.
   */
  public StringBuilder() {
    self = (StringBuilder)this;
  }

  /**
   * Sets the default value for the {@link String#format} property.
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
   * Sets the default builder for the {@link String#format} property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public StringBuilder withFormat(Builder<String> builder) {
    this.builder$format$java$lang$String = builder;
    this.isSet$format$java$lang$String = false;
    return self;
  }

  /**
   * Sets the default value for the {@link String#nextNumber} property.
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
   * Sets the default builder for the {@link String#nextNumber} property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public StringBuilder withNextNumber(Builder<Long> builder) {
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
      String _format = !isSet$format$java$lang$String && builder$format$java$lang$String!=null?builder$format$java$lang$String.build():value$format$java$lang$String;
      long _nextNumber = !isSet$nextNumber$long && builder$nextNumber$long!=null?builder$nextNumber$long.build():value$nextNumber$long;
      String result = DslBase.PojoFactory.createString(_format, _nextNumber);
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Throwable t) {
      throw new java.lang.reflect.UndeclaredThrowableException(t);
    }
  }
}
