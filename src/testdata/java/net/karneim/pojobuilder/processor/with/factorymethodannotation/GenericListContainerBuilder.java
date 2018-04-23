package net.karneim.pojobuilder.processor.with.factorymethodannotation;

import java.util.List;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class GenericListContainerBuilder<T>
    implements Cloneable {
  protected GenericListContainerBuilder<T> self;
  protected String value$content$java$lang$String;
  protected boolean isSet$content$java$lang$String;

  /**
   * Creates a new {@link GenericListContainerBuilder}.
   */
  public GenericListContainerBuilder() {
    self = (GenericListContainerBuilder<T>)this;
  }

  /**
   * Sets the default value for the content property.
   *
   * @param value the default value
   * @return this builder
   */
  public GenericListContainerBuilder<T> withContent(String value) {
    this.value$content$java$lang$String = value;
    this.isSet$content$java$lang$String = true;
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
      GenericListContainerBuilder<T> result = (GenericListContainerBuilder<T>)super.clone();
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
  public GenericListContainerBuilder<T> but() {
    return (GenericListContainerBuilder<T>)clone();
  }

  /**
   * Creates a new {@link Container} based on this builder's settings.
   *
   * @return the created Container
   */
  public Container<List<T>> build() {
    try {
      Container<List<T>> result = ContainerFactory.newGenericListContainer(value$content$java$lang$String);
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
