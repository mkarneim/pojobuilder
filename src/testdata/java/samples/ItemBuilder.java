package samples;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class ItemBuilder
    implements Builder<Item>, Cloneable {
  protected ItemBuilder self;
  protected int value$amount$int;
  protected boolean isSet$amount$int;
  protected Builder<? extends Integer> builder$amount$int;
  protected String value$article$java$lang$String;
  protected boolean isSet$article$java$lang$String;
  protected Builder<? extends String> builder$article$java$lang$String;

  /**
   * Creates a new {@link ItemBuilder}.
   */
  public ItemBuilder() {
    self = (ItemBuilder)this;
  }

  /**
   * Sets the default value for the {@link Item#amount} property.
   *
   * @param value the default value
   * @return this builder
   */
  public ItemBuilder withAmount(int value) {
    this.value$amount$int = value;
    this.isSet$amount$int = true;
    return self;
  }

  /**
   * Sets the default builder for the {@link Item#amount} property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public ItemBuilder withAmount(Builder<? extends Integer> builder) {
    this.builder$amount$int = builder;
    this.isSet$amount$int = false;
    return self;
  }

  /**
   * Sets the default value for the {@link Item#article} property.
   *
   * @param value the default value
   * @return this builder
   */
  public ItemBuilder withArticle(String value) {
    this.value$article$java$lang$String = value;
    this.isSet$article$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default builder for the {@link Item#article} property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public ItemBuilder withArticle(Builder<? extends String> builder) {
    this.builder$article$java$lang$String = builder;
    this.isSet$article$java$lang$String = false;
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
      ItemBuilder result = (ItemBuilder)super.clone();
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
  public ItemBuilder but() {
    return (ItemBuilder)clone();
  }

  /**
   * Creates a new {@link Item} based on this builder's settings.
   *
   * @return the created Item
   */
  @Override
  public Item build() {
    try {
      int _amount;
      if (!isSet$amount$int && builder$amount$int != null) {
        _amount = builder$amount$int.build();
      } else {
        _amount = value$amount$int;
      }
      String _article;
      if (!isSet$article$java$lang$String && builder$article$java$lang$String != null) {
        _article = builder$article$java$lang$String.build();
      } else {
        _article = value$article$java$lang$String;
      }
      Item result = new Item(_amount, _article);
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
