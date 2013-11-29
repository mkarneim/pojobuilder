package samples.with.copy;

import samples.with.copy.AddressDTO;
import javax.annotation.Generated;

/**
 * The {@link AddressDTOBuilder} is a Builder for {@link AddressDTO} objects.
 *
 * <p>
 *     This class has been generated.
 *     Please DO NOT MODIFIY this file
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * </p>
 */
@Generated("PojoBuilder")
public class AddressDTOBuilder implements Cloneable {
	protected AddressDTOBuilder self;
	protected String value$street$java$lang$String; // mandatory constructor parameter
	protected boolean isSet$street$java$lang$String = false; 

	protected boolean value$forSale$boolean; // mandatory constructor parameter
	protected boolean isSet$forSale$boolean = false; 

	protected String value$postCode$java$lang$String; // mandatory constructor parameter
	protected boolean isSet$postCode$java$lang$String = false; 

	protected String value$city$java$lang$String; // mandatory constructor parameter
	protected boolean isSet$city$java$lang$String = false; 

	protected String value$name$java$lang$String; // mandatory constructor parameter
	protected boolean isSet$name$java$lang$String = false; 



	/**
	 * Creates a new {@link AddressDTOBuilder}.
	 */
	public AddressDTOBuilder() {
		self = (AddressDTOBuilder)this;
	}

	/**
	 * Sets the default value for the {@link AddressDTO#street} property.
	DO STUFF
	 * @param value the default value
	 * @return this builder
	 */
	public AddressDTOBuilder withStreet( String value) {
		this.value$street$java$lang$String = value;
		this.isSet$street$java$lang$String = true;
		return self;
	}
	/**
	 * Sets the default value for the {@link AddressDTO#forSale} property.
	DO STUFF
	 * @param value the default value
	 * @return this builder
	 */
	public AddressDTOBuilder withForSale( boolean value) {
		this.value$forSale$boolean = value;
		this.isSet$forSale$boolean = true;
		return self;
	}
	/**
	 * Sets the default value for the {@link AddressDTO#postCode} property.
	DO STUFF
	 * @param value the default value
	 * @return this builder
	 */
	public AddressDTOBuilder withPostCode( String value) {
		this.value$postCode$java$lang$String = value;
		this.isSet$postCode$java$lang$String = true;
		return self;
	}
	/**
	 * Sets the default value for the {@link AddressDTO#city} property.
	DO STUFF
	 * @param value the default value
	 * @return this builder
	 */
	public AddressDTOBuilder withCity( String value) {
		this.value$city$java$lang$String = value;
		this.isSet$city$java$lang$String = true;
		return self;
	}
	/**
	 * Sets the default value for the {@link AddressDTO#name} property.
	DO STUFF
	 * @param value the default value
	 * @return this builder
	 */
	public AddressDTOBuilder withName( String value) {
		this.value$name$java$lang$String = value;
		this.isSet$name$java$lang$String = true;
		return self;
	}


	/**
	 * Returns a clone of this builder.
	 * @return the clone
	 */
	@Override	
	public Object clone() {
		try {
			AddressDTOBuilder result = (AddressDTOBuilder)super.clone();
			result.self = result;
			return result;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.getMessage());
		}        
	}

	/**
	 * Returns a clone of this builder.
	 * @return the clone
	 */
	public AddressDTOBuilder but() {
		return (AddressDTOBuilder)clone();
	}

	/**
	 * Creates a new {@link AddressDTO} based on this builder's settings.
	 * @return the created AddressDTO
	 */
	public AddressDTO build() {
		AddressDTO result = new AddressDTO( this.value$name$java$lang$String ,this.value$street$java$lang$String ,this.value$city$java$lang$String ,this.value$postCode$java$lang$String ,this.value$forSale$boolean );

		return result;
	}

	/**
 	* Copies properties from <i>original</i> into the builder
 	* @param original the object to be copied
 	* @return this builder
 	*/
	public AddressDTOBuilder copy(AddressDTO original) {
		this.withStreet(original.getStreet());
		this.withForSale(original.isForSale());
		this.withPostCode(original.getPostCode());
		this.withCity(original.getCity());
		this.withName(original.getName());

		return self;
	}
}