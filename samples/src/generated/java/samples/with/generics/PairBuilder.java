package samples.with.generics;



import samples.with.generics.Pair;


/**
 * The {@link PairBuilder} is a Builder for {@link Pair} objects.
 *
 * <p>
 *     Please DO NOT MODIFIY this class
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * </p>
 * @created by the PojoBuilder generator
 */
public class PairBuilder<A extends Comparable<A>, B extends Number> implements Cloneable {
	protected PairBuilder<A, B> self;
	protected A value$valueA$A; 
	protected boolean isSet$valueA$A = false; 

	protected B value$valueB$B; 
	protected boolean isSet$valueB$B = false; 



	/**
	 * Creates a new {@link PairBuilder}.
	 */
	public PairBuilder() {
		self = (PairBuilder<A, B>)this;
	}

	/**
	 * Sets the default value for the {@link Pair#valueA} property.
	 * @param value the default value
	 * @return this builder
	 */
	public PairBuilder<A, B> withValueA( A value) {
		this.value$valueA$A = value;
		this.isSet$valueA$A = true;
		return self;
	}
	/**
	 * Sets the default value for the {@link Pair#valueB} property.
	 * @param value the default value
	 * @return this builder
	 */
	public PairBuilder<A, B> withValueB( B value) {
		this.value$valueB$B = value;
		this.isSet$valueB$B = true;
		return self;
	}


	/**
	 * Returns a clone of this builder.
	 * @return the clone
	 */
	@Override	
	public Object clone() {
		try {
			PairBuilder<A, B> result = (PairBuilder<A, B>)super.clone();
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
	public PairBuilder<A, B> but() {
		return (PairBuilder<A, B>)clone();
	}

	/**
	 * Creates a new {@link Pair} based on this builder's settings.
	 * @return the created Pair
	 */
	public Pair<A, B> build() {
		Pair<A, B> result = new Pair<A, B>( );

		if ( this.isSet$valueA$A) {    
			result.setValueA( this.value$valueA$A);		
		}
		if ( this.isSet$valueB$B) {    
			result.setValueB( this.value$valueB$B);		
		}

		return result;
	}

}