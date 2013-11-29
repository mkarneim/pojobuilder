package samples.with.generics;

import samples.with.generics.ConcreteEntity;
import javax.annotation.Generated;

/**
 * The {@link ConcreteEntityBuilder} is a Builder for {@link ConcreteEntity} objects.
 *
 * <p>
 *     This class has been generated.
 *     Please DO NOT MODIFIY this file
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * </p>
 */
@Generated("PojoBuilder")
public class ConcreteEntityBuilder implements Cloneable {
    protected ConcreteEntityBuilder self;
    protected Long value$key$java$lang$Long; 
    protected boolean isSet$key$java$lang$Long = false;

    protected String value$name$java$lang$String; 
    protected boolean isSet$name$java$lang$String = false;



    /**
     * Creates a new {@link ConcreteEntityBuilder}.
     */
    public ConcreteEntityBuilder() {
        self = (ConcreteEntityBuilder)this;
    }

    /**
     * Sets the default value for the {@link ConcreteEntity#key} property.
     * @param value the default value
     * @return this builder
     */
    public ConcreteEntityBuilder withKey( Long value) {
        this.value$key$java$lang$Long = value;
        this.isSet$key$java$lang$Long = true;
        return self;
    }
    /**
     * Sets the default value for the {@link ConcreteEntity#name} property.
     * @param value the default value
     * @return this builder
     */
    public ConcreteEntityBuilder withName( String value) {
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
            ConcreteEntityBuilder result = (ConcreteEntityBuilder)super.clone();
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
    public ConcreteEntityBuilder but() {
        return (ConcreteEntityBuilder)clone();
    }

    /**
     * Creates a new {@link ConcreteEntity} based on this builder's settings.
     * @return the created ConcreteEntity
     */
    public ConcreteEntity build() {
        ConcreteEntity result = new ConcreteEntity( );

        if ( this.isSet$key$java$lang$Long) {    
            result.setKey( this.value$key$java$lang$Long);        
        }
        if ( this.isSet$name$java$lang$String) {    
            result.setName( this.value$name$java$lang$String);        
        }

        return result;
    }

}