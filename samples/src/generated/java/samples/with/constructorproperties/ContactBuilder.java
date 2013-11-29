package samples.with.constructorproperties;

import samples.with.constructorproperties.Contact;
import java.util.Date;
import javax.annotation.Generated;

/**
 * The {@link ContactBuilder} is a Builder for {@link Contact} objects.
 *
 * <p>
 *     This class has been generated.
 *     Please DO NOT MODIFIY this file
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * </p>
 */
@Generated("PojoBuilder")
public class ContactBuilder implements Cloneable {
    protected ContactBuilder self;
    protected Date value$dateOfBirth$java$util$Date; // mandatory constructor parameter
    protected boolean isSet$dateOfBirth$java$util$Date = false;

    protected String value$surname$java$lang$String; // mandatory constructor parameter
    protected boolean isSet$surname$java$lang$String = false;

    protected String value$firstname$java$lang$String; // mandatory constructor parameter
    protected boolean isSet$firstname$java$lang$String = false;



    /**
     * Creates a new {@link ContactBuilder}.
     */
    public ContactBuilder() {
        self = (ContactBuilder)this;
    }

    /**
     * Sets the default value for the {@link Contact#dateOfBirth} property.
     * @param value the default value
     * @return this builder
     */
    public ContactBuilder withDateOfBirth( Date value) {
        this.value$dateOfBirth$java$util$Date = value;
        this.isSet$dateOfBirth$java$util$Date = true;
        return self;
    }
    /**
     * Sets the default value for the {@link Contact#surname} property.
     * @param value the default value
     * @return this builder
     */
    public ContactBuilder withSurname( String value) {
        this.value$surname$java$lang$String = value;
        this.isSet$surname$java$lang$String = true;
        return self;
    }
    /**
     * Sets the default value for the {@link Contact#firstname} property.
     * @param value the default value
     * @return this builder
     */
    public ContactBuilder withFirstname( String value) {
        this.value$firstname$java$lang$String = value;
        this.isSet$firstname$java$lang$String = true;
        return self;
    }


    /**
     * Returns a clone of this builder.
     * @return the clone
     */
    @Override    
    public Object clone() {
        try {
            ContactBuilder result = (ContactBuilder)super.clone();
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
    public ContactBuilder but() {
        return (ContactBuilder)clone();
    }

    /**
     * Creates a new {@link Contact} based on this builder's settings.
     * @return the created Contact
     */
    public Contact build() {
        Contact result = new Contact( this.value$firstname$java$lang$String ,this.value$surname$java$lang$String ,this.value$dateOfBirth$java$util$Date );

        return result;
    }

}