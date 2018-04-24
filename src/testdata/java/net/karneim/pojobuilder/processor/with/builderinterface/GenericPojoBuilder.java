package net.karneim.pojobuilder.processor.with.builderinterface;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class GenericPojoBuilder<P extends Number>
    implements Builder<GenericPojo<P>>, Cloneable {
  protected GenericPojoBuilder<P> self;
  protected String value$name$java$lang$String;
  protected boolean isSet$name$java$lang$String;
  protected Builder<? extends String> builder$name$java$lang$String;
  protected P value$content$P;
  protected boolean isSet$content$P;
  protected Builder<? extends P> builder$content$P;

  /**
   * Creates a new {@link GenericPojoBuilder}.
   */
  public GenericPojoBuilder() {
    self = (GenericPojoBuilder<P>)this;
  }

  /**
   * Sets the default value for the {@link GenericPojo#name} property.
   *
   * @param value the default value
   * @return this builder
   */
  public GenericPojoBuilder<P> withName(String value) {
    this.value$name$java$lang$String = value;
    this.isSet$name$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default builder for the {@link GenericPojo#name} property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public GenericPojoBuilder<P> withName(Builder<? extends String> builder) {
    this.builder$name$java$lang$String = builder;
    this.isSet$name$java$lang$String = false;
    return self;
  }

  /**
   * Sets the default value for the content property.
   *
   * @param value the default value
   * @return this builder
   */
  public GenericPojoBuilder<P> withContent(P value) {
    this.value$content$P = value;
    this.isSet$content$P = true;
    return self;
  }

  /**
   * Sets the default builder for the content property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public GenericPojoBuilder<P> withContent(Builder<? extends P> builder) {
    this.builder$content$P = builder;
    this.isSet$content$P = false;
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
      GenericPojoBuilder<P> result = (GenericPojoBuilder<P>)super.clone();
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
  public GenericPojoBuilder<P> but() {
    return (GenericPojoBuilder<P>)clone();
  }

  /**
   * Creates a new {@link GenericPojo} based on this builder's settings.
   *
   * @return the created GenericPojo
   */
  @Override
  public GenericPojo<P> build() {
    try {
      String _name;
      if (!isSet$name$java$lang$String && builder$name$java$lang$String != null) {
        _name = builder$name$java$lang$String.build();
      } else {
        _name = value$name$java$lang$String;
      }
      GenericPojo<P> result = new GenericPojo<P>(_name);
      if (isSet$content$P) {
        result.setContent(value$content$P);
      } else if (builder$content$P != null) {
        result.setContent(builder$content$P.build());
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
