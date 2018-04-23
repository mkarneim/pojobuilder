package samples;

import java.io.File;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class FileBuilder
    implements Cloneable {
  protected FileBuilder self;
  protected String value$path$java$lang$String;
  protected boolean isSet$path$java$lang$String;
  protected long value$lastModified$long;
  protected boolean isSet$lastModified$long;
  protected boolean value$writable$boolean;
  protected boolean isSet$writable$boolean;
  protected boolean value$readable$boolean;
  protected boolean isSet$readable$boolean;
  protected boolean value$executable$boolean;
  protected boolean isSet$executable$boolean;

  /**
   * Creates a new {@link FileBuilder}.
   */
  public FileBuilder() {
    self = (FileBuilder)this;
  }

  /**
   * Sets the default value for the path property.
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
   * Sets the default value for the lastModified property.
   *
   * @param value the default value
   * @return this builder
   */
  public FileBuilder withLastModified(long value) {
    this.value$lastModified$long = value;
    this.isSet$lastModified$long = true;
    return self;
  }

  /**
   * Sets the default value for the writable property.
   *
   * @param value the default value
   * @return this builder
   */
  public FileBuilder withWritable(boolean value) {
    this.value$writable$boolean = value;
    this.isSet$writable$boolean = true;
    return self;
  }

  /**
   * Sets the default value for the readable property.
   *
   * @param value the default value
   * @return this builder
   */
  public FileBuilder withReadable(boolean value) {
    this.value$readable$boolean = value;
    this.isSet$readable$boolean = true;
    return self;
  }

  /**
   * Sets the default value for the executable property.
   *
   * @param value the default value
   * @return this builder
   */
  public FileBuilder withExecutable(boolean value) {
    this.value$executable$boolean = value;
    this.isSet$executable$boolean = true;
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
  @GwtIncompatible
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
      if (isSet$lastModified$long) {
        result.setLastModified(value$lastModified$long);
      }
      if (isSet$writable$boolean) {
        result.setWritable(value$writable$boolean);
      }
      if (isSet$readable$boolean) {
        result.setReadable(value$readable$boolean);
      }
      if (isSet$executable$boolean) {
        result.setExecutable(value$executable$boolean);
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
