package samples.with.name;



import samples.with.name.Contact;


/**
 * The {@link FluentContactBuilder} is a Builder for {@link Contact} objects.
 *
 * 
 *     Please DO NOT MODIFIY this class
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * 
 * @created by the PojoBuilder generator
 */
public class FluentContactBuilder implements Cloneable {
	protected FluentContactBuilder self;
	protected String value$surname$java$lang$String; // mandatory constructor parameter
	protected boolean isSet$surname$java$lang$String = false; 

	protected String value$firstname$java$lang$String; // mandatory constructor parameter
	protected boolean isSet$firstname$java$lang$String = false; 

	protected String value$email$java$lang$String; 
	protected boolean isSet$email$java$lang$String = false; 



	/**
	 * Creates a new {@link FluentContactBuilder}.
	 */
	public FluentContactBuilder() {
		self = (FluentContactBuilder)this;
	}

	/**
	 * Sets the default value for the {@link Contact#surname} property.
	 * @param value the default value
	 * @return this builder
	 */
	public FluentContactBuilder withSurname( String value) {
		this.value$surname$java$lang$String = value;
		this.isSet$surname$java$lang$String = true;
		return self;
	}
	/**
	 * Sets the default value for the {@link Contact#firstname} property.
	 * @param value the default value
	 * @return this builder
	 */
	public FluentContactBuilder withFirstname( String value) {
		this.value$firstname$java$lang$String = value;
		this.isSet$firstname$java$lang$String = true;
		return self;
	}
	/**
	 * Sets the default value for the {@link Contact#email} property.
	 * @param value the default value
	 * @return this builder
	 */
	public FluentContactBuilder withEmail( String value) {
		this.value$email$java$lang$String = value;
		this.isSet$email$java$lang$String = true;
		return self;
	}


	/**
	 * Returns a clone of this builder.
	 * @return the clone
	 */
	@Override	
	public Object clone() {
		try {
			FluentContactBuilder result = (FluentContactBuilder)super.clone();
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
	public FluentContactBuilder but() {
		return (FluentContactBuilder)clone();
	}

	/**
	 * Creates a new {@link Contact} based on this builder's settings.
	 * @return the created Contact
	 */
	public Contact build() {
		Contact result = new Contact( this.value$surname$java$lang$String ,this.value$firstname$java$lang$String );

		if ( this.isSet$email$java$lang$String) {    
			result.setEmail( this.value$email$java$lang$String);		
		}

		return result;
	}

}