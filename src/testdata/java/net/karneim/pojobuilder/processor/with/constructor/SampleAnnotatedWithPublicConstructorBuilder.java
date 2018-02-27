package net.karneim.pojobuilder.processor.with.constructor;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class SampleAnnotatedWithPublicConstructorBuilder
    implements Cloneable {
  protected SampleAnnotatedWithPublicConstructorBuilder self;

  /**
   * Creates a new {@link SampleAnnotatedWithPublicConstructorBuilder}.
   */
  public SampleAnnotatedWithPublicConstructorBuilder() {
    self = (SampleAnnotatedWithPublicConstructorBuilder)this;
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
      SampleAnnotatedWithPublicConstructorBuilder result = (SampleAnnotatedWithPublicConstructorBuilder)super.clone();
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
  public SampleAnnotatedWithPublicConstructorBuilder but() {
    return (SampleAnnotatedWithPublicConstructorBuilder)clone();
  }

  /**
   * Creates a new {@link SampleAnnotatedWithPublicConstructor} based on this builder's settings.
   *
   * @return the created SampleAnnotatedWithPublicConstructor
   */
  public SampleAnnotatedWithPublicConstructor build() {
    try {
      SampleAnnotatedWithPublicConstructor result = new SampleAnnotatedWithPublicConstructor();
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
