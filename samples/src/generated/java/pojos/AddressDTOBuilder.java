package pojos;



import java.lang.Object;
import pojos.AddressDTO;
import java.lang.String;


/**
 * The {@link AddressDTOBuilder} is a Builder for {@link AddressDTO} objects.
 *
 * @created  by the PojoBuilder generator
 */
public class AddressDTOBuilder implements Cloneable {

	protected String value$street$java$lang$String;
	protected final boolean hasValue$street$java$lang$String = true; // mandatory constructor parameter
	protected String value$postCode$java$lang$String;
	protected final boolean hasValue$postCode$java$lang$String = true; // mandatory constructor parameter
	protected String value$city$java$lang$String;
	protected final boolean hasValue$city$java$lang$String = true; // mandatory constructor parameter
	protected String value$name$java$lang$String;
	protected final boolean hasValue$name$java$lang$String = true; // mandatory constructor parameter


	/**
	 * Creates a new {@link AddressDTOBuilder}.
	 */
	public AddressDTOBuilder() {
	}

	/**
	 * Sets the default value for the {@link AddressDTO#street} property.
	 * @param value the default value
	 * @return this builder
	 */
	public AddressDTOBuilder withStreet( String value) {
		this.value$street$java$lang$String = value;
		return this;
	}
	/**
	 * Sets the default value for the {@link AddressDTO#postCode} property.
	 * @param value the default value
	 * @return this builder
	 */
	public AddressDTOBuilder withPostCode( String value) {
		this.value$postCode$java$lang$String = value;
		return this;
	}
	/**
	 * Sets the default value for the {@link AddressDTO#city} property.
	 * @param value the default value
	 * @return this builder
	 */
	public AddressDTOBuilder withCity( String value) {
		this.value$city$java$lang$String = value;
		return this;
	}
	/**
	 * Sets the default value for the {@link AddressDTO#name} property.
	 * @param value the default value
	 * @return this builder
	 */
	public AddressDTOBuilder withName( String value) {
		this.value$name$java$lang$String = value;
		return this;
	}


	/**
	 * Returns a clone of this builder.
	 * @return the clone
	 */
	@Override
	public Object clone() {
		try {
			AddressDTOBuilder result = (AddressDTOBuilder)super.clone();
			return result;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.getMessage());
		}        
	}

	/**
	 * Returns a clone of this builder.
	 * @return the clone
	 */
	public AddressDTOBuilder but() {
		return (AddressDTOBuilder)clone();
	}

	/**
	 * Creates a new {@link AddressDTO} based on this builders settings.
	 * @return the created AddressDTO
	 */
	public AddressDTO build() {
		AddressDTO result = new AddressDTO( this.value$name$java$lang$String ,this.value$street$java$lang$String ,this.value$city$java$lang$String ,this.value$postCode$java$lang$String );
		return result;
	}

}