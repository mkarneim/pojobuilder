package samples.with.generics;



import java.util.List;
import samples.with.generics.NumberGrid;
import java.util.Collection;


/**
 * The {@link NumberGridBuilder} is a Builder for {@link NumberGrid} objects.
 *
 * 
 *     Please DO NOT MODIFIY this class
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * 
 * @created by the PojoBuilder generator
 */
public class NumberGridBuilder<E extends Number> implements Cloneable {
	protected final NumberGridBuilder<E> self;
	protected List<Collection<E>> value$elements$java$util$List; 
	protected boolean isSet$elements$java$util$List = false; 



	/**
	 * Creates a new {@link NumberGridBuilder}.
	 */
	public NumberGridBuilder() {
		self = (NumberGridBuilder<E>)this;
	}

	/**
	 * Sets the default value for the {@link NumberGrid#elements} property.
	 * @param value the default value
	 * @return this builder
	 */
	public NumberGridBuilder<E> withElements( List<Collection<E>> value) {
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
	@SuppressWarnings("unchecked")
	public NumberGridBuilder<E> but() {
		return (NumberGridBuilder<E>)clone();
	}

	/**
	 * Creates a new {@link NumberGrid} based on this builder's settings.
	 * @return the created NumberGrid
	 */
	public NumberGrid<E> build() {
		NumberGrid<E> result = new NumberGrid<E>( );

		if ( this.isSet$elements$java$util$List) {    
			result.setElements( this.value$elements$java$util$List);		
		}

		return result;
	}

}