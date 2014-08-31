package net.karneim.pojobuilder.processor.with.factorymethodannotation;

import javax.annotation.Generated;

@Generated("PojoBuilder")
public class StringPairBuilder
    implements Cloneable {
  protected StringPairBuilder self;
  protected String value$left$java$lang$String;
  protected boolean isSet$left$java$lang$String;
  protected String value$right$java$lang$String;
  protected boolean isSet$right$java$lang$String;

  /**
   * Creates a new {@link StringPairBuilder}.
   */
  public StringPairBuilder() {
    self = (StringPairBuilder)this;
  }

  /**
   * Sets the default value for the {@link Pair#left} property.
   *
   * @param value the default value
   * @return this builder
   */
  public StringPairBuilder withLeft(String value) {
    this.value$left$java$lang$String = value;
    this.isSet$left$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default value for the {@link Pair#right} property.
   *
   * @param value the default value
   * @return this builder
   */
  public StringPairBuilder withRight(String value) {
    this.value$right$java$lang$String = value;
    this.isSet$right$java$lang$String = true;
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
      StringPairBuilder result = (StringPairBuilder)super.clone();
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
  public StringPairBuilder but() {
    return (StringPairBuilder)clone();
  }

  /**
   * Creates a new {@link Pair} based on this builder's settings.
   *
   * @return the created Pair
   */
  public Pair<String, String> build() {
    try {
      Pair<String, String> result = PairFactory.createStringPair(value$left$java$lang$String, value$right$java$lang$String);
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Throwable t) {
      throw new java.lang.reflect.UndeclaredThrowableException(t);
    }
  }
}
