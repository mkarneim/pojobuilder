package samples.with.generics;

import samples.with.generics.Item;
import samples.with.generics.Container;
import java.io.Serializable;
import javax.annotation.Generated;

/**
 * The {@link ContainerBuilder} is a Builder for {@link Container} objects.
 *
 * <p>
 *     This class has been generated.
 *     Please DO NOT MODIFIY this file
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * </p>
 */
@Generated("PojoBuilder")
public class ContainerBuilder<T extends Item & Serializable> implements Cloneable {
    protected ContainerBuilder<T> self;
    protected T value$element$T; 
    protected boolean isSet$element$T = false;



    /**
     * Creates a new {@link ContainerBuilder}.
     */
    public ContainerBuilder() {
        self = (ContainerBuilder<T>)this;
    }

    /**
     * Sets the default value for the {@link Container#element} property.
     * @param value the default value
     * @return this builder
     */
    public ContainerBuilder<T> withElement( T value) {
        this.value$element$T = value;
        this.isSet$element$T = true;
        return self;
    }


    /**
     * Returns a clone of this builder.
     * @return the clone
     */
    @Override    
    public Object clone() {
        try {
            ContainerBuilder<T> result = (ContainerBuilder<T>)super.clone();
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
    @SuppressWarnings("unchecked")
    public ContainerBuilder<T> but() {
        return (ContainerBuilder<T>)clone();
    }

    /**
     * Creates a new {@link Container} based on this builder's settings.
     * @return the created Container
     */
    public Container<T> build() {
        Container<T> result = new Container<T>( );

        if ( this.isSet$element$T) {    
            result.setElement( this.value$element$T);        
        }

        return result;
    }

}