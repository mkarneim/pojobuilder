package net.karneim.pojobuilder.processor.with.builderinterface;

import java.io.File;
import javax.annotation.Generated;

@Generated("PojoBuilder")
public class AnotherPojoBuilder
    implements Builder<Pojo>, Cloneable {
  protected AnotherPojoBuilder self;
  protected File value$file$java$io$File;
  protected boolean isSet$file$java$io$File;
  protected Builder<File> builder$file$java$io$File;
  protected int value$age$int;
  protected boolean isSet$age$int;
  protected Builder<Integer> builder$age$int;
  protected String value$name$java$lang$String;
  protected boolean isSet$name$java$lang$String;
  protected Builder<String> builder$name$java$lang$String;

  /**
   * Creates a new {@link AnotherPojoBuilder}.
   */
  public AnotherPojoBuilder() {
    self = (AnotherPojoBuilder)this;
  }

  /**
   * Sets the default value for the {@link Pojo#file} property.
   *
   * @param value the default value
   * @return this builder
   */
  public AnotherPojoBuilder withFile(File value) {
    this.value$file$java$io$File = value;
    this.isSet$file$java$io$File = true;
    return self;
  }

  /**
   * Sets the default builder for the {@link Pojo#file} property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public AnotherPojoBuilder withFile(Builder<File> builder) {
    this.builder$file$java$io$File = builder;
    this.isSet$file$java$io$File = false;
    return self;
  }

  /**
   * Sets the default value for the {@link Pojo#age} property.
   *
   * @param value the default value
   * @return this builder
   */
  public AnotherPojoBuilder withAge(int value) {
    this.value$age$int = value;
    this.isSet$age$int = true;
    return self;
  }

  /**
   * Sets the default builder for the {@link Pojo#age} property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public AnotherPojoBuilder withAge(Builder<Integer> builder) {
    this.builder$age$int = builder;
    this.isSet$age$int = false;
    return self;
  }

  /**
   * Sets the default value for the {@link Pojo#name} property.
   *
   * @param value the default value
   * @return this builder
   */
  public AnotherPojoBuilder withName(String value) {
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
  public AnotherPojoBuilder withName(Builder<String> builder) {
    this.builder$name$java$lang$String = builder;
    this.isSet$name$java$lang$String = false;
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
      AnotherPojoBuilder result = (AnotherPojoBuilder)super.clone();
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
  public AnotherPojoBuilder but() {
    return (AnotherPojoBuilder)clone();
  }

  /**
   * Creates a new {@link Pojo} based on this builder's settings.
   *
   * @return the created Pojo
   */
  @Override
  public Pojo build() {
    try {
      File _file = !isSet$file$java$io$File && builder$file$java$io$File!=null?builder$file$java$io$File.build():value$file$java$io$File;
      int _age = !isSet$age$int && builder$age$int!=null?builder$age$int.build():value$age$int;
      Pojo result = PojoFactory.createPojo(_file, _age);
      if (isSet$name$java$lang$String) {
        result.name = value$name$java$lang$String;
      } else if (builder$name$java$lang$String!=null) {
        result.name = builder$name$java$lang$String.build();
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new java.lang.reflect.UndeclaredThrowableException(ex);
    }
  }
}
