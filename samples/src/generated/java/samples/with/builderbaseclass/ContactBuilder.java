package samples.with.builderbaseclass;



import samples.with.builderbaseclass.BaseBuilder;
import java.lang.String;
import samples.with.builderbaseclass.Contact;


/**
 * The {@link ContactBuilder} is a Builder for {@link Contact} objects.
 *
 * @created  by the PojoBuilder generator
 */
public class ContactBuilder extends BaseBuilder implements Cloneable {

	protected String value$email$java$lang$String; 
	protected boolean isSet$email$java$lang$String = false; 

	protected String value$name$java$lang$String; // mandatory constructor parameter
	protected boolean isSet$name$java$lang$String = false; 



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
		this.value$email$java$lang$String = value;
		this.isSet$email$java$lang$String = true;
		return this;
	}
	/**
	 * Sets the default value for the {@link Contact#name} property.
	 * @param value the default value
	 * @return this builder
	 */
	public ContactBuilder withName( String value) {
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
			ContactBuilder result = (ContactBuilder)super.clone();
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
		Contact result = new Contact( this.value$name$java$lang$String );
		if ( this.isSet$email$java$lang$String) {    
			result.setEmail( this.value$email$java$lang$String);		
		}

		return result;
	}

}