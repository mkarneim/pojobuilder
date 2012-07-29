package internal;



import javax.lang.model.element.ExecutableElement;
import internal.ExecutableElementFilter;
import javax.lang.model.element.ElementKind;


/**
 * The {@link ExecutableElementFilterBuilder} is a Builder for {@link ExecutableElementFilter} objects.
 *
 * @created  by the PojoBuilder generator
 */
public class ExecutableElementFilterBuilder<E extends ExecutableElement> implements Cloneable {

	protected String value$simpleNameMatches$java$lang$String; 
	protected boolean isSet$simpleNameMatches$java$lang$String = false; 

	protected Iterable value$elements$java$lang$Iterable; // mandatory constructor parameter
	protected boolean isSet$elements$java$lang$Iterable = false; 

	protected Class value$annotation$java$lang$Class; 
	protected boolean isSet$annotation$java$lang$Class = false; 

	protected ElementKind value$kind$javax$lang$model$element$ElementKind; 
	protected boolean isSet$kind$javax$lang$model$element$ElementKind = false; 



	/**
	 * Creates a new {@link ExecutableElementFilterBuilder}.
	 */
	public ExecutableElementFilterBuilder() {
	}

	/**
	 * Sets the default value for the {@link ExecutableElementFilter#simpleNameMatches} property.
	 * @param value the default value
	 * @return this builder
	 */
	public ExecutableElementFilterBuilder<E> withSimpleNameMatches( String value) {
		this.value$simpleNameMatches$java$lang$String = value;
		this.isSet$simpleNameMatches$java$lang$String = true;
		return this;
	}
	/**
	 * Sets the default value for the {@link ExecutableElementFilter#elements} property.
	 * @param value the default value
	 * @return this builder
	 */
	public ExecutableElementFilterBuilder<E> withElements( Iterable value) {
		this.value$elements$java$lang$Iterable = value;
		this.isSet$elements$java$lang$Iterable = true;
		return this;
	}
	/**
	 * Sets the default value for the {@link ExecutableElementFilter#annotation} property.
	 * @param value the default value
	 * @return this builder
	 */
	public ExecutableElementFilterBuilder<E> withAnnotation( Class value) {
		this.value$annotation$java$lang$Class = value;
		this.isSet$annotation$java$lang$Class = true;
		return this;
	}
	/**
	 * Sets the default value for the {@link ExecutableElementFilter#kind} property.
	 * @param value the default value
	 * @return this builder
	 */
	public ExecutableElementFilterBuilder<E> withKind( ElementKind value) {
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
	public ExecutableElementFilterBuilder<E> but() {
		return (ExecutableElementFilterBuilder<E>)clone();
	}

	/**
	 * Creates a new {@link ExecutableElementFilter} based on this builder's settings.
	 * @return the created ExecutableElementFilter
	 */
	public ExecutableElementFilter<E> build() {
		ExecutableElementFilter<E> result = new ExecutableElementFilter<E>( this.value$elements$java$lang$Iterable );

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