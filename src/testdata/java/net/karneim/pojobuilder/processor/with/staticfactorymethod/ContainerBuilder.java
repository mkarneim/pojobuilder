package net.karneim.pojobuilder.processor.with.staticfactorymethod;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class ContainerBuilder<T>
    implements Cloneable {
  protected ContainerBuilder<T> self;
  protected T value$value$T;
  protected boolean isSet$value$T;

  /**
   * Factory Method to construct a ContainerBuilder<T>
   *
   * @return a new ContainerBuilder<T>
   */
  public static <T> ContainerBuilder<T> container() {
    return new ContainerBuilder<T>();
  }

  /**
   * Creates a new {@link ContainerBuilder}.
   */
  public ContainerBuilder() {
    self = (ContainerBuilder<T>)this;
  }

  /**
   * Sets the default value for the value property.
   *
   * @param value the default value
   * @return this builder
   */
  public ContainerBuilder<T> withValue(T value) {
    this.value$value$T = value;
    this.isSet$value$T = true;
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
      ContainerBuilder<T> result = (ContainerBuilder<T>)super.clone();
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
  public ContainerBuilder<T> but() {
    return (ContainerBuilder<T>)clone();
  }

  /**
   * Creates a new {@link Container} based on this builder's settings.
   *
   * @return the created Container
   */
  public Container<T> build() {
    try {
      Container<T> result = new Container<T>();
      if (isSet$value$T) {
        result.setValue(value$value$T);
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
