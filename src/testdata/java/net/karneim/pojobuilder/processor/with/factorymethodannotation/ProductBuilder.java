package net.karneim.pojobuilder.processor.with.factorymethodannotation;

import java.math.BigDecimal;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class ProductBuilder
    implements Cloneable {
  protected ProductBuilder self;
  protected String value$name$java$lang$String;
  protected boolean isSet$name$java$lang$String;
  protected BigDecimal value$price$java$math$BigDecimal;
  protected boolean isSet$price$java$math$BigDecimal;

  /**
   * Creates a new {@link ProductBuilder}.
   */
  public ProductBuilder() {
    self = (ProductBuilder)this;
  }

  /**
   * Sets the default value for the name property.
   *
   * @param value the default value
   * @return this builder
   */
  public ProductBuilder withName(String value) {
    this.value$name$java$lang$String = value;
    this.isSet$name$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default value for the price property.
   *
   * @param value the default value
   * @return this builder
   */
  public ProductBuilder withPrice(BigDecimal value) {
    this.value$price$java$math$BigDecimal = value;
    this.isSet$price$java$math$BigDecimal = true;
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
      ProductBuilder result = (ProductBuilder)super.clone();
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
  public ProductBuilder but() {
    return (ProductBuilder)clone();
  }

  /**
   * Creates a new {@link Product} based on this builder's settings.
   *
   * @return the created Product
   */
  public Product build() {
    try {
      Product result = ProductFactory.makeProduct(value$name$java$lang$String, value$price$java$math$BigDecimal);
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
