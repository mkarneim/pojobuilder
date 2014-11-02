package net.karneim.pojobuilder.processor.with.customannotation.builder;

import javax.annotation.Generated;
import net.karneim.pojobuilder.processor.with.customannotation.PojoA;

@Generated("PojoBuilder")
public class FluentPojoABuilderA
    implements Cloneable {
  protected FluentPojoABuilderA self;
  protected String value$name$java$lang$String;
  protected boolean isSet$name$java$lang$String;

  /**
   * Creates a new {@link FluentPojoABuilderA}.
   */
  public FluentPojoABuilderA() {
    self = (FluentPojoABuilderA)this;
  }

  /**
   * Sets the default value for the {@link PojoA#name} property.
   *
   * @param value the default value
   * @return this builder
   */
  public FluentPojoABuilderA withName(String value) {
    this.value$name$java$lang$String = value;
    this.isSet$name$java$lang$String = true;
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
      FluentPojoABuilderA result = (FluentPojoABuilderA)super.clone();
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
  public FluentPojoABuilderA but() {
    return (FluentPojoABuilderA)clone();
  }

  /**
   * Creates a new {@link PojoA} based on this builder's settings.
   *
   * @return the created PojoA
   */
  public PojoA build() {
    try {
      PojoA result = new PojoA();
      if (isSet$name$java$lang$String) {
        result.name = value$name$java$lang$String;
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new java.lang.reflect.UndeclaredThrowableException(ex);
    }
  }
}
