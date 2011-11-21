package samples.with.arrays;



import samples.with.arrays.Item;
import samples.with.arrays.Order;


/**
 * The {@link OrderBuilder} is a Builder for {@link Order} objects.
 *
 * @created  by the PojoBuilder generator
 */
public class OrderBuilder implements Cloneable {

	protected String value$customer$java$lang$String; 
	protected boolean isSet$customer$java$lang$String = false; 

	protected Item[] value$items$samples$with$arrays$Item$; 
	protected boolean isSet$items$samples$with$arrays$Item$ = false; 



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
	 * Sets the default value for the {@link Order#items} property.
	 * @param value the default value
	 * @return this builder
	 */
	public OrderBuilder withItems( Item[] value) {
		this.value$items$samples$with$arrays$Item$ = value;
		this.isSet$items$samples$with$arrays$Item$ = true;
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
	 * Creates a new {@link Order} based on this builders settings.
	 * @return the created Order
	 */
	public Order build() {
		Order result = new Order( );
		if ( this.isSet$customer$java$lang$String) {    
			result.setCustomer( this.value$customer$java$lang$String);		
		}
		if ( this.isSet$items$samples$with$arrays$Item$) {    
			result.setItems( this.value$items$samples$with$arrays$Item$);		
		}

		return result;
	}

}