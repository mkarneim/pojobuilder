group builder;

importStatement(importName) ::= <<
import <importName>;

>>

propertyVar(property) ::= <<

protected <property.type.basename> value$<property.fieldname>;
<if(!property.constructorParameter)>protected boolean hasValue$<property.fieldname> = false;<else>protected final boolean hasValue$<property.fieldname> = true; // mandatory constructor parameter<endif>

>>

propertyWith(property) ::= <<
/**
 * Sets the default value for the {@link <model.productType.basename>#<property.name>} property.
 * @param value the default value
 * @return this builder
 */
public <model.type.basename> with<property.nameUC>( <property.type.basename> value) {
	this.value$<property.fieldname> = value;
<if(!property.constructorParameter)>	this.hasValue$<property.fieldname> = true;<endif>
	return this;
}

>>

propertySetterCall(property) ::= <<
if ( this.hasValue$<property.fieldname>) {    
	result.<property.setter>( this.value$<property.fieldname>);		
}

>>

propertyAssignment(property) ::= <<
if ( this.hasValue$<property.fieldname>) { 
	result.<property.name> = this.value$<property.fieldname>;	
}

>>

productContructorCall(model) ::= <<
<model.productType.basename> result = new <model.productType.basename>( <model.propertiesForConstructor:constructorArgument(); separator=",">);
>>

constructorArgument(property) ::= <<this.value$<property.fieldname> >>

builderClass(model) ::= <<
<if(model.type.package)>package <model.type.package>;


<endif>
import <model.productType.name>;

<model.imports:importStatement()>

/**
 * The {@link <model.type.basename>} is a Builder for {@link <model.productType.basename>} objects.
 *
 * @created <model.created> by the PojoBuilder generator
 */
public class <model.type.basename> <if(!model.superType.object)>extends <model.superType.basename> <endif>implements Cloneable {

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
		return result;
	}
	

}
>>