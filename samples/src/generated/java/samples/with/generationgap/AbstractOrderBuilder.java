package samples.with.generationgap;

import java.util.List;
import samples.with.generationgap.Order;
import javax.annotation.Generated;

/**
 * The {@link AbstractOrderBuilder} is a Builder for {@link Order} objects.
 *
 * <p>
 *     This class has been generated.
 *     Please DO NOT MODIFIY this file
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * </p>
 */
@Generated("PojoBuilder")
public abstract class AbstractOrderBuilder<T extends Object> implements Cloneable {
	protected OrderBuilder<T> self;
	protected String value$customer$java$lang$String; 
	protected boolean isSet$customer$java$lang$String = false; 

	protected List<T> value$items$java$util$List; 
	protected boolean isSet$items$java$util$List = false; 



	/**
	 * Creates a new {@link AbstractOrderBuilder}.
	 */
	public AbstractOrderBuilder() {
		self = (OrderBuilder<T>)this;
	}

	/**
	 * Sets the default value for the {@link Order#customer} property.
	DO STUFF
	 * @param value the default value
	 * @return this builder
	 */
	public OrderBuilder<T> withCustomer( String value) {
		this.value$customer$java$lang$String = value;
		this.isSet$customer$java$lang$String = true;
		return self;
	}
	/**
	 * Sets the default value for the {@link Order#items} property.
	DO STUFF
	 * @param value the default value
	 * @return this builder
	 */
	public OrderBuilder<T> withItems( List<T> value) {
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
			OrderBuilder<T> result = (OrderBuilder<T>)super.clone();
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
	public OrderBuilder<T> but() {
		return (OrderBuilder<T>)clone();
	}

	/**
	 * Creates a new {@link Order} based on this builder's settings.
	 * @return the created Order
	 */
	public Order<T> build() {
		Order<T> result = new Order<T>( );

		if ( this.isSet$customer$java$lang$String) {    
			result.setCustomer( this.value$customer$java$lang$String);		
		}
		if ( this.isSet$items$java$util$List) {    
			result.setItems( this.value$items$java$util$List);		
		}

		return result;
	}

}