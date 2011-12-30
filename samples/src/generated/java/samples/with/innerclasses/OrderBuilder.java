package samples.with.innerclasses;



import samples.with.innerclasses.Order;


/**
 * The {@link OrderBuilder} is a Builder for {@link Order} objects.
 *
 * @created  by the PojoBuilder generator
 */
public class OrderBuilder implements Cloneable {

	protected String value$customer$java$lang$String; 
	protected boolean isSet$customer$java$lang$String = false; 



	/**
	 * Creates a new {@link OrderBuilder}.
	 */
	public OrderBuilder() {
	}

	/**
	 * Sets the default value for the {@link Order#customer} property.
	 * @param value the default value
	 * @return this builder
	 */
	public OrderBuilder withCustomer( String value) {
		this.value$customer$java$lang$String = value;
		this.isSet$customer$java$lang$String = true;
		return this;
	}


	/**
	 * Returns a clone of this builder.
	 * @return the clone
	 */
	@Override	
	public Object clone() {
		try {
			Object result = super.clone();
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