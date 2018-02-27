package net.karneim.pojobuilder.processor.with.generics;

import java.io.File;
import java.util.List;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class PojoBuilder<T>
    implements Cloneable {
  protected PojoBuilder<T> self;
  protected T value$someElement$T;
  protected boolean isSet$someElement$T;
  protected List<T> value$someList$java$util$List;
  protected boolean isSet$someList$java$util$List;
  protected List<File> value$someFileList$java$util$List;
  protected boolean isSet$someFileList$java$util$List;
  protected List<? extends T> value$someWildcardList$java$util$List;
  protected boolean isSet$someWildcardList$java$util$List;
  protected Pojo<T> value$somePojo$net$karneim$pojobuilder$processor$with$generics$Pojo;
  protected boolean isSet$somePojo$net$karneim$pojobuilder$processor$with$generics$Pojo;

  /**
   * Creates a new {@link PojoBuilder}.
   */
  public PojoBuilder() {
    self = (PojoBuilder<T>)this;
  }

  /**
   * Sets the default value for the {@link Pojo#someElement} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoBuilder<T> withSomeElement(T value) {
    this.value$someElement$T = value;
    this.isSet$someElement$T = true;
    return self;
  }

  /**
   * Sets the default value for the {@link Pojo#someList} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoBuilder<T> withSomeList(List<T> value) {
    this.value$someList$java$util$List = value;
    this.isSet$someList$java$util$List = true;
    return self;
  }

  /**
   * Sets the default value for the {@link Pojo#someFileList} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoBuilder<T> withSomeFileList(List<File> value) {
    this.value$someFileList$java$util$List = value;
    this.isSet$someFileList$java$util$List = true;
    return self;
  }

  /**
   * Sets the default value for the {@link Pojo#someWildcardList} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoBuilder<T> withSomeWildcardList(List<? extends T> value) {
    this.value$someWildcardList$java$util$List = value;
    this.isSet$someWildcardList$java$util$List = true;
    return self;
  }

  /**
   * Sets the default value for the {@link Pojo#somePojo} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoBuilder<T> withSomePojo(Pojo<T> value) {
    this.value$somePojo$net$karneim$pojobuilder$processor$with$generics$Pojo = value;
    this.isSet$somePojo$net$karneim$pojobuilder$processor$with$generics$Pojo = true;
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
      PojoBuilder<T> result = (PojoBuilder<T>)super.clone();
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
  public PojoBuilder<T> but() {
    return (PojoBuilder<T>)clone();
  }

  /**
   * Creates a new {@link Pojo} based on this builder's settings.
   *
   * @return the created Pojo
   */
  public Pojo<T> build() {
    try {
      Pojo<T> result = new Pojo<T>();
      if (isSet$someElement$T) {
        result.someElement = value$someElement$T;
      }
      if (isSet$someList$java$util$List) {
        result.someList = value$someList$java$util$List;
      }
      if (isSet$someFileList$java$util$List) {
        result.someFileList = value$someFileList$java$util$List;
      }
      if (isSet$someWildcardList$java$util$List) {
        result.someWildcardList = value$someWildcardList$java$util$List;
      }
      if (isSet$somePojo$net$karneim$pojobuilder$processor$with$generics$Pojo) {
        result.somePojo = value$somePojo$net$karneim$pojobuilder$processor$with$generics$Pojo;
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
