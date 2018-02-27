package net.karneim.pojobuilder.processor.with.constructor;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class SampleAnnotatedWithPrivateConstructorBuilder
    implements Cloneable {
  protected SampleAnnotatedWithPrivateConstructorBuilder self;

  /**
   * Factory Method to construct a SampleAnnotatedWithPrivateConstructorBuilder
   *
   * @return a new SampleAnnotatedWithPrivateConstructorBuilder
   */
  public static SampleAnnotatedWithPrivateConstructorBuilder sampleAnnotatedWithPrivateConstructor() {
    return new SampleAnnotatedWithPrivateConstructorBuilder();
  }

  /**
   * Creates a new {@link SampleAnnotatedWithPrivateConstructorBuilder}.
   */
  private SampleAnnotatedWithPrivateConstructorBuilder() {
    self = (SampleAnnotatedWithPrivateConstructorBuilder)this;
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
      SampleAnnotatedWithPrivateConstructorBuilder result = (SampleAnnotatedWithPrivateConstructorBuilder)super.clone();
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
  public SampleAnnotatedWithPrivateConstructorBuilder but() {
    return (SampleAnnotatedWithPrivateConstructorBuilder)clone();
  }

  /**
   * Creates a new {@link SampleAnnotatedWithPrivateConstructor} based on this builder's settings.
   *
   * @return the created SampleAnnotatedWithPrivateConstructor
   */
  public SampleAnnotatedWithPrivateConstructor build() {
    try {
      SampleAnnotatedWithPrivateConstructor result = new SampleAnnotatedWithPrivateConstructor();
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
