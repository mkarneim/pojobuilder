package samples.with.innerclasses;

import samples.with.innerclasses.Order;
import javax.annotation.Generated;

/**
 * The {@link OrderBuilder} is a Builder for {@link Order} objects.
 *
 * <p>
 *     This class has been generated.
 *     Please DO NOT MODIFIY this file
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * </p>
 */
@Generated("PojoBuilder")
public class OrderBuilder implements Cloneable {
	protected OrderBuilder self;
	protected String value$customer$java$lang$String; 
	protected boolean isSet$customer$java$lang$String = false; 



	/**
	 * Creates a new {@link OrderBuilder}.
	 */
	public OrderBuilder() {
		self = (OrderBuilder)this;
	}

	/**
	 * Sets the default value for the {@link Order#customer} property.
	DO STUFF
	 * @param value the default value
	 * @return this builder
	 */
	public OrderBuilder withCustomer( String value) {
		this.value$customer$java$lang$String = value;
		this.isSet$customer$java$lang$String = true;
		return self;
	}


	/**
	 * Returns a clone of this builder.
	 * @return the clone
	 */
	@Override	
	public Object clone() {
		try {
			OrderBuilder result = (OrderBuilder)super.clone();
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
	public OrderBuilder but() {
		return (OrderBuilder)clone();
	}

	/**
	 * Creates a new {@link Order} based on this builder's settings.
	 * @return the created Order
	 */
	public Order build() {
		Order result = new Order( );

		if ( this.isSet$customer$java$lang$String) {    
			result.setCustomer( this.value$customer$java$lang$String);		
		}

		return result;
	}

}