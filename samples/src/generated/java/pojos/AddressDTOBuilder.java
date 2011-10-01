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

	private String[] street$java$lang$String = new String[] {};
	private String[] postCode$java$lang$String = new String[] {};
	private String[] city$java$lang$String = new String[] {};
	private String[] name$java$lang$String = new String[] {};

	
	/**
	 * Creates a new {@link AddressDTOBuilder}.
	 */
	public AddressDTOBuilder() {
	}
	
	/**
	 * Sets the default value for the {@link AddressDTO#street} property.
	 * @param value the default value
	 * @return this builder
	 */
	public AddressDTOBuilder withStreet( String value) {
		this.street$java$lang$String = new String[] { value };
		return this;
	}

	/**
	 * Sets the default values for the {@link AddressDTO#street} property.
	 * @param values the default values
	 * @return this builder
	 */
	public AddressDTOBuilder withStreetFrom( String... values) {
		if ( values == null) {
			throw new IllegalArgumentException("array of default values must not be null!");
		}
		this.street$java$lang$String = values;
		return this;
	}
	/**
	 * Sets the default value for the {@link AddressDTO#postCode} property.
	 * @param value the default value
	 * @return this builder
	 */
	public AddressDTOBuilder withPostCode( String value) {
		this.postCode$java$lang$String = new String[] { value };
		return this;
	}

	/**
	 * Sets the default values for the {@link AddressDTO#postCode} property.
	 * @param values the default values
	 * @return this builder
	 */
	public AddressDTOBuilder withPostCodeFrom( String... values) {
		if ( values == null) {
			throw new IllegalArgumentException("array of default values must not be null!");
		}
		this.postCode$java$lang$String = values;
		return this;
	}
	/**
	 * Sets the default value for the {@link AddressDTO#city} property.
	 * @param value the default value
	 * @return this builder
	 */
	public AddressDTOBuilder withCity( String value) {
		this.city$java$lang$String = new String[] { value };
		return this;
	}

	/**
	 * Sets the default values for the {@link AddressDTO#city} property.
	 * @param values the default values
	 * @return this builder
	 */
	public AddressDTOBuilder withCityFrom( String... values) {
		if ( values == null) {
			throw new IllegalArgumentException("array of default values must not be null!");
		}
		this.city$java$lang$String = values;
		return this;
	}
	/**
	 * Sets the default value for the {@link AddressDTO#name} property.
	 * @param value the default value
	 * @return this builder
	 */
	public AddressDTOBuilder withName( String value) {
		this.name$java$lang$String = new String[] { value };
		return this;
	}

	/**
	 * Sets the default values for the {@link AddressDTO#name} property.
	 * @param values the default values
	 * @return this builder
	 */
	public AddressDTOBuilder withNameFrom( String... values) {
		if ( values == null) {
			throw new IllegalArgumentException("array of default values must not be null!");
		}
		this.name$java$lang$String = values;
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
		if ( street$java$lang$String.length > 0) {
			result.street = street$java$lang$String[buildCount%street$java$lang$String.length];	
		}
		if ( postCode$java$lang$String.length > 0) {
			result.postCode = postCode$java$lang$String[buildCount%postCode$java$lang$String.length];	
		}
		if ( city$java$lang$String.length > 0) {
			result.city = city$java$lang$String[buildCount%city$java$lang$String.length];	
		}
		if ( name$java$lang$String.length > 0) {
			result.name = name$java$lang$String[buildCount%name$java$lang$String.length];	
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