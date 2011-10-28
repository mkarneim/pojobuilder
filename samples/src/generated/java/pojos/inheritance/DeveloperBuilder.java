package pojos.inheritance;



import java.lang.Object;
import java.lang.Long;
import java.lang.String;
import pojos.inheritance.Developer;


/**
 * The {@link DeveloperBuilder} is a Builder for {@link Developer} objects.
 *
 * @created  by the PojoBuilder generator
 */
public class DeveloperBuilder implements Cloneable {

	protected Long value$id$java$lang$Long; // mandatory constructor parameter
	protected boolean isSet$id$java$lang$Long = false; 

	protected String[] value$languages$java$lang$String$; 
	protected boolean isSet$languages$java$lang$String$ = false; 

	protected String value$name$java$lang$String; 
	protected boolean isSet$name$java$lang$String = false; 



	/**
	 * Creates a new {@link DeveloperBuilder}.
	 */
	public DeveloperBuilder() {
	}

	/**
	 * Sets the default value for the {@link Developer#id} property.
	 * @param value the default value
	 * @return this builder
	 */
	public DeveloperBuilder withId( Long value) {
		this.value$id$java$lang$Long = value;
		this.isSet$id$java$lang$Long = true;
		return this;
	}
	/**
	 * Sets the default value for the {@link Developer#languages} property.
	 * @param value the default value
	 * @return this builder
	 */
	public DeveloperBuilder withLanguages( String[] value) {
		this.value$languages$java$lang$String$ = value;
		this.isSet$languages$java$lang$String$ = true;
		return this;
	}
	/**
	 * Sets the default value for the {@link Developer#name} property.
	 * @param value the default value
	 * @return this builder
	 */
	public DeveloperBuilder withName( String value) {
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
			DeveloperBuilder result = (DeveloperBuilder)super.clone();
			return result;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.getMessage());
		}        
	}

	/**
	 * Returns a clone of this builder.
	 * @return the clone
	 */
	public DeveloperBuilder but() {
		return (DeveloperBuilder)clone();
	}

	/**
	 * Creates a new {@link Developer} based on this builders settings.
	 * @return the created Developer
	 */
	public Developer build() {
		Developer result = new Developer( this.value$id$java$lang$Long );
		if ( this.isSet$languages$java$lang$String$) {    
			result.setLanguages( this.value$languages$java$lang$String$);		
		}
		if ( this.isSet$name$java$lang$String) {    
			result.setName( this.value$name$java$lang$String);		
		}

		return result;
	}

}