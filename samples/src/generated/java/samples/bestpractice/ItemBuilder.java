package samples.bestpractice;

import samples.bestpractice.Item;
import javax.annotation.Generated;

/**
 * The {@link ItemBuilder} is a Builder for {@link Item} objects.
 *
 * <p>
 *     This class has been generated.
 *     Please DO NOT MODIFIY this file
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * </p>
 */
@Generated("PojoBuilder")
public class ItemBuilder implements Cloneable {
	protected ItemBuilder self;
	protected int value$amount$int; 
	protected boolean isSet$amount$int = false; 

	protected String value$name$java$lang$String; 
	protected boolean isSet$name$java$lang$String = false; 



	/**
	 * Creates a new {@link ItemBuilder}.
	 */
	public ItemBuilder() {
		self = (ItemBuilder)this;
	}

	/**
	 * Sets the default value for the {@link Item#amount} property.
	DO STUFF
	 * @param value the default value
	 * @return this builder
	 */
	public ItemBuilder withAmount( int value) {
		this.value$amount$int = value;
		this.isSet$amount$int = true;
		return self;
	}
	/**
	 * Sets the default value for the {@link Item#name} property.
	DO STUFF
	 * @param value the default value
	 * @return this builder
	 */
	public ItemBuilder withName( String value) {
		this.value$name$java$lang$String = value;
		this.isSet$name$java$lang$String = true;
		return self;
	}


	/**
	 * Returns a clone of this builder.
	 * @return the clone
	 */
	@Override	
	public Object clone() {
		try {
			ItemBuilder result = (ItemBuilder)super.clone();
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
	public ItemBuilder but() {
		return (ItemBuilder)clone();
	}

	/**
	 * Creates a new {@link Item} based on this builder's settings.
	 * @return the created Item
	 */
	public Item build() {
		Item result = new Item( );

		if ( this.isSet$amount$int) { 
			result.amount = this.value$amount$int;	
		}
		if ( this.isSet$name$java$lang$String) { 
			result.name = this.value$name$java$lang$String;	
		}

		return result;
	}

}