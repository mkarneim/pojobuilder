group builder;

propertyImport(importName) ::= <<
import <importName>;

>>

propertyVar(property) ::= <<
private <property.type.basename>[] <property.fieldname> = new <property.type.basename>[] {<if(property.constructorParameter)><property.type.defaultValue><endif>};<if(property.constructorParameter)> // constructor parameter<endif>

>>

propertyWith(property) ::= <<
/**
 * Sets the default value for the {@link <model.productType.basename>#<property.name>} property.
 * @param value the default value
 * @return this builder
 */
public <model.type.basename> with<property.nameUC>( <property.type.basename> value) {
	this.<property.fieldname> = new <property.type.basename>[] { value };
	return this;
}

/**
 * Sets the default values for the {@link <model.productType.basename>#<property.name>} property.
 * @param values the default values
 * @return this builder
 */
public <model.type.basename> with<property.nameUC>From( <property.type.basename>... values) {
	if ( values == null) {
		throw new IllegalArgumentException("array of default values must not be null!");
	}
<if(property.constructorParameter)>
	if ( values.length == 0) {
		throw new IllegalArgumentException("array of default values for a constructor parameter must contain at least one element");
	}

<endif>
	this.<property.fieldname> = values;
	return this;
}

>>

propertySetterCall(property) ::= <<
if ( <property.fieldname>.length > 0) {    
	result.<property.setter>( <property.fieldname>[buildCount%<property.fieldname>.length]);		
}

>>

propertyAssignment(property) ::= <<
if ( <property.fieldname>.length > 0) {
	result.<property.name> = <property.fieldname>[buildCount%<property.fieldname>.length];	
}

>>

productContructorCall(model) ::= <<
<model.productType.basename> result = new <model.productType.basename>( <model.propertiesForConstructor:constructorArgument(); separator=", ">);
>>

constructorArgument(property) ::= <<<property.fieldname>[buildCount%<property.fieldname>.length]>>

productContructorParameter(property) ::= <<
<property.type.basename> <property.fieldname>

>>

builderClass(model) ::= <<
<if(model.type.package)>package <model.type.package>;


<endif>
import <model.productType.name>;
import java.util.ArrayList;
import java.util.List;

<model.propertyImports:propertyImport()>

/**
 * The {@link <model.type.basename>} is a Builder for {@link <model.productType.basename>} objects.
 *
 * @created <model.created> by the PojoBuilder generator
 */
public class <model.type.basename> implements Cloneable {
	/*
	 * Counter of {@link #build} invocations. 
	 */
	private int buildCount = 0;

	<model.properties:propertyVar()>
	
	/**
	 * Creates a new {@link <model.type.basename>}.
	 */
	public <model.type.basename>() {
	}
	
	<model.properties:propertyWith()>
	
	/**
	 * Returns a clone of this builder.
	 * @return the clone
	 */
	@Override
	public Object clone() {
		try {
			<model.type.basename> result = (<model.type.basename>)super.clone();
			result.buildCount = 0;
			return result;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.getMessage());
		}        
	}
    
	/**
	 * Returns a clone of this builder.
	 * @return the clone
	 */
	public <model.type.basename> but() {
		return (<model.type.basename>)clone();
	}
	
	/**
	 * Creates a new {@link <model.productType.basename>} based on this builders settings.
	 * @return the created <model.productType.basename>
	 */
	public <model.productType.basename> build() {
		<model:productContructorCall()>
		<model.propertiesForSetters:propertySetterCall()>
		<model.propertiesForAssignment:propertyAssignment()>
		
		buildCount++;    
		return result;
	}
	
	/**
	 * Creates an array of new {@link <model.productType.basename>} objects with the given number of elements.
	 * @param size the number of elements
	 * @return the array containing the new objects
	 */
	public <model.productType.basename>[] buildArray(int size) {
		<model.productType.basename>[] result = new <model.productType.basename>[size];
		for( int i=0; i\<size; ++i) {
			result[i] = build();
		}
		return result;
	}
	
	/**
	 * Creates a list of new {@link <model.productType.basename>} objects with the given number of elements.
	 * @param size the number of elements
	 * @return the list containing the new objects
	 */
	public List\<<model.productType.basename>\> buildList(int size) { 
		List\<<model.productType.basename>\> result = new ArrayList\<<model.productType.basename>\>(size);
		for( int i=0; i\<size; ++i) {
			result.add( build());
		}
		return result;
	}

}
>>