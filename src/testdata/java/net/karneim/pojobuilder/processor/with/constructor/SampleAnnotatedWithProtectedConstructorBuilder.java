package net.karneim.pojobuilder.processor.with.constructor;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class SampleAnnotatedWithProtectedConstructorBuilder
    implements Cloneable {
  protected SampleAnnotatedWithProtectedConstructorBuilder self;

  /**
   * Creates a new {@link SampleAnnotatedWithProtectedConstructorBuilder}.
   */
  protected SampleAnnotatedWithProtectedConstructorBuilder() {
    self = (SampleAnnotatedWithProtectedConstructorBuilder)this;
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
      SampleAnnotatedWithProtectedConstructorBuilder result = (SampleAnnotatedWithProtectedConstructorBuilder)super.clone();
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
  public SampleAnnotatedWithProtectedConstructorBuilder but() {
    return (SampleAnnotatedWithProtectedConstructorBuilder)clone();
  }

  /**
   * Creates a new {@link SampleAnnotatedWithProtectedConstructor} based on this builder's settings.
   *
   * @return the created SampleAnnotatedWithProtectedConstructor
   */
  public SampleAnnotatedWithProtectedConstructor build() {
    try {
      SampleAnnotatedWithProtectedConstructor result = new SampleAnnotatedWithProtectedConstructor();
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
