package net.karneim.pojobuilder.processor.with.array;

import java.io.File;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class PojoBuilder
    implements Cloneable {
  protected PojoBuilder self;
  protected File[] value$someArray$java$io$File$L;
  protected boolean isSet$someArray$java$io$File$L;
  protected String[][] value$someMatrix$java$lang$String$L$L;
  protected boolean isSet$someMatrix$java$lang$String$L$L;
  protected Pojo[] value$pojoArray$net$karneim$pojobuilder$processor$with$array$Pojo$L;
  protected boolean isSet$pojoArray$net$karneim$pojobuilder$processor$with$array$Pojo$L;

  /**
   * Creates a new {@link PojoBuilder}.
   */
  public PojoBuilder() {
    self = (PojoBuilder)this;
  }

  /**
   * Sets the default value for the {@link Pojo#someArray} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoBuilder withSomeArray(File[] value) {
    this.value$someArray$java$io$File$L = value;
    this.isSet$someArray$java$io$File$L = true;
    return self;
  }

  /**
   * Sets the default value for the {@link Pojo#someMatrix} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoBuilder withSomeMatrix(String[][] value) {
    this.value$someMatrix$java$lang$String$L$L = value;
    this.isSet$someMatrix$java$lang$String$L$L = true;
    return self;
  }

  /**
   * Sets the default value for the {@link Pojo#pojoArray} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoBuilder withPojoArray(Pojo[] value) {
    this.value$pojoArray$net$karneim$pojobuilder$processor$with$array$Pojo$L = value;
    this.isSet$pojoArray$net$karneim$pojobuilder$processor$with$array$Pojo$L = true;
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
      PojoBuilder result = (PojoBuilder)super.clone();
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
  public PojoBuilder but() {
    return (PojoBuilder)clone();
  }

  /**
   * Creates a new {@link Pojo} based on this builder's settings.
   *
   * @return the created Pojo
   */
  public Pojo build() {
    try {
      Pojo result = new Pojo();
      if (isSet$someArray$java$io$File$L) {
        result.someArray = value$someArray$java$io$File$L;
      }
      if (isSet$someMatrix$java$lang$String$L$L) {
        result.someMatrix = value$someMatrix$java$lang$String$L$L;
      }
      if (isSet$pojoArray$net$karneim$pojobuilder$processor$with$array$Pojo$L) {
        result.pojoArray = value$pojoArray$net$karneim$pojobuilder$processor$with$array$Pojo$L;
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
