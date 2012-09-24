package samples.with.generics;



import java.util.List;
import samples.with.generics.Item;
import samples.with.generics.Order;


/**
 * The {@link OrderBuilder} is a Builder for {@link Order} objects.
 *
 * 
 *     Please DO NOT MODIFIY this class
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * 
 * @created by the PojoBuilder generator
 */
public class OrderBuilder implements Cloneable {
	protected OrderBuilder self;
	protected String value$customer$java$lang$String; 
	protected boolean isSet$customer$java$lang$String = false; 

	protected List<Item> value$items$java$util$List; 
	protected boolean isSet$items$java$util$List = false; 



	/**
	 * Creates a new {@link OrderBuilder}.
	 */
	public OrderBuilder() {
		self = (OrderBuilder)this;
	}

	/**
	 * Sets the default value for the {@link Order#customer} property.
	 * @param value the default value
	 * @return this builder
	 */
	public OrderBuilder withCustomer( String value) {
		this.value$customer$java$lang$String = value;
		this.isSet$customer$java$lang$String = true;
		return self;
	}
	/**
	 * Sets the default value for the {@link Order#items} property.
	 * @param value the default value
	 * @return this builder
	 */
	public OrderBuilder withItems( List<Item> value) {
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
		if ( this.isSet$items$java$util$List) {    
			result.setItems( this.value$items$java$util$List);		
		}

		return result;
	}

}