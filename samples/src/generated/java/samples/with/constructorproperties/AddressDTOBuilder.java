package samples.with.constructorproperties;

import samples.with.constructorproperties.AddressDTO;
import javax.annotation.Generated;

/**
 * The {@link AddressDTOBuilder} is a Builder for {@link AddressDTO} objects.
 *
 * <p>
 *     This class has been generated.
 *     Please DO NOT MODIFIY this file
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * </p>
 */
@Generated("PojoBuilder")
public class AddressDTOBuilder implements Cloneable {
    protected AddressDTOBuilder self;
    protected String value$street$java$lang$String; // mandatory constructor parameter
    protected boolean isSet$street$java$lang$String = false;

    protected String value$postCode$java$lang$String; // mandatory constructor parameter
    protected boolean isSet$postCode$java$lang$String = false;

    protected String value$city$java$lang$String; // mandatory constructor parameter
    protected boolean isSet$city$java$lang$String = false;

    protected String value$name$java$lang$String; // mandatory constructor parameter
    protected boolean isSet$name$java$lang$String = false;



    /**
     * Creates a new {@link AddressDTOBuilder}.
     */
    public AddressDTOBuilder() {
        self = (AddressDTOBuilder)this;
    }

    /**
     * Sets the default value for the {@link AddressDTO#street} property.
     * @param value the default value
     * @return this builder
     */
    public AddressDTOBuilder withStreet( String value) {
        this.value$street$java$lang$String = value;
        this.isSet$street$java$lang$String = true;
        return self;
    }
    /**
     * Sets the default value for the {@link AddressDTO#postCode} property.
     * @param value the default value
     * @return this builder
     */
    public AddressDTOBuilder withPostCode( String value) {
        this.value$postCode$java$lang$String = value;
        this.isSet$postCode$java$lang$String = true;
        return self;
    }
    /**
     * Sets the default value for the {@link AddressDTO#city} property.
     * @param value the default value
     * @return this builder
     */
    public AddressDTOBuilder withCity( String value) {
        this.value$city$java$lang$String = value;
        this.isSet$city$java$lang$String = true;
        return self;
    }
    /**
     * Sets the default value for the {@link AddressDTO#name} property.
     * @param value the default value
     * @return this builder
     */
    public AddressDTOBuilder withName( String value) {
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
            AddressDTOBuilder result = (AddressDTOBuilder)super.clone();
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
    public AddressDTOBuilder but() {
        return (AddressDTOBuilder)clone();
    }

    /**
     * Creates a new {@link AddressDTO} based on this builder's settings.
     * @return the created AddressDTO
     */
    public AddressDTO build() {
        try {
            AddressDTO result = new AddressDTO( this.value$name$java$lang$String ,this.value$street$java$lang$String ,this.value$city$java$lang$String ,this.value$postCode$java$lang$String );

            return result;
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Throwable t) {
            throw new java.lang.reflect.UndeclaredThrowableException(t);
        }        
    }

}