package samples.with.superclass;

import samples.with.superclass.Person;
import javax.annotation.Generated;

/**
 * The {@link PersonBuilder} is a Builder for {@link Person} objects.
 *
 * <p>
 *     This class has been generated.
 *     Please DO NOT MODIFIY this file
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * </p>
 */
@Generated("PojoBuilder")
public class PersonBuilder implements Cloneable {
    protected PersonBuilder self;
    protected boolean value$artificial$boolean; // mandatory constructor parameter
    protected boolean isSet$artificial$boolean = false;

    protected Long value$id$java$lang$Long; // mandatory constructor parameter
    protected boolean isSet$id$java$lang$Long = false;

    protected String value$name$java$lang$String; 
    protected boolean isSet$name$java$lang$String = false;



    /**
     * Creates a new {@link PersonBuilder}.
     */
    public PersonBuilder() {
        self = (PersonBuilder)this;
    }

    /**
     * Sets the default value for the {@link Person#artificial} property.
     * @param value the default value
     * @return this builder
     */
    public PersonBuilder withArtificial( boolean value) {
        this.value$artificial$boolean = value;
        this.isSet$artificial$boolean = true;
        return self;
    }
    /**
     * Sets the default value for the {@link Person#id} property.
     * @param value the default value
     * @return this builder
     */
    public PersonBuilder withId( Long value) {
        this.value$id$java$lang$Long = value;
        this.isSet$id$java$lang$Long = true;
        return self;
    }
    /**
     * Sets the default value for the {@link Person#name} property.
     * @param value the default value
     * @return this builder
     */
    public PersonBuilder withName( String value) {
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
            PersonBuilder result = (PersonBuilder)super.clone();
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
    public PersonBuilder but() {
        return (PersonBuilder)clone();
    }

    /**
     * Creates a new {@link Person} based on this builder's settings.
     * @return the created Person
     */
    public Person build() {
        Person result = new Person( this.value$id$java$lang$Long ,this.value$artificial$boolean );

        if ( this.isSet$name$java$lang$String) {    
            result.setName( this.value$name$java$lang$String);        
        }

        return result;
    }

}