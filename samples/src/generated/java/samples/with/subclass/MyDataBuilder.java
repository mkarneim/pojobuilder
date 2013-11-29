package samples.with.subclass;

import java.util.Date;
import javax.annotation.Generated;
import samples.with.subclass.MyData;

/**
 * The {@link MyDataBuilder} is a Builder for {@link MyData} objects.
 *
 * <p>
 *     This class has been generated.
 *     Please DO NOT MODIFIY this file
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * </p>
 */
@Generated("PojoBuilder")
public class MyDataBuilder implements Cloneable {
	protected MyDataBuilder self;
	protected Date value$end$java$util$Date; // mandatory constructor parameter
	protected boolean isSet$end$java$util$Date = false; 

	protected String value$name$java$lang$String; 
	protected boolean isSet$name$java$lang$String = false; 

	protected Date value$start$java$util$Date; // mandatory constructor parameter
	protected boolean isSet$start$java$util$Date = false; 



	/**
	 * Creates a new {@link MyDataBuilder}.
	 */
	public MyDataBuilder() {
		self = (MyDataBuilder)this;
	}

	/**
	 * Sets the default value for the {@link MyData#end} property.
	DO STUFF
	 * @param value the default value
	 * @return this builder
	 */
	public MyDataBuilder withEnd( Date value) {
		this.value$end$java$util$Date = value;
		this.isSet$end$java$util$Date = true;
		return self;
	}
	/**
	 * Sets the default value for the {@link MyData#name} property.
	DO STUFF
	 * @param value the default value
	 * @return this builder
	 */
	public MyDataBuilder withName( String value) {
		this.value$name$java$lang$String = value;
		this.isSet$name$java$lang$String = true;
		return self;
	}
	/**
	 * Sets the default value for the {@link MyData#start} property.
	DO STUFF
	 * @param value the default value
	 * @return this builder
	 */
	public MyDataBuilder withStart( Date value) {
		this.value$start$java$util$Date = value;
		this.isSet$start$java$util$Date = true;
		return self;
	}


	/**
	 * Returns a clone of this builder.
	 * @return the clone
	 */
	@Override	
	public Object clone() {
		try {
			MyDataBuilder result = (MyDataBuilder)super.clone();
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
	public MyDataBuilder but() {
		return (MyDataBuilder)clone();
	}

	/**
	 * Creates a new {@link MyData} based on this builder's settings.
	 * @return the created MyData
	 */
	public MyData build() {
		MyData result = new MyData( this.value$start$java$util$Date ,this.value$end$java$util$Date );

		if ( this.isSet$name$java$lang$String) {    
			result.setName( this.value$name$java$lang$String);		
		}

		return result;
	}

}