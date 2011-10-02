package pojos;

import pojos.User;
import java.util.ArrayList;
import java.util.List;

import java.lang.String;
import java.io.File;
import java.util.Date;


/**
 * The {@link UserBuilder} is a Builder for {@link User} objects.
 *
 * @created  by the PojoBuilder generator
 */
public class UserBuilder implements Cloneable {
	/*
	 * Counter of {@link #build} invocations. 
	 */
	private int buildCount = 0;

	private Date[] created$java$util$Date = new Date[] {null}; // constructor parameter
	private File[] homeDirectory$java$io$File = new File[] {};
	private String[] homeDirectory$java$lang$String = new String[] {};
	private long[] id$long = new long[] {0}; // constructor parameter
	private String[] passwordHash$java$lang$String = new String[] {};
	private String[] name$java$lang$String = new String[] {null}; // constructor parameter

	
	/**
	 * Creates a new {@link UserBuilder}.
	 */
	public UserBuilder() {
	}
	
	/**
	 * Sets the default value for the {@link User#created} property.
	 * @param value the default value
	 * @return this builder
	 */
	public UserBuilder withCreated( Date value) {
		this.created$java$util$Date = new Date[] { value };
		return this;
	}

	/**
	 * Sets the default values for the {@link User#created} property.
	 * @param values the default values
	 * @return this builder
	 */
	public UserBuilder withCreatedFrom( Date... values) {
		if ( values == null) {
			throw new IllegalArgumentException("array of default values must not be null!");
		}
		if ( values.length == 0) {
			throw new IllegalArgumentException("array of default values for a constructor parameter must contain at least one element");
		}
		this.created$java$util$Date = values;
		return this;
	}
	/**
	 * Sets the default value for the {@link User#homeDirectory} property.
	 * @param value the default value
	 * @return this builder
	 */
	public UserBuilder withHomeDirectory( File value) {
		this.homeDirectory$java$io$File = new File[] { value };
		return this;
	}

	/**
	 * Sets the default values for the {@link User#homeDirectory} property.
	 * @param values the default values
	 * @return this builder
	 */
	public UserBuilder withHomeDirectoryFrom( File... values) {
		if ( values == null) {
			throw new IllegalArgumentException("array of default values must not be null!");
		}
		this.homeDirectory$java$io$File = values;
		return this;
	}
	/**
	 * Sets the default value for the {@link User#homeDirectory} property.
	 * @param value the default value
	 * @return this builder
	 */
	public UserBuilder withHomeDirectory( String value) {
		this.homeDirectory$java$lang$String = new String[] { value };
		return this;
	}

	/**
	 * Sets the default values for the {@link User#homeDirectory} property.
	 * @param values the default values
	 * @return this builder
	 */
	public UserBuilder withHomeDirectoryFrom( String... values) {
		if ( values == null) {
			throw new IllegalArgumentException("array of default values must not be null!");
		}
		this.homeDirectory$java$lang$String = values;
		return this;
	}
	/**
	 * Sets the default value for the {@link User#id} property.
	 * @param value the default value
	 * @return this builder
	 */
	public UserBuilder withId( long value) {
		this.id$long = new long[] { value };
		return this;
	}

	/**
	 * Sets the default values for the {@link User#id} property.
	 * @param values the default values
	 * @return this builder
	 */
	public UserBuilder withIdFrom( long... values) {
		if ( values == null) {
			throw new IllegalArgumentException("array of default values must not be null!");
		}
		if ( values.length == 0) {
			throw new IllegalArgumentException("array of default values for a constructor parameter must contain at least one element");
		}
		this.id$long = values;
		return this;
	}
	/**
	 * Sets the default value for the {@link User#passwordHash} property.
	 * @param value the default value
	 * @return this builder
	 */
	public UserBuilder withPasswordHash( String value) {
		this.passwordHash$java$lang$String = new String[] { value };
		return this;
	}

	/**
	 * Sets the default values for the {@link User#passwordHash} property.
	 * @param values the default values
	 * @return this builder
	 */
	public UserBuilder withPasswordHashFrom( String... values) {
		if ( values == null) {
			throw new IllegalArgumentException("array of default values must not be null!");
		}
		this.passwordHash$java$lang$String = values;
		return this;
	}
	/**
	 * Sets the default value for the {@link User#name} property.
	 * @param value the default value
	 * @return this builder
	 */
	public UserBuilder withName( String value) {
		this.name$java$lang$String = new String[] { value };
		return this;
	}

	/**
	 * Sets the default values for the {@link User#name} property.
	 * @param values the default values
	 * @return this builder
	 */
	public UserBuilder withNameFrom( String... values) {
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
			UserBuilder result = (UserBuilder)super.clone();
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
	public UserBuilder but() {
		return (UserBuilder)clone();
	}
	
	/**
	 * Creates a new {@link User} based on this builders settings.
	 * @return the created User
	 */
	public User build() {
		User result = new User( id$long[buildCount%id$long.length], name$java$lang$String[buildCount%name$java$lang$String.length], created$java$util$Date[buildCount%created$java$util$Date.length]);
		if ( homeDirectory$java$io$File.length > 0) {    
			result.setHomeDirectory( homeDirectory$java$io$File[buildCount%homeDirectory$java$io$File.length]);		
		}
		if ( homeDirectory$java$lang$String.length > 0) {    
			result.setHomeDirectory( homeDirectory$java$lang$String[buildCount%homeDirectory$java$lang$String.length]);		
		}
		if ( passwordHash$java$lang$String.length > 0) {    
			result.setPasswordHash( passwordHash$java$lang$String[buildCount%passwordHash$java$lang$String.length]);		
		}

		
		buildCount++;    
		return result;
	}
	
	/**
	 * Creates an array of new {@link User} objects with the given number of elements.
	 * @param size the number of elements
	 * @return the array containing the new objects
	 */
	public User[] buildArray(int size) {
		User[] result = new User[size];
		for( int i=0; i<size; ++i) {
			result[i] = build();
		}
		return result;
	}
	
	/**
	 * Creates a list of new {@link User} objects with the given number of elements.
	 * @param size the number of elements
	 * @return the list containing the new objects
	 */
	public List<User> buildList(int size) { 
		List<User> result = new ArrayList<User>(size);
		for( int i=0; i<size; ++i) {
			result.add( build());
		}
		return result;
	}

}