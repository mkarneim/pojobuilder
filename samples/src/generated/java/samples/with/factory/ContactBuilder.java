package samples.with.factory;



import samples.with.factory.Contact;
import samples.with.factory.PojoFactory;


/**
 * The {@link ContactBuilder} is a Builder for {@link Contact} objects.
 *
 * @created  by the PojoBuilder generator
 */
public class ContactBuilder implements Cloneable {

	protected String value$surname$java$lang$String; // mandatory constructor parameter
	protected boolean isSet$surname$java$lang$String = false; 

	protected String value$firstname$java$lang$String; // mandatory constructor parameter
	protected boolean isSet$firstname$java$lang$String = false; 

	protected String value$email$java$lang$String; 
	protected boolean isSet$email$java$lang$String = false; 



	/**
	 * Creates a new {@link ContactBuilder}.
	 */
	public ContactBuilder() {
	}

	/**
	 * Sets the default value for the {@link Contact#surname} property.
	 * @param value the default value
	 * @return this builder
	 */
	public ContactBuilder withSurname( String value) {
		this.value$surname$java$lang$String = value;
		this.isSet$surname$java$lang$String = true;
		return this;
	}
	/**
	 * Sets the default value for the {@link Contact#firstname} property.
	 * @param value the default value
	 * @return this builder
	 */
	public ContactBuilder withFirstname( String value) {
		this.value$firstname$java$lang$String = value;
		this.isSet$firstname$java$lang$String = true;
		return this;
	}
	/**
	 * Sets the default value for the {@link Contact#email} property.
	 * @param value the default value
	 * @return this builder
	 */
	public ContactBuilder withEmail( String value) {
		this.value$email$java$lang$String = value;
		this.isSet$email$java$lang$String = true;
		return this;
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
	public ContactBuilder but() {
		return (ContactBuilder)clone();
	}

	/**
	 * Creates a new {@link Contact} based on this builders settings.
	 * @return the created Contact
	 */
	public Contact build() {
		Contact result = PojoFactory.createContact( this.value$firstname$java$lang$String ,this.value$surname$java$lang$String );

		if ( this.isSet$email$java$lang$String) {    
			result.setEmail( this.value$email$java$lang$String);		
		}

		return result;
	}

}