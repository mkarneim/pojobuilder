package net.karneim.pojobuilder.processor.with.builderinterface;

import java.io.File;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class Pojo3Builder
    implements Supplier<Pojo3>, Cloneable {
  protected Pojo3Builder self;
  protected File value$file$java$io$File;
  protected boolean isSet$file$java$io$File;
  protected Supplier<? extends File> builder$file$java$io$File;
  protected String value$name$java$lang$String;
  protected boolean isSet$name$java$lang$String;
  protected Supplier<? extends String> builder$name$java$lang$String;
  protected int value$age$int;
  protected boolean isSet$age$int;
  protected Supplier<? extends Integer> builder$age$int;

  /**
   * Creates a new {@link Pojo3Builder}.
   */
  public Pojo3Builder() {
    self = (Pojo3Builder)this;
  }

  /**
   * Sets the default value for the file property.
   *
   * @param value the default value
   * @return this builder
   */
  public Pojo3Builder withFile(File value) {
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
  public Pojo3Builder withFile(Supplier<? extends File> builder) {
    this.builder$file$java$io$File = builder;
    this.isSet$file$java$io$File = false;
    return self;
  }

  /**
   * Sets the default value for the {@link Pojo3#name} property.
   *
   * @param value the default value
   * @return this builder
   */
  public Pojo3Builder withName(String value) {
    this.value$name$java$lang$String = value;
    this.isSet$name$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default builder for the {@link Pojo3#name} property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public Pojo3Builder withName(Supplier<? extends String> builder) {
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
  public Pojo3Builder withAge(int value) {
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
  public Pojo3Builder withAge(Supplier<? extends Integer> builder) {
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
      Pojo3Builder result = (Pojo3Builder)super.clone();
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
  public Pojo3Builder but() {
    return (Pojo3Builder)clone();
  }

  /**
   * Creates a new {@link Pojo3} based on this builder's settings.
   *
   * @return the created Pojo3
   */
  @Override
  public Pojo3 get() {
    try {
      File _file;
      if (!isSet$file$java$io$File && builder$file$java$io$File != null) {
        _file = builder$file$java$io$File.get();
      } else {
        _file = value$file$java$io$File;
      }
      Pojo3 result = new Pojo3(_file);
      if (isSet$age$int) {
        result.setAge(value$age$int);
      } else if (builder$age$int != null) {
        result.setAge(builder$age$int.get());
      }
      if (isSet$name$java$lang$String) {
        result.name = value$name$java$lang$String;
      } else if (builder$name$java$lang$String != null) {
        result.name = builder$name$java$lang$String.get();
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
