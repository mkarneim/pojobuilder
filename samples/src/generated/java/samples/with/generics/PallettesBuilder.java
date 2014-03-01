package samples.with.generics;

import java.awt.Color;
import java.util.List;
import java.util.Set;
import samples.with.generics.Pallettes;
import javax.annotation.Generated;

/**
 * The {@link PallettesBuilder} is a Builder for {@link Pallettes} objects.
 *
 * <p>
 *     This class has been generated.
 *     Please DO NOT MODIFIY this file
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * </p>
 */
@Generated("PojoBuilder")
public class PallettesBuilder implements Cloneable {
    protected PallettesBuilder self;
    protected List<Set<Color>> value$elements$java$util$List; 
    protected boolean isSet$elements$java$util$List = false;



    /**
     * Creates a new {@link PallettesBuilder}.
     */
    public PallettesBuilder() {
        self = (PallettesBuilder)this;
    }

    /**
     * Sets the default value for the {@link Pallettes#elements} property.
     * @param value the default value
     * @return this builder
     */
    public PallettesBuilder withElements( List<Set<Color>> value) {
        this.value$elements$java$util$List = value;
        this.isSet$elements$java$util$List = true;
        return self;
    }


    /**
     * Returns a clone of this builder.
     * @return the clone
     */
    @Override    
    public Object clone() {
        try {
            PallettesBuilder result = (PallettesBuilder)super.clone();
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
    public PallettesBuilder but() {
        return (PallettesBuilder)clone();
    }

    /**
     * Creates a new {@link Pallettes} based on this builder's settings.
     * @return the created Pallettes
     */
    public Pallettes build() {
        try {
            Pallettes result = new Pallettes( );

            if ( this.isSet$elements$java$util$List) {    
                result.setElements( this.value$elements$java$util$List);        
            }

            return result;
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Throwable t) {
            throw new java.lang.reflect.UndeclaredThrowableException(t);
        }        
    }

}