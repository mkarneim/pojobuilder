package samples.with.methodoverloading;

import java.io.File;
import java.util.Date;
import samples.with.methodoverloading.User;
import javax.annotation.Generated;

/**
 * The {@link UserBuilder} is a Builder for {@link User} objects.
 *
 * <p>
 *     This class has been generated.
 *     Please DO NOT MODIFIY this file
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * </p>
 */
@Generated("PojoBuilder")
public class UserBuilder implements Cloneable {
    protected UserBuilder self;
    protected Date value$created$java$util$Date; // mandatory constructor parameter
    protected boolean isSet$created$java$util$Date = false;

    protected File value$homeDirectory$java$io$File; 
    protected boolean isSet$homeDirectory$java$io$File = false;

    protected String value$homeDirectory$java$lang$String; 
    protected boolean isSet$homeDirectory$java$lang$String = false;

    protected long value$id$long; // mandatory constructor parameter
    protected boolean isSet$id$long = false;

    protected String value$passwordHash$java$lang$String; 
    protected boolean isSet$passwordHash$java$lang$String = false;

    protected String value$name$java$lang$String; // mandatory constructor parameter
    protected boolean isSet$name$java$lang$String = false;



    /**
     * Creates a new {@link UserBuilder}.
     */
    public UserBuilder() {
        self = (UserBuilder)this;
    }

    /**
     * Sets the default value for the {@link User#created} property.
     * @param value the default value
     * @return this builder
     */
    public UserBuilder withCreated( Date value) {
        this.value$created$java$util$Date = value;
        this.isSet$created$java$util$Date = true;
        return self;
    }
    /**
     * Sets the default value for the {@link User#homeDirectory} property.
     * @param value the default value
     * @return this builder
     */
    public UserBuilder withHomeDirectory( File value) {
        this.value$homeDirectory$java$io$File = value;
        this.isSet$homeDirectory$java$io$File = true;
        return self;
    }
    /**
     * Sets the default value for the {@link User#homeDirectory} property.
     * @param value the default value
     * @return this builder
     */
    public UserBuilder withHomeDirectory( String value) {
        this.value$homeDirectory$java$lang$String = value;
        this.isSet$homeDirectory$java$lang$String = true;
        return self;
    }
    /**
     * Sets the default value for the {@link User#id} property.
     * @param value the default value
     * @return this builder
     */
    public UserBuilder withId( long value) {
        this.value$id$long = value;
        this.isSet$id$long = true;
        return self;
    }
    /**
     * Sets the default value for the {@link User#passwordHash} property.
     * @param value the default value
     * @return this builder
     */
    public UserBuilder withPasswordHash( String value) {
        this.value$passwordHash$java$lang$String = value;
        this.isSet$passwordHash$java$lang$String = true;
        return self;
    }
    /**
     * Sets the default value for the {@link User#name} property.
     * @param value the default value
     * @return this builder
     */
    public UserBuilder withName( String value) {
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
            UserBuilder result = (UserBuilder)super.clone();
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
    public UserBuilder but() {
        return (UserBuilder)clone();
    }

    /**
     * Creates a new {@link User} based on this builder's settings.
     * @return the created User
     */
    public User build() {
        try {
            User result = new User( this.value$id$long ,this.value$name$java$lang$String ,this.value$created$java$util$Date );

            if ( this.isSet$homeDirectory$java$io$File) {    
                result.setHomeDirectory( this.value$homeDirectory$java$io$File);        
            }
            if ( this.isSet$homeDirectory$java$lang$String) {    
                result.setHomeDirectory( this.value$homeDirectory$java$lang$String);        
            }
            if ( this.isSet$passwordHash$java$lang$String) {    
                result.setPasswordHash( this.value$passwordHash$java$lang$String);        
            }

            return result;
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Throwable t) {
            throw new java.lang.reflect.UndeclaredThrowableException(t);
        }        
    }

}