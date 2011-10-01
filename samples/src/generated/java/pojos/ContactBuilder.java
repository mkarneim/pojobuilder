package pojos;

import pojos.Contact;
import java.util.ArrayList;
import java.util.List;

import java.lang.String;


/**
 * The {@link ContactBuilder} is a Builder for {@link Contact} objects.
 *
 * @created  by the PojoBuilder generator
 */
public class ContactBuilder implements Cloneable {
	/*
	 * Counter of {@link #build} invocations. 
	 */
	private int buildCount = 0;

	private String[] email$java$lang$String = new String[] {};
	private String[] name$java$lang$String = new String[] {null}; // constructor parameter

	
	/**
	 * Creates a new {@link ContactBuilder}.
	 */
	public ContactBuilder() {
	}
	
	/**
	 * Sets the default value for the {@link Contact#email} property.
	 * @param value the default value
	 * @return this builder
	 */
	public ContactBuilder withEmail( String value) {
		this.email$java$lang$String = new String[] { value };
		return this;
	}

	/**
	 * Sets the default values for the {@link Contact#email} property.
	 * @param values the default values
	 * @return this builder
	 */
	public ContactBuilder withEmailFrom( String... values) {
		if ( values == null) {
			throw new IllegalArgumentException("array of default values must not be null!");
		}
		this.email$java$lang$String = values;
		return this;
	}
	/**
	 * Sets the default value for the {@link Contact#name} property.
	 * @param value the default value
	 * @return this builder
	 */
	public ContactBuilder withName( String value) {
		this.name$java$lang$String = new String[] { value };
		return this;
	}

	/**
	 * Sets the default values for the {@link Contact#name} property.
	 * @param values the default values
	 * @return this builder
	 */
	public ContactBuilder withNameFrom( String... values) {
		if ( values == null) {
			throw new IllegalArgumentException("array of default values must not be null!");
		}
		if ( values.length == 0) {
			throw new IllegalArgumentException("array of default values for a constructor parameter must contain at least one element");
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
			ContactBuilder result = (ContactBuilder)super.clone();
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
	public ContactBuilder override() {
		return (ContactBuilder)clone();
	}
	
	/**
	 * Creates a new {@link Contact} based on this builders settings.
	 * @return the created Contact
	 */
	public Contact build() {
		Contact result = new Contact( name$java$lang$String[buildCount%name$java$lang$String.length]);
		if ( email$java$lang$String.length > 0) {    
			result.setEmail( email$java$lang$String[buildCount%email$java$lang$String.length]);		
		}

		
		buildCount++;    
		return result;
	}
	
	/**
	 * Creates an array of new {@link Contact} objects with the given number of elements.
	 * @param size the number of elements
	 * @return the array containing the new objects
	 */
	public Contact[] buildArray(int size) {
		Contact[] result = new Contact[size];
		for( int i=0; i<size; ++i) {
			result[i] = build();
		}
		return result;
	}
	
	/**
	 * Creates a list of new {@link Contact} objects with the given number of elements.
	 * @param size the number of elements
	 * @return the list containing the new objects
	 */
	public List<Contact> buildList(int size) { 
		List<Contact> result = new ArrayList<Contact>(size);
		for( int i=0; i<size; ++i) {
			result.add( build());
		}
		return result;
	}

}