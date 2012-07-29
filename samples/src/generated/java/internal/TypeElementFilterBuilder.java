package internal;



import internal.TypeElementFilter;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.ElementKind;


/**
 * The {@link TypeElementFilterBuilder} is a Builder for {@link TypeElementFilter} objects.
 *
 * @created  by the PojoBuilder generator
 */
public class TypeElementFilterBuilder<E extends TypeElement> implements Cloneable {

	protected String value$simpleNameMatches$java$lang$String; 
	protected boolean isSet$simpleNameMatches$java$lang$String = false; 

	protected Iterable value$elements$java$lang$Iterable; // mandatory constructor parameter
	protected boolean isSet$elements$java$lang$Iterable = false; 

	protected Class value$annotation$java$lang$Class; 
	protected boolean isSet$annotation$java$lang$Class = false; 

	protected ElementKind value$kind$javax$lang$model$element$ElementKind; 
	protected boolean isSet$kind$javax$lang$model$element$ElementKind = false; 

	protected String value$qualifiedName$java$lang$String; 
	protected boolean isSet$qualifiedName$java$lang$String = false; 



	/**
	 * Creates a new {@link TypeElementFilterBuilder}.
	 */
	public TypeElementFilterBuilder() {
	}

	/**
	 * Sets the default value for the {@link TypeElementFilter#simpleNameMatches} property.
	 * @param value the default value
	 * @return this builder
	 */
	public TypeElementFilterBuilder<E> withSimpleNameMatches( String value) {
		this.value$simpleNameMatches$java$lang$String = value;
		this.isSet$simpleNameMatches$java$lang$String = true;
		return this;
	}
	/**
	 * Sets the default value for the {@link TypeElementFilter#elements} property.
	 * @param value the default value
	 * @return this builder
	 */
	public TypeElementFilterBuilder<E> withElements( Iterable value) {
		this.value$elements$java$lang$Iterable = value;
		this.isSet$elements$java$lang$Iterable = true;
		return this;
	}
	/**
	 * Sets the default value for the {@link TypeElementFilter#annotation} property.
	 * @param value the default value
	 * @return this builder
	 */
	public TypeElementFilterBuilder<E> withAnnotation( Class value) {
		this.value$annotation$java$lang$Class = value;
		this.isSet$annotation$java$lang$Class = true;
		return this;
	}
	/**
	 * Sets the default value for the {@link TypeElementFilter#kind} property.
	 * @param value the default value
	 * @return this builder
	 */
	public TypeElementFilterBuilder<E> withKind( ElementKind value) {
		this.value$kind$javax$lang$model$element$ElementKind = value;
		this.isSet$kind$javax$lang$model$element$ElementKind = true;
		return this;
	}
	/**
	 * Sets the default value for the {@link TypeElementFilter#qualifiedName} property.
	 * @param value the default value
	 * @return this builder
	 */
	public TypeElementFilterBuilder<E> withQualifiedName( String value) {
		this.value$qualifiedName$java$lang$String = value;
		this.isSet$qualifiedName$java$lang$String = true;
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
	public TypeElementFilterBuilder<E> but() {
		return (TypeElementFilterBuilder<E>)clone();
	}

	/**
	 * Creates a new {@link TypeElementFilter} based on this builder's settings.
	 * @return the created TypeElementFilter
	 */
	public TypeElementFilter<E> build() {
		TypeElementFilter<E> result = new TypeElementFilter<E>( this.value$elements$java$lang$Iterable );

		if ( this.isSet$simpleNameMatches$java$lang$String) {    
			result.setSimpleNameMatches( this.value$simpleNameMatches$java$lang$String);		
		}
		if ( this.isSet$annotation$java$lang$Class) {    
			result.setAnnotation( this.value$annotation$java$lang$Class);		
		}
		if ( this.isSet$kind$javax$lang$model$element$ElementKind) {    
			result.setKind( this.value$kind$javax$lang$model$element$ElementKind);		
		}
		if ( this.isSet$qualifiedName$java$lang$String) {    
			result.setQualifiedName( this.value$qualifiedName$java$lang$String);		
		}

		return result;
	}

}