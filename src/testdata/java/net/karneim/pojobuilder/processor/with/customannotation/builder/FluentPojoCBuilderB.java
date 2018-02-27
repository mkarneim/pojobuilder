package net.karneim.pojobuilder.processor.with.customannotation.builder;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;
import net.karneim.pojobuilder.processor.with.customannotation.PojoC;

@Generated("PojoBuilder")
public class FluentPojoCBuilderB
    implements Cloneable {
  protected FluentPojoCBuilderB self;
  protected String value$name$java$lang$String;
  protected boolean isSet$name$java$lang$String;

  /**
   * Creates a new {@link FluentPojoCBuilderB}.
   */
  public FluentPojoCBuilderB() {
    self = (FluentPojoCBuilderB)this;
  }

  /**
   * Sets the default value for the {@link PojoC#name} property.
   *
   * @param value the default value
   * @return this builder
   */
  public FluentPojoCBuilderB withName(String value) {
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
      FluentPojoCBuilderB result = (FluentPojoCBuilderB)super.clone();
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
  public FluentPojoCBuilderB but() {
    return (FluentPojoCBuilderB)clone();
  }

  /**
   * Copies the values from the given pojo into this builder.
   *
   * @param pojo
   * @return this builder
   */
  public FluentPojoCBuilderB copy(PojoC pojo) {
    withName(pojo.name);
    return self;
  }

  /**
   * Creates a new {@link PojoC} based on this builder's settings.
   *
   * @return the created PojoC
   */
  public PojoC build() {
    try {
      PojoC result = new PojoC();
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
