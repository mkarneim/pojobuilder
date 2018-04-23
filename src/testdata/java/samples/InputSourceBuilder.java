package samples;

import java.io.InputStream;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;
import org.xml.sax.InputSource;

@Generated("PojoBuilder")
public class InputSourceBuilder
    implements Cloneable {
  protected InputSourceBuilder self;
  protected InputStream value$byteStream$java$io$InputStream;
  protected boolean isSet$byteStream$java$io$InputStream;
  protected String value$encoding$java$lang$String;
  protected boolean isSet$encoding$java$lang$String;

  /**
   * Creates a new {@link InputSourceBuilder}.
   */
  public InputSourceBuilder() {
    self = (InputSourceBuilder)this;
  }

  /**
   * Sets the default value for the byteStream property.
   *
   * @param value the default value
   * @return this builder
   */
  public InputSourceBuilder withByteStream(InputStream value) {
    this.value$byteStream$java$io$InputStream = value;
    this.isSet$byteStream$java$io$InputStream = true;
    return self;
  }

  /**
   * Sets the default value for the encoding property.
   *
   * @param value the default value
   * @return this builder
   */
  public InputSourceBuilder withEncoding(String value) {
    this.value$encoding$java$lang$String = value;
    this.isSet$encoding$java$lang$String = true;
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
      InputSourceBuilder result = (InputSourceBuilder)super.clone();
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
  public InputSourceBuilder but() {
    return (InputSourceBuilder)clone();
  }

  /**
   * Creates a new {@link InputSource} based on this builder's settings.
   *
   * @return the created InputSource
   */
  public InputSource build() {
    try {
      InputSource result = InputSourceFactory.createInputSource(value$byteStream$java$io$InputStream);
      if (isSet$encoding$java$lang$String) {
        result.setEncoding(value$encoding$java$lang$String);
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
