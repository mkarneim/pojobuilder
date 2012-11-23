package samples.with.superclass;

import samples.with.superclass.Developer;
import javax.annotation.Generated;

/**
 * The {@link DeveloperBuilder} is a Builder for {@link Developer} objects.
 *
 * <p>
 *     This class has been generated.
 *     Please DO NOT MODIFIY this file
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * </p>
 */
@Generated("PojoBuilder")
public class DeveloperBuilder implements Cloneable {
	protected DeveloperBuilder self;
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
		self = (DeveloperBuilder)this;
	}

	/**
	 * Sets the default value for the {@link Developer#id} property.
	 * @param value the default value
	 * @return this builder
	 */
	public DeveloperBuilder withId( Long value) {
		this.value$id$java$lang$Long = value;
		this.isSet$id$java$lang$Long = true;
		return self;
	}
	/**
	 * Sets the default value for the {@link Developer#languages} property.
	 * @param value the default value
	 * @return this builder
	 */
	public DeveloperBuilder withLanguages( String[] value) {
		this.value$languages$java$lang$String$ = value;
		this.isSet$languages$java$lang$String$ = true;
		return self;
	}
	/**
	 * Sets the default value for the {@link Developer#name} property.
	 * @param value the default value
	 * @return this builder
	 */
	public DeveloperBuilder withName( String value) {
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
			DeveloperBuilder result = (DeveloperBuilder)super.clone();
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
	public DeveloperBuilder but() {
		return (DeveloperBuilder)clone();
	}

	/**
	 * Creates a new {@link Developer} based on this builder's settings.
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