package samples;

import javaxfake.annotation.Generated;
import net.karneim.pojobuilder.GwtIncompatible;

@Generated("PojoBuilder")
public class AddressBuilder
    implements Builder<Address>, Cloneable {
  protected AddressBuilder self;
  protected String value$street$java$lang$String;
  protected boolean isSet$street$java$lang$String;
  protected Builder<? extends String> builder$street$java$lang$String;
  protected String value$city$java$lang$String;
  protected boolean isSet$city$java$lang$String;
  protected Builder<? extends String> builder$city$java$lang$String;
  protected String value$postCode$java$lang$String;
  protected boolean isSet$postCode$java$lang$String;
  protected Builder<? extends String> builder$postCode$java$lang$String;

  /**
   * Creates a new {@link AddressBuilder}.
   */
  public AddressBuilder() {
    self = (AddressBuilder)this;
  }

  /**
   * Sets the default value for the street property.
   *
   * @param value the default value
   * @return this builder
   */
  public AddressBuilder withStreet(String value) {
    this.value$street$java$lang$String = value;
    this.isSet$street$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default builder for the street property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public AddressBuilder withStreet(Builder<? extends String> builder) {
    this.builder$street$java$lang$String = builder;
    this.isSet$street$java$lang$String = false;
    return self;
  }

  /**
   * Sets the default value for the city property.
   *
   * @param value the default value
   * @return this builder
   */
  public AddressBuilder withCity(String value) {
    this.value$city$java$lang$String = value;
    this.isSet$city$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default builder for the city property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public AddressBuilder withCity(Builder<? extends String> builder) {
    this.builder$city$java$lang$String = builder;
    this.isSet$city$java$lang$String = false;
    return self;
  }

  /**
   * Sets the default value for the postCode property.
   *
   * @param value the default value
   * @return this builder
   */
  public AddressBuilder withPostCode(String value) {
    this.value$postCode$java$lang$String = value;
    this.isSet$postCode$java$lang$String = true;
    return self;
  }

  /**
   * Sets the default builder for the postCode property.
   *
   * @param builder the default builder
   * @return this builder
   */
  public AddressBuilder withPostCode(Builder<? extends String> builder) {
    this.builder$postCode$java$lang$String = builder;
    this.isSet$postCode$java$lang$String = false;
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
      AddressBuilder result = (AddressBuilder)super.clone();
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
  public AddressBuilder but() {
    return (AddressBuilder)clone();
  }

  /**
   * Creates a new {@link Address} based on this builder's settings.
   *
   * @return the created Address
   */
  @Override
  public Address build() {
    try {
      Address result = new Address();
      if (isSet$street$java$lang$String) {
        result.setStreet(value$street$java$lang$String);
      } else if (builder$street$java$lang$String != null) {
        result.setStreet(builder$street$java$lang$String.build());
      }
      if (isSet$city$java$lang$String) {
        result.setCity(value$city$java$lang$String);
      } else if (builder$city$java$lang$String != null) {
        result.setCity(builder$city$java$lang$String.build());
      }
      if (isSet$postCode$java$lang$String) {
        result.setPostCode(value$postCode$java$lang$String);
      } else if (builder$postCode$java$lang$String != null) {
        result.setPostCode(builder$postCode$java$lang$String.build());
      }
      return result;
    } catch (RuntimeException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
