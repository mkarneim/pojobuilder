package samples;

import java.util.List;
import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class OrderBuilder
    implements Builder<Order>, Cloneable {
  protected OrderBuilder self;
  protected Recipient value$recipient$samples$Recipient;
  protected boolean isSet$recipient$samples$Recipient;
  protected Builder<? extends Recipient> builder$recipient$samples$Recipient;
  protected List<Item> value$items$java$util$List;
  protected boolean isSet$items$java$util$List;
  protected Builder<? extends List<Item>> builder$items$java$util$List;

  /**
   * Creates a new {@link OrderBuilder}.
   */
  public OrderBuilder() {
    self = (OrderBuilder)this;
  }

  /**
   * Sets the default value for the {@link Order#recipient} property.
   *
   * @param value the default value
   * @return this builder
   */
  public OrderBuilder withRecipient(Recipient value) {
    this.value$recipient$samples$Recipient = value;
    this.isSet$recipient$samples$Recipient = true;
    return self;
  }

  /**
   * Sets the default builder for the {@link Order#recipient} property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public OrderBuilder withRecipient(Builder<? extends Recipient> builder) {
    this.builder$recipient$samples$Recipient = builder;
    this.isSet$recipient$samples$Recipient = false;
    return self;
  }

  /**
   * Sets the default value for the {@link Order#items} property.
   *
   * @param value the default value
   * @return this builder
   */
  public OrderBuilder withItems(List<Item> value) {
    this.value$items$java$util$List = value;
    this.isSet$items$java$util$List = true;
    return self;
  }

  /**
   * Sets the default builder for the {@link Order#items} property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public OrderBuilder withItems(Builder<? extends List<Item>> builder) {
    this.builder$items$java$util$List = builder;
    this.isSet$items$java$util$List = false;
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
      OrderBuilder result = (OrderBuilder)super.clone();
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
  public OrderBuilder but() {
    return (OrderBuilder)clone();
  }

  /**
   * Creates a new {@link Order} based on this builder's settings.
   *
   * @return the created Order
   */
  @Override
  public Order build() {
    try {
      Order result = new Order();
      if (isSet$recipient$samples$Recipient) {
        result.recipient = value$recipient$samples$Recipient;
      } else if (builder$recipient$samples$Recipient != null) {
        result.recipient = builder$recipient$samples$Recipient.build();
      }
      if (isSet$items$java$util$List) {
        result.items = value$items$java$util$List;
      } else if (builder$items$java$util$List != null) {
        result.items = builder$items$java$util$List.build();
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
