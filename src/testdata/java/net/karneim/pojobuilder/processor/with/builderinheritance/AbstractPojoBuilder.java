package net.karneim.pojobuilder.processor.with.builderinheritance;

import javax.annotation.Generated;

@Generated("PojoBuilder")
public abstract class AbstractPojoBuilder
    implements Cloneable {
  protected AbstractPojoBuilder self;
  protected int value$field$int;
  protected boolean isSet$field$int;

  /**
   * Creates a new {@link AbstractPojoBuilder}.
   */
  public AbstractPojoBuilder() {
    self = (AbstractPojoBuilder)this;
  }

  /**
   * Sets the default value for the {@link AbstractPojo#field} property.
   *
   * @param value the default value
   * @return this builder
   */
  public AbstractPojoBuilder withField(int value) {
    this.value$field$int = value;
    this.isSet$field$int = true;
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
      AbstractPojoBuilder result = (AbstractPojoBuilder)super.clone();
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
  public AbstractPojoBuilder but() {
    return (AbstractPojoBuilder)clone();
  }

  /**
   * Creates a new {@link AbstractPojo} based on this builder's settings.
   *
   * @return the created AbstractPojo
   */
  public abstract AbstractPojo build();
}
