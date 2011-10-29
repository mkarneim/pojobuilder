package samples.with.superclass;



import samples.with.superclass.Person;
import java.lang.Object;
import java.lang.Long;
import java.lang.String;


/**
 * The {@link PersonBuilder} is a Builder for {@link Person} objects.
 *
 * @created  by the PojoBuilder generator
 */
public class PersonBuilder implements Cloneable {

	protected boolean value$artificial$boolean; // mandatory constructor parameter
	protected boolean isSet$artificial$boolean = false; 

	protected Long value$id$java$lang$Long; // mandatory constructor parameter
	protected boolean isSet$id$java$lang$Long = false; 

	protected String value$name$java$lang$String; 
	protected boolean isSet$name$java$lang$String = false; 



	/**
	 * Creates a new {@link PersonBuilder}.
	 */
	public PersonBuilder() {
	}

	/**
	 * Sets the default value for the {@link Person#artificial} property.
	 * @param value the default value
	 * @return this builder
	 */
	public PersonBuilder withArtificial( boolean value) {
		this.value$artificial$boolean = value;
		this.isSet$artificial$boolean = true;
		return this;
	}
	/**
	 * Sets the default value for the {@link Person#id} property.
	 * @param value the default value
	 * @return this builder
	 */
	public PersonBuilder withId( Long value) {
		this.value$id$java$lang$Long = value;
		this.isSet$id$java$lang$Long = true;
		return this;
	}
	/**
	 * Sets the default value for the {@link Person#name} property.
	 * @param value the default value
	 * @return this builder
	 */
	public PersonBuilder withName( String value) {
		this.value$name$java$lang$String = value;
		this.isSet$name$java$lang$String = true;
		return this;
	}


	/**
	 * Returns a clone of this builder.
	 * @return the clone
	 */
	@Override
	public Object clone() {
		try {
			PersonBuilder result = (PersonBuilder)super.clone();
			return result;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.getMessage());
		}        
	}

	/**
	 * Returns a clone of this builder.
	 * @return the clone
	 */
	public PersonBuilder but() {
		return (PersonBuilder)clone();
	}

	/**
	 * Creates a new {@link Person} based on this builders settings.
	 * @return the created Person
	 */
	public Person build() {
		Person result = new Person( this.value$id$java$lang$Long ,this.value$artificial$boolean );
		if ( this.isSet$name$java$lang$String) {    
			result.setName( this.value$name$java$lang$String);		
		}

		return result;
	}

}