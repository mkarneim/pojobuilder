package samples;

import java.io.File;
import javax.annotation.Generated;

@Generated("PojoBuilder")
public class FileBuilder
    implements Cloneable {
  protected FileBuilder self;
  protected String value$path$java$lang$String;
  protected boolean isSet$path$java$lang$String;

  /**
   * Creates a new {@link FileBuilder}.
   */
  public FileBuilder() {
    self = (FileBuilder)this;
  }

  /**
   * Sets the default value for the {@link File#path} property.
   *
   * @param value the default value
   * @return this builder
   */
  public FileBuilder withPath(String value) {
    this.value$path$java$lang$String = value;
    this.isSet$path$java$lang$String = true;
    return self;
  }

  /**
   * Returns a clone of this builder.
   *
   * @return the clone
   */
  @Override
  public Object clone() {
    try {
      FileBuilder result = (FileBuilder)super.clone();
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
  public FileBuilder but() {
    return (FileBuilder)clone();
  }

  /**
   * Creates a new {@link File} based on this builder's settings.
   *
   * @return the created File
   */
  public File build() {
    try {
      File result = FileFactory.createFile(value$path$java$lang$String);
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Throwable t) {
      throw new java.lang.reflect.UndeclaredThrowableException(t);
    }
  }
}
