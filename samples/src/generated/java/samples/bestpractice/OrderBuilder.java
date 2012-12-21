package samples.bestpractice;

import samples.bestpractice.Order;
import samples.bestpractice.Item;
import java.util.List;
import java.util.Date;
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
	protected Date value$date$java$util$Date; 
	protected boolean isSet$date$java$util$Date = false; 

	protected List<Item> value$items$java$util$List; 
	protected boolean isSet$items$java$util$List = false; 

	protected long value$id$long; 
	protected boolean isSet$id$long = false; 



	/**
	 * Creates a new {@link OrderBuilder}.
	 */
	public OrderBuilder() {
		self = (OrderBuilder)this;
	}

	/**
	 * Sets the default value for the {@link Order#date} property.
	 * @param value the default value
	 * @return this builder
	 */
	public OrderBuilder withDate( Date value) {
		this.value$date$java$util$Date = value;
		this.isSet$date$java$util$Date = true;
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
	 * Sets the default value for the {@link Order#id} property.
	 * @param value the default value
	 * @return this builder
	 */
	public OrderBuilder withId( long value) {
		this.value$id$long = value;
		this.isSet$id$long = true;
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

		if ( this.isSet$date$java$util$Date) { 
			result.date = this.value$date$java$util$Date;	
		}
		if ( this.isSet$items$java$util$List) { 
			result.items = this.value$items$java$util$List;	
		}
		if ( this.isSet$id$long) { 
			result.id = this.value$id$long;	
		}

		return result;
	}

}