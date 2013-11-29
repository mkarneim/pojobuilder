package samples.with.factory;

import samples.with.factory.Contact;
import samples.with.factory.PojoFactory;
import javax.annotation.Generated;

/**
 * The {@link ContactMonteur} is a Builder for {@link Contact} objects.
 *
 * <p>
 *     This class has been generated.
 *     Please DO NOT MODIFIY this file
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * </p>
 */
@Generated("PojoBuilder")
public class ContactMonteur implements Cloneable {
	protected ContactMonteur self;
	protected String value$surname$java$lang$String; // mandatory constructor parameter
	protected boolean isSet$surname$java$lang$String = false; 

	protected String value$firstname$java$lang$String; // mandatory constructor parameter
	protected boolean isSet$firstname$java$lang$String = false; 

	protected String value$email$java$lang$String; 
	protected boolean isSet$email$java$lang$String = false; 



	/**
	 * Creates a new {@link ContactMonteur}.
	 */
	public ContactMonteur() {
		self = (ContactMonteur)this;
	}

	/**
	 * Sets the default value for the {@link Contact#surname} property.
	DO STUFF
	 * @param value the default value
	 * @return this builder
	 */
	public ContactMonteur withSurname( String value) {
		this.value$surname$java$lang$String = value;
		this.isSet$surname$java$lang$String = true;
		return self;
	}
	/**
	 * Sets the default value for the {@link Contact#firstname} property.
	DO STUFF
	 * @param value the default value
	 * @return this builder
	 */
	public ContactMonteur withFirstname( String value) {
		this.value$firstname$java$lang$String = value;
		this.isSet$firstname$java$lang$String = true;
		return self;
	}
	/**
	 * Sets the default value for the {@link Contact#email} property.
	DO STUFF
	 * @param value the default value
	 * @return this builder
	 */
	public ContactMonteur withEmail( String value) {
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
			ContactMonteur result = (ContactMonteur)super.clone();
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
	public ContactMonteur but() {
		return (ContactMonteur)clone();
	}

	/**
	 * Creates a new {@link Contact} based on this builder's settings.
	 * @return the created Contact
	 */
	public Contact build() {
		Contact result = PojoFactory.construireContact( this.value$firstname$java$lang$String ,this.value$surname$java$lang$String );

		if ( this.isSet$email$java$lang$String) {    
			result.setEmail( this.value$email$java$lang$String);		
		}

		return result;
	}

}