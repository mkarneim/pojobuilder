package samples;

import javax.annotation.Generated;

@Generated("PojoBuilder")
public class ItemBuilder
    implements Builder<Item>, Cloneable {
  protected ItemBuilder self;
  protected int value$amount$int;
  protected boolean isSet$amount$int;
  protected Builder<Integer> builder$amount$int;
  protected String value$article$java$lang$String;
  protected boolean isSet$article$java$lang$String;
  protected Builder<String> builder$article$java$lang$String;

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
  public ItemBuilder withAmount(Builder<Integer> builder) {
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
  public ItemBuilder withArticle(Builder<String> builder) {
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
      int _amount = !isSet$amount$int && builder$amount$int!=null?builder$amount$int.build():value$amount$int;
      String _article = !isSet$article$java$lang$String && builder$article$java$lang$String!=null?builder$article$java$lang$String.build():value$article$java$lang$String;
      Item result = new Item(_amount, _article);
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Throwable t) {
      throw new java.lang.reflect.UndeclaredThrowableException(t);
    }
  }
}
