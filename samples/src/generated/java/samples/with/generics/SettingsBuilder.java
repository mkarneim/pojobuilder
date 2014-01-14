package samples.with.generics;

import java.util.Map;
import samples.with.generics.Settings;
import javax.annotation.Generated;

/**
 * The {@link SettingsBuilder} is a Builder for {@link Settings} objects.
 *
 * <p>
 *     This class has been generated.
 *     Please DO NOT MODIFIY this file
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * </p>
 */
@Generated("PojoBuilder")
public class SettingsBuilder implements Cloneable {
    protected SettingsBuilder self;
    protected Map<String, Object> value$entries$java$util$Map; 
    protected boolean isSet$entries$java$util$Map = false;



    /**
     * Creates a new {@link SettingsBuilder}.
     */
    public SettingsBuilder() {
        self = (SettingsBuilder)this;
    }

    /**
     * Sets the default value for the {@link Settings#entries} property.
     * @param value the default value
     * @return this builder
     */
    public SettingsBuilder withEntries( Map<String, Object> value) {
        this.value$entries$java$util$Map = value;
        this.isSet$entries$java$util$Map = true;
        return self;
    }


    /**
     * Returns a clone of this builder.
     * @return the clone
     */
    @Override    
    public Object clone() {
        try {
            SettingsBuilder result = (SettingsBuilder)super.clone();
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
    public SettingsBuilder but() {
        return (SettingsBuilder)clone();
    }

    /**
     * Creates a new {@link Settings} based on this builder's settings.
     * @return the created Settings
     */
    public Settings build() {
        try {
            Settings result = new Settings( );

            if ( this.isSet$entries$java$util$Map) { 
                result.entries = this.value$entries$java$util$Map;    
            }

            return result;
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Throwable t) {
            throw new java.lang.reflect.UndeclaredThrowableException(t);
        }        
    }

}