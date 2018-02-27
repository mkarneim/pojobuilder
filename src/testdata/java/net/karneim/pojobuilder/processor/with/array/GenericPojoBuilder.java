package net.karneim.pojobuilder.processor.with.array;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class GenericPojoBuilder<T>
    implements Cloneable {
  protected GenericPojoBuilder<T> self;
  protected T[] value$someArray$T$L;
  protected boolean isSet$someArray$T$L;
  protected T[][] value$someMatrix$T$L$L;
  protected boolean isSet$someMatrix$T$L$L;
  protected GenericPojo<T>[] value$somePojos$net$karneim$pojobuilder$processor$with$array$GenericPojo$L;
  protected boolean isSet$somePojos$net$karneim$pojobuilder$processor$with$array$GenericPojo$L;

  /**
   * Creates a new {@link GenericPojoBuilder}.
   */
  public GenericPojoBuilder() {
    self = (GenericPojoBuilder<T>)this;
  }

  /**
   * Sets the default value for the {@link GenericPojo#someArray} property.
   *
   * @param value the default value
   * @return this builder
   */
  public GenericPojoBuilder<T> withSomeArray(T[] value) {
    this.value$someArray$T$L = value;
    this.isSet$someArray$T$L = true;
    return self;
  }

  /**
   * Sets the default value for the {@link GenericPojo#someMatrix} property.
   *
   * @param value the default value
   * @return this builder
   */
  public GenericPojoBuilder<T> withSomeMatrix(T[][] value) {
    this.value$someMatrix$T$L$L = value;
    this.isSet$someMatrix$T$L$L = true;
    return self;
  }

  /**
   * Sets the default value for the {@link GenericPojo#somePojos} property.
   *
   * @param value the default value
   * @return this builder
   */
  public GenericPojoBuilder<T> withSomePojos(GenericPojo<T>[] value) {
    this.value$somePojos$net$karneim$pojobuilder$processor$with$array$GenericPojo$L = value;
    this.isSet$somePojos$net$karneim$pojobuilder$processor$with$array$GenericPojo$L = true;
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
      GenericPojoBuilder<T> result = (GenericPojoBuilder<T>)super.clone();
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
  public GenericPojoBuilder<T> but() {
    return (GenericPojoBuilder<T>)clone();
  }

  /**
   * Creates a new {@link GenericPojo} based on this builder's settings.
   *
   * @return the created GenericPojo
   */
  public GenericPojo<T> build() {
    try {
      GenericPojo<T> result = new GenericPojo<T>();
      if (isSet$someArray$T$L) {
        result.someArray = value$someArray$T$L;
      }
      if (isSet$someMatrix$T$L$L) {
        result.someMatrix = value$someMatrix$T$L$L;
      }
      if (isSet$somePojos$net$karneim$pojobuilder$processor$with$array$GenericPojo$L) {
        result.somePojos = value$somePojos$net$karneim$pojobuilder$processor$with$array$GenericPojo$L;
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
