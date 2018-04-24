package samples;

import java.net.URL;
import java.net.URLStreamHandler;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class UrlBuilder
    implements Cloneable {
  protected UrlBuilder self;
  protected String value$protocol$java$lang$String;
  protected boolean isSet$protocol$java$lang$String;
  protected String value$host$java$lang$String;
  protected boolean isSet$host$java$lang$String;
  protected int value$port$int;
  protected boolean isSet$port$int;
  protected String value$file$java$lang$String;
  protected boolean isSet$file$java$lang$String;
  protected URLStreamHandler value$handler$java$net$URLStreamHandler;
  protected boolean isSet$handler$java$net$URLStreamHandler;

  /**
   * Creates a new {@link UrlBuilder}.
   */
  public UrlBuilder() {
    self = (UrlBuilder)this;
  }

  /**
   * Sets the default value for the protocol property.
   *
   * @param value the default value
   * @return this builder
   */
  public UrlBuilder withProtocol(String value) {
    this.value$protocol$java$lang$String = value;
    this.isSet$protocol$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default value for the host property.
   *
   * @param value the default value
   * @return this builder
   */
  public UrlBuilder withHost(String value) {
    this.value$host$java$lang$String = value;
    this.isSet$host$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default value for the port property.
   *
   * @param value the default value
   * @return this builder
   */
  public UrlBuilder withPort(int value) {
    this.value$port$int = value;
    this.isSet$port$int = true;
    return self;
  }

  /**
   * Sets the default value for the file property.
   *
   * @param value the default value
   * @return this builder
   */
  public UrlBuilder withFile(String value) {
    this.value$file$java$lang$String = value;
    this.isSet$file$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default value for the handler property.
   *
   * @param value the default value
   * @return this builder
   */
  public UrlBuilder withHandler(URLStreamHandler value) {
    this.value$handler$java$net$URLStreamHandler = value;
    this.isSet$handler$java$net$URLStreamHandler = true;
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
      UrlBuilder result = (UrlBuilder)super.clone();
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
  public UrlBuilder but() {
    return (UrlBuilder)clone();
  }

  /**
   * Creates a new {@link URL} based on this builder's settings.
   *
   * @return the created URL
   */
  public URL build() {
    try {
      URL result = UrlFactory.createUrl(value$protocol$java$lang$String, value$host$java$lang$String, value$port$int, value$file$java$lang$String, value$handler$java$net$URLStreamHandler);
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
