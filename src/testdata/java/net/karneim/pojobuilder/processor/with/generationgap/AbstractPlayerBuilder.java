package net.karneim.pojobuilder.processor.with.generationgap;

import javax.annotation.Generated;

@Generated("PojoBuilder")
public abstract class AbstractPlayerBuilder
    implements Cloneable {
  protected PlayerBuilder self;
  protected String value$name$java$lang$String;
  protected boolean isSet$name$java$lang$String;
  protected int value$points$int;
  protected boolean isSet$points$int;

  /**
   * Creates a new {@link AbstractPlayerBuilder}.
   */
  public AbstractPlayerBuilder() {
    self = (PlayerBuilder)this;
  }

  /**
   * Sets the default value for the {@link Player#name} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PlayerBuilder withName(String value) {
    this.value$name$java$lang$String = value;
    this.isSet$name$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default value for the {@link Player#points} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PlayerBuilder withPoints(int value) {
    this.value$points$int = value;
    this.isSet$points$int = true;
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
      PlayerBuilder result = (PlayerBuilder)super.clone();
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
  public PlayerBuilder but() {
    return (PlayerBuilder)clone();
  }

  /**
   * Creates a new {@link Player} based on this builder's settings.
   *
   * @return the created Player
   */
  public Player build() {
    try {
      Player result = new Player(value$name$java$lang$String);
      if (isSet$points$int) {
        result.setPoints(value$points$int);
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Throwable t) {
      throw new java.lang.reflect.UndeclaredThrowableException(t);
    }
  }
}
