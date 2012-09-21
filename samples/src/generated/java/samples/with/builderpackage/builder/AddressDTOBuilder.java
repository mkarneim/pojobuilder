package samples.with.builderpackage.builder;



import samples.with.builderpackage.AddressDTO;


/**
 * The {@link AddressDTOBuilder} is a Builder for {@link AddressDTO} objects.
 *
 * 
 *     Please DO NOT MODIFIY this class
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * 
 * @created by the PojoBuilder generator
 */
public class AddressDTOBuilder implements Cloneable {
	protected final AddressDTOBuilder self;
	protected String value$street$java$lang$String; 
	protected boolean isSet$street$java$lang$String = false; 

	protected String value$postCode$java$lang$String; 
	protected boolean isSet$postCode$java$lang$String = false; 

	protected String value$city$java$lang$String; 
	protected boolean isSet$city$java$lang$String = false; 

	protected String value$name$java$lang$String; 
	protected boolean isSet$name$java$lang$String = false; 



	/**
	 * Creates a new {@link AddressDTOBuilder}.
	 */
	public AddressDTOBuilder() {
		self = (AddressDTOBuilder)this;
	}

	/**
	 * Sets the default value for the {@link AddressDTO#street} property.
	 * @param value the default value
	 * @return this builder
	 */
	public AddressDTOBuilder withStreet( String value) {
		this.value$street$java$lang$String = value;
		this.isSet$street$java$lang$String = true;
		return self;
	}
	/**
	 * Sets the default value for the {@link AddressDTO#postCode} property.
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
			Object result = super.clone();
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
		AddressDTO result = new AddressDTO( );

		if ( this.isSet$street$java$lang$String) { 
			result.street = this.value$street$java$lang$String;	
		}
		if ( this.isSet$postCode$java$lang$String) { 
			result.postCode = this.value$postCode$java$lang$String;	
		}
		if ( this.isSet$city$java$lang$String) { 
			result.city = this.value$city$java$lang$String;	
		}
		if ( this.isSet$name$java$lang$String) { 
			result.name = this.value$name$java$lang$String;	
		}

		return result;
	}

}