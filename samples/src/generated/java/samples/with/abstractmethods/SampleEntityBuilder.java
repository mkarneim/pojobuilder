package samples.with.abstractmethods;

import samples.with.abstractmethods.SampleEntity;
import javax.annotation.Generated;

/**
 * The {@link SampleEntityBuilder} is a Builder for {@link SampleEntity} objects.
 *
 * <p>
 *     This class has been generated.
 *     Please DO NOT MODIFIY this file
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * </p>
 */
@Generated("PojoBuilder")
public class SampleEntityBuilder implements Cloneable {
    protected SampleEntityBuilder self;
    protected Long value$id$java$lang$Long; 
    protected boolean isSet$id$java$lang$Long = false;

    protected Long value$key$java$lang$Long; 
    protected boolean isSet$key$java$lang$Long = false;

    protected String value$name$java$lang$String; 
    protected boolean isSet$name$java$lang$String = false;



    /**
     * Creates a new {@link SampleEntityBuilder}.
     */
    public SampleEntityBuilder() {
        self = (SampleEntityBuilder)this;
    }

    /**
     * Sets the default value for the {@link SampleEntity#id} property.
     * @param value the default value
     * @return this builder
     */
    public SampleEntityBuilder withId( Long value) {
        this.value$id$java$lang$Long = value;
        this.isSet$id$java$lang$Long = true;
        return self;
    }
    /**
     * Sets the default value for the {@link SampleEntity#key} property.
     * @param value the default value
     * @return this builder
     */
    public SampleEntityBuilder withKey( Long value) {
        this.value$key$java$lang$Long = value;
        this.isSet$key$java$lang$Long = true;
        return self;
    }
    /**
     * Sets the default value for the {@link SampleEntity#name} property.
     * @param value the default value
     * @return this builder
     */
    public SampleEntityBuilder withName( String value) {
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
            SampleEntityBuilder result = (SampleEntityBuilder)super.clone();
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
    public SampleEntityBuilder but() {
        return (SampleEntityBuilder)clone();
    }

    /**
     * Creates a new {@link SampleEntity} based on this builder's settings.
     * @return the created SampleEntity
     */
    public SampleEntity build() {
        SampleEntity result = new SampleEntity( );

        if ( this.isSet$id$java$lang$Long) {    
            result.setId( this.value$id$java$lang$Long);        
        }
        if ( this.isSet$key$java$lang$Long) {    
            result.setKey( this.value$key$java$lang$Long);        
        }
        if ( this.isSet$name$java$lang$String) {    
            result.setName( this.value$name$java$lang$String);        
        }

        return result;
    }

}