package net.karneim.pojobuilder.processor.with.builderinheritance;

import javax.annotation.Generated;

@Generated("PojoBuilder")
public class FruitBuilder
    implements Cloneable {
  protected FruitBuilder self;
  protected String value$colour$java$lang$String;
  protected boolean isSet$colour$java$lang$String;

  /**
   * Creates a new {@link FruitBuilder}.
   */
  public FruitBuilder() {
    self = (FruitBuilder)this;
  }

  /**
   * Sets the default value for the {@link Fruit#colour} property.
   *
   * @param value the default value
   * @return this builder
   */
  public FruitBuilder withColour(String value) {
    this.value$colour$java$lang$String = value;
    this.isSet$colour$java$lang$String = true;
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
      FruitBuilder result = (FruitBuilder)super.clone();
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
  public FruitBuilder but() {
    return (FruitBuilder)clone();
  }

  /**
   * Creates a new {@link Fruit} based on this builder's settings.
   *
   * @return the created Fruit
   */
  public Fruit build() {
    try {
      Fruit result = new Fruit();
      if (isSet$colour$java$lang$String) {
        result.colour = value$colour$java$lang$String;
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new java.lang.reflect.UndeclaredThrowableException(ex);
    }
  }
}
