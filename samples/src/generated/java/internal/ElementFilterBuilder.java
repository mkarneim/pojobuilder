package internal;



import internal.ElementFilter;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Element;


/**
 * The {@link ElementFilterBuilder} is a Builder for {@link ElementFilter} objects.
 *
 * @created  by the PojoBuilder generator
 */
public class ElementFilterBuilder<E extends Element> implements Cloneable {

	protected String value$simpleNameMatches$java$lang$String; 
	protected boolean isSet$simpleNameMatches$java$lang$String = false; 

	protected Iterable value$elements$java$lang$Iterable; // mandatory constructor parameter
	protected boolean isSet$elements$java$lang$Iterable = false; 

	protected Class value$annotation$java$lang$Class; 
	protected boolean isSet$annotation$java$lang$Class = false; 

	protected ElementKind value$kind$javax$lang$model$element$ElementKind; 
	protected boolean isSet$kind$javax$lang$model$element$ElementKind = false; 



	/**
	 * Creates a new {@link ElementFilterBuilder}.
	 */
	public ElementFilterBuilder() {
	}

	/**
	 * Sets the default value for the {@link ElementFilter#simpleNameMatches} property.
	 * @param value the default value
	 * @return this builder
	 */
	public ElementFilterBuilder<E> withSimpleNameMatches( String value) {
		this.value$simpleNameMatches$java$lang$String = value;
		this.isSet$simpleNameMatches$java$lang$String = true;
		return this;
	}
	/**
	 * Sets the default value for the {@link ElementFilter#elements} property.
	 * @param value the default value
	 * @return this builder
	 */
	public ElementFilterBuilder<E> withElements( Iterable value) {
		this.value$elements$java$lang$Iterable = value;
		this.isSet$elements$java$lang$Iterable = true;
		return this;
	}
	/**
	 * Sets the default value for the {@link ElementFilter#annotation} property.
	 * @param value the default value
	 * @return this builder
	 */
	public ElementFilterBuilder<E> withAnnotation( Class value) {
		this.value$annotation$java$lang$Class = value;
		this.isSet$annotation$java$lang$Class = true;
		return this;
	}
	/**
	 * Sets the default value for the {@link ElementFilter#kind} property.
	 * @param value the default value
	 * @return this builder
	 */
	public ElementFilterBuilder<E> withKind( ElementKind value) {
		this.value$kind$javax$lang$model$element$ElementKind = value;
		this.isSet$kind$javax$lang$model$element$ElementKind = true;
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
	@SuppressWarnings("unchecked")
	public ElementFilterBuilder<E> but() {
		return (ElementFilterBuilder<E>)clone();
	}

	/**
	 * Creates a new {@link ElementFilter} based on this builder's settings.
	 * @return the created ElementFilter
	 */
	public ElementFilter<E> build() {
		ElementFilter<E> result = new ElementFilter<E>( this.value$elements$java$lang$Iterable );

		if ( this.isSet$simpleNameMatches$java$lang$String) {    
			result.setSimpleNameMatches( this.value$simpleNameMatches$java$lang$String);		
		}
		if ( this.isSet$annotation$java$lang$Class) {    
			result.setAnnotation( this.value$annotation$java$lang$Class);		
		}
		if ( this.isSet$kind$javax$lang$model$element$ElementKind) {    
			result.setKind( this.value$kind$javax$lang$model$element$ElementKind);		
		}

		return result;
	}

}