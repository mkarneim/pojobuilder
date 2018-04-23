package net.karneim.pojobuilder.processor.with.factorymethodannotation;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class ContainerBuilder<X extends Number>
    implements Cloneable {
  protected ContainerBuilder<X> self;
  protected X value$content$X;
  protected boolean isSet$content$X;

  /**
   * Creates a new {@link ContainerBuilder}.
   */
  public ContainerBuilder() {
    self = (ContainerBuilder<X>)this;
  }

  /**
   * Sets the default value for the content property.
   *
   * @param value the default value
   * @return this builder
   */
  public ContainerBuilder<X> withContent(X value) {
    this.value$content$X = value;
    this.isSet$content$X = true;
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
      ContainerBuilder<X> result = (ContainerBuilder<X>)super.clone();
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
  public ContainerBuilder<X> but() {
    return (ContainerBuilder<X>)clone();
  }

  /**
   * Creates a new {@link Container} based on this builder's settings.
   *
   * @return the created Container
   */
  public Container<X> build() {
    try {
      Container<X> result = ContainerFactory.createContainer(value$content$X);
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
