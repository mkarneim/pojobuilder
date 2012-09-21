package samples.with.generationgap.andfactory;



import samples.with.generationgap.andfactory.ContactFactory;
import java.util.List;
import samples.with.generationgap.andfactory.Contact;


/**
 * The {@link AbstractContactBuilder} is a Builder for {@link Contact} objects.
 *
 * 
 *     Please DO NOT MODIFIY this class
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * 
 * @created by the PojoBuilder generator
 */
public abstract class AbstractContactBuilder implements Cloneable {
	protected final ContactBuilder self;
	protected List<String> value$emailAddresses$java$util$List; 
	protected boolean isSet$emailAddresses$java$util$List = false; 

	protected String value$name$java$lang$String; 
	protected boolean isSet$name$java$lang$String = false; 



	/**
	 * Creates a new {@link AbstractContactBuilder}.
	 */
	public AbstractContactBuilder() {
		self = (ContactBuilder)this;
	}

	/**
	 * Sets the default value for the {@link Contact#emailAddresses} property.
	 * @param value the default value
	 * @return this builder
	 */
	public ContactBuilder withEmailAddresses( List<String> value) {
		this.value$emailAddresses$java$util$List = value;
		this.isSet$emailAddresses$java$util$List = true;
		return self;
	}
	/**
	 * Sets the default value for the {@link Contact#name} property.
	 * @param value the default value
	 * @return this builder
	 */
	public ContactBuilder withName( String value) {
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
	 * Creates a new {@link Contact} based on this builder's settings.
	 * @return the created Contact
	 */
	public Contact build() {
		Contact result = ContactFactory.newContact( );

		if ( this.isSet$emailAddresses$java$util$List) {    
			result.setEmailAddresses( this.value$emailAddresses$java$util$List);		
		}
		if ( this.isSet$name$java$lang$String) {    
			result.setName( this.value$name$java$lang$String);		
		}

		return result;
	}

}