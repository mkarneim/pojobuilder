package samples.with.generics;

import java.util.List;
import samples.with.generics.Order2;
import samples.with.generics.Item;
import java.io.Serializable;
import javax.annotation.Generated;

/**
 * The {@link Order2Builder} is a Builder for {@link Order2} objects.
 *
 * <p>
 *     This class has been generated.
 *     Please DO NOT MODIFIY this file
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * </p>
 */
@Generated("PojoBuilder")
public class Order2Builder<T extends Item & Serializable> implements Cloneable {
	protected Order2Builder<T> self;
	protected String value$customer$java$lang$String; 
	protected boolean isSet$customer$java$lang$String = false; 

	protected List<T> value$items$java$util$List; 
	protected boolean isSet$items$java$util$List = false; 



	/**
	 * Creates a new {@link Order2Builder}.
	 */
	public Order2Builder() {
		self = (Order2Builder<T>)this;
	}

	/**
	 * Sets the default value for the {@link Order2#customer} property.
	 * @param value the default value
	 * @return this builder
	 */
	public Order2Builder<T> withCustomer( String value) {
		this.value$customer$java$lang$String = value;
		this.isSet$customer$java$lang$String = true;
		return self;
	}
	/**
	 * Sets the default value for the {@link Order2#items} property.
	 * @param value the default value
	 * @return this builder
	 */
	public Order2Builder<T> withItems( List<T> value) {
		this.value$items$java$util$List = value;
		this.isSet$items$java$util$List = true;
		return self;
	}


	/**
	 * Returns a clone of this builder.
	 * @return the clone
	 */
	@Override	
	public Object clone() {
		try {
			Order2Builder<T> result = (Order2Builder<T>)super.clone();
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
	public Order2Builder<T> but() {
		return (Order2Builder<T>)clone();
	}

	/**
	 * Creates a new {@link Order2} based on this builder's settings.
	 * @return the created Order2
	 */
	public Order2<T> build() {
		Order2<T> result = new Order2<T>( );

		if ( this.isSet$customer$java$lang$String) {    
			result.setCustomer( this.value$customer$java$lang$String);		
		}
		if ( this.isSet$items$java$util$List) {    
			result.setItems( this.value$items$java$util$List);		
		}

		return result;
	}

}