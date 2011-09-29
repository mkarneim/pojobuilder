package pojos;

import pojos.AddressDTO;
import java.util.ArrayList;
import java.util.List;

import java.lang.String;


/**
 * The {@link AddressDTOBuilder} is a Builder for {@link AddressDTO} objects.
 *
 * @created  by the PojoBuilder generator
 */
public class AddressDTOBuilder implements Cloneable {
	/*
	 * Counter of {@link #build} invocations. 
	 */
	private int buildCount = 0;

	private String[] address2$java$lang$String = new String[] {};
	private String[] zip$java$lang$String = new String[] {};
	private String[] address1$java$lang$String = new String[] {};
	private String[] country$java$lang$String = new String[] {};

	
	/**
	 * Creates a new {@link AddressDTOBuilder}.
	 */
	public AddressDTOBuilder() {
	}
	
	/**
	 * Sets the default value for the {@link AddressDTO#address2$java$lang$String} property.
	 * @param value the default value
	 * @return this builder
	 */
	public AddressDTOBuilder withAddress2( String value) {
		this.address2$java$lang$String = new String[] { value };
		return this;
	}

	/**
	 * Sets the default values for the {@link AddressDTO#address2$java$lang$String} property.
	 * @param values the default values
	 * @return this builder
	 */
	public AddressDTOBuilder withAddress2From( String... values) {
	if ( values == null) {
		throw new IllegalArgumentException("array of default values must not be null!");
	}
		this.address2$java$lang$String = values;
		return this;
	}
	/**
	 * Sets the default value for the {@link AddressDTO#zip$java$lang$String} property.
	 * @param value the default value
	 * @return this builder
	 */
	public AddressDTOBuilder withZip( String value) {
		this.zip$java$lang$String = new String[] { value };
		return this;
	}

	/**
	 * Sets the default values for the {@link AddressDTO#zip$java$lang$String} property.
	 * @param values the default values
	 * @return this builder
	 */
	public AddressDTOBuilder withZipFrom( String... values) {
	if ( values == null) {
		throw new IllegalArgumentException("array of default values must not be null!");
	}
		this.zip$java$lang$String = values;
		return this;
	}
	/**
	 * Sets the default value for the {@link AddressDTO#address1$java$lang$String} property.
	 * @param value the default value
	 * @return this builder
	 */
	public AddressDTOBuilder withAddress1( String value) {
		this.address1$java$lang$String = new String[] { value };
		return this;
	}

	/**
	 * Sets the default values for the {@link AddressDTO#address1$java$lang$String} property.
	 * @param values the default values
	 * @return this builder
	 */
	public AddressDTOBuilder withAddress1From( String... values) {
	if ( values == null) {
		throw new IllegalArgumentException("array of default values must not be null!");
	}
		this.address1$java$lang$String = values;
		return this;
	}
	/**
	 * Sets the default value for the {@link AddressDTO#country$java$lang$String} property.
	 * @param value the default value
	 * @return this builder
	 */
	public AddressDTOBuilder withCountry( String value) {
		this.country$java$lang$String = new String[] { value };
		return this;
	}

	/**
	 * Sets the default values for the {@link AddressDTO#country$java$lang$String} property.
	 * @param values the default values
	 * @return this builder
	 */
	public AddressDTOBuilder withCountryFrom( String... values) {
	if ( values == null) {
		throw new IllegalArgumentException("array of default values must not be null!");
	}
		this.country$java$lang$String = values;
		return this;
	}

	
	/**
	 * Returns a clone of this builder.
	 * @return the clone
	 */
	@Override
	public Object clone() {
		try {
			AddressDTOBuilder result = (AddressDTOBuilder)super.clone();
			result.buildCount = 0;
			return result;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.getMessage());
		}        
	}
    
    /**
     * Returns a clone of this builder.
     * @return the clone
     */
	public AddressDTOBuilder override() {
		return (AddressDTOBuilder)clone();
	}
	
	/**
	 * Creates a new {@link AddressDTO} based on this builders settings.
	 * @return the created AddressDTO
	 */
	public AddressDTO build() {
		AddressDTO result = new AddressDTO( );
		if ( address2$java$lang$String.length > 0) {
			result.address2 = address2$java$lang$String[buildCount%address2$java$lang$String.length];	
		}
		if ( zip$java$lang$String.length > 0) {
			result.zip = zip$java$lang$String[buildCount%zip$java$lang$String.length];	
		}
		if ( address1$java$lang$String.length > 0) {
			result.address1 = address1$java$lang$String[buildCount%address1$java$lang$String.length];	
		}
		if ( country$java$lang$String.length > 0) {
			result.country = country$java$lang$String[buildCount%country$java$lang$String.length];	
		}

		
		buildCount++;    
		return result;
	}
	
	/**
	 * Creates an array of new {@link AddressDTO} objects with the given number of elements.
	 * @param size the number of elements
	 * @return the array containing the new objects
	 */
	public AddressDTO[] buildArray(int size) {
		AddressDTO[] result = new AddressDTO[size];
		for( int i=0; i<size; ++i) {
			result[i] = build();
		}
		return result;
	}
	
	/**
	 * Creates a list of new {@link AddressDTO} objects with the given number of elements.
	 * @param size the number of elements
	 * @return the list containing the new objects
	 */
	public List<AddressDTO> buildList(int size) { 
		List<AddressDTO> result = new ArrayList<AddressDTO>(size);
		for( int i=0; i<size; ++i) {
			result.add( build());
		}
		return result;
	}

}