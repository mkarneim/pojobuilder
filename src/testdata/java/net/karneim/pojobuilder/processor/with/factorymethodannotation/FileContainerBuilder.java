package net.karneim.pojobuilder.processor.with.factorymethodannotation;

import java.io.File;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class FileContainerBuilder
    implements Cloneable {
  protected FileContainerBuilder self;
  protected File value$content$java$io$File;
  protected boolean isSet$content$java$io$File;

  /**
   * Creates a new {@link FileContainerBuilder}.
   */
  public FileContainerBuilder() {
    self = (FileContainerBuilder)this;
  }

  /**
   * Sets the default value for the content property.
   *
   * @param value the default value
   * @return this builder
   */
  public FileContainerBuilder withContent(File value) {
    this.value$content$java$io$File = value;
    this.isSet$content$java$io$File = true;
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
      FileContainerBuilder result = (FileContainerBuilder)super.clone();
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
  public FileContainerBuilder but() {
    return (FileContainerBuilder)clone();
  }

  /**
   * Creates a new {@link Container} based on this builder's settings.
   *
   * @return the created Container
   */
  public Container<File> build() {
    try {
      Container<File> result = ContainerFactory.createFileContainer(value$content$java$io$File);
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
