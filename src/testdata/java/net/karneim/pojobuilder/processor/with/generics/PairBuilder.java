package net.karneim.pojobuilder.processor.with.generics;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class PairBuilder<A extends Comparable<A>, B extends Number>
    implements Cloneable {
  protected PairBuilder<A, B> self;
  protected A value$valueA$A;
  protected boolean isSet$valueA$A;
  protected B value$valueB$B;
  protected boolean isSet$valueB$B;

  /**
   * Creates a new {@link PairBuilder}.
   */
  public PairBuilder() {
    self = (PairBuilder<A, B>)this;
  }

  /**
   * Sets the default value for the valueA property.
   *
   * @param value the default value
   * @return this builder
   */
  public PairBuilder<A, B> withValueA(A value) {
    this.value$valueA$A = value;
    this.isSet$valueA$A = true;
    return self;
  }

  /**
   * Sets the default value for the valueB property.
   *
   * @param value the default value
   * @return this builder
   */
  public PairBuilder<A, B> withValueB(B value) {
    this.value$valueB$B = value;
    this.isSet$valueB$B = true;
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
      @SuppressWarnings("unchecked")
      PairBuilder<A, B> result = (PairBuilder<A, B>)super.clone();
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
  @SuppressWarnings("unchecked")
  @GwtIncompatible
  public PairBuilder<A, B> but() {
    return (PairBuilder<A, B>)clone();
  }

  /**
   * Creates a new {@link Pair} based on this builder's settings.
   *
   * @return the created Pair
   */
  public Pair<A, B> build() {
    try {
      Pair<A, B> result = new Pair<A, B>();
      if (isSet$valueA$A) {
        result.setValueA(value$valueA$A);
      }
      if (isSet$valueB$B) {
        result.setValueB(value$valueB$B);
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
