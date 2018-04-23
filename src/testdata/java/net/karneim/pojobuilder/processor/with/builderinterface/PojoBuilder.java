package net.karneim.pojobuilder.processor.with.builderinterface;

import java.io.File;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class PojoBuilder
    implements Builder<Pojo>, Cloneable {
  protected PojoBuilder self;
  protected File value$file$java$io$File;
  protected boolean isSet$file$java$io$File;
  protected Builder<? extends File> builder$file$java$io$File;
  protected String value$name$java$lang$String;
  protected boolean isSet$name$java$lang$String;
  protected Builder<? extends String> builder$name$java$lang$String;
  protected int value$age$int;
  protected boolean isSet$age$int;
  protected Builder<? extends Integer> builder$age$int;

  /**
   * Creates a new {@link PojoBuilder}.
   */
  public PojoBuilder() {
    self = (PojoBuilder)this;
  }

  /**
   * Sets the default value for the file property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoBuilder withFile(File value) {
    this.value$file$java$io$File = value;
    this.isSet$file$java$io$File = true;
    return self;
  }

  /**
   * Sets the default builder for the file property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public PojoBuilder withFile(Builder<? extends File> builder) {
    this.builder$file$java$io$File = builder;
    this.isSet$file$java$io$File = false;
    return self;
  }

  /**
   * Sets the default value for the {@link Pojo#name} property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoBuilder withName(String value) {
    this.value$name$java$lang$String = value;
    this.isSet$name$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default builder for the {@link Pojo#name} property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public PojoBuilder withName(Builder<? extends String> builder) {
    this.builder$name$java$lang$String = builder;
    this.isSet$name$java$lang$String = false;
    return self;
  }

  /**
   * Sets the default value for the age property.
   *
   * @param value the default value
   * @return this builder
   */
  public PojoBuilder withAge(int value) {
    this.value$age$int = value;
    this.isSet$age$int = true;
    return self;
  }

  /**
   * Sets the default builder for the age property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public PojoBuilder withAge(Builder<? extends Integer> builder) {
    this.builder$age$int = builder;
    this.isSet$age$int = false;
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
  @Override
  public Pojo build() {
    try {
      File _file;
      if (!isSet$file$java$io$File && builder$file$java$io$File != null) {
        _file = builder$file$java$io$File.build();
      } else {
        _file = value$file$java$io$File;
      }
      Pojo result = new Pojo(_file);
      if (isSet$age$int) {
        result.setAge(value$age$int);
      } else if (builder$age$int != null) {
        result.setAge(builder$age$int.build());
      }
      if (isSet$name$java$lang$String) {
        result.name = value$name$java$lang$String;
      } else if (builder$name$java$lang$String != null) {
        result.name = builder$name$java$lang$String.build();
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
