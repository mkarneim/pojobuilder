package net.karneim.pojobuilder.processor.with.customannotation.builder;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;
import net.karneim.pojobuilder.processor.with.customannotation.PojoAB;

@Generated("PojoBuilder")
public class FluentPojoABBuilderB
    implements Cloneable {
  protected FluentPojoABBuilderB self;
  protected String value$name$java$lang$String;
  protected boolean isSet$name$java$lang$String;

  /**
   * Creates a new {@link FluentPojoABBuilderB}.
   */
  public FluentPojoABBuilderB() {
    self = (FluentPojoABBuilderB)this;
  }

  /**
   * Sets the default value for the {@link PojoAB#name} property.
   *
   * @param value the default value
   * @return this builder
   */
  public FluentPojoABBuilderB withName(String value) {
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
  @GwtIncompatible
  public Object clone() {
    try {
      FluentPojoABBuilderB result = (FluentPojoABBuilderB)super.clone();
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
  public FluentPojoABBuilderB but() {
    return (FluentPojoABBuilderB)clone();
  }

  /**
   * Copies the values from the given pojo into this builder.
   *
   * @param pojo
   * @return this builder
   */
  public FluentPojoABBuilderB copy(PojoAB pojo) {
    withName(pojo.name);
    return self;
  }

  /**
   * Creates a new {@link PojoAB} based on this builder's settings.
   *
   * @return the created PojoAB
   */
  public PojoAB build() {
    try {
      PojoAB result = new PojoAB();
      if (isSet$name$java$lang$String) {
        result.name = value$name$java$lang$String;
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
