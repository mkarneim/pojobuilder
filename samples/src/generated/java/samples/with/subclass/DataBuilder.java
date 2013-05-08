package samples.with.subclass;

import samples.with.subclass.Data;
import java.util.Date;
import javax.annotation.Generated;

/**
 * The {@link DataBuilder} is a Builder for {@link Data} objects.
 *
 * <p>
 *     This class has been generated.
 *     Please DO NOT MODIFIY this file
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * </p>
 */
@Generated("PojoBuilder")
public class DataBuilder implements Cloneable {
	protected DataBuilder self;
	protected Date value$end$java$util$Date; // mandatory constructor parameter
	protected boolean isSet$end$java$util$Date = false; 

	protected Date value$start$java$util$Date; // mandatory constructor parameter
	protected boolean isSet$start$java$util$Date = false; 



	/**
	 * Creates a new {@link DataBuilder}.
	 */
	public DataBuilder() {
		self = (DataBuilder)this;
	}

	/**
	 * Sets the default value for the {@link Data#end} property.
	 * @param value the default value
	 * @return this builder
	 */
	public DataBuilder withEnd( Date value) {
		this.value$end$java$util$Date = value;
		this.isSet$end$java$util$Date = true;
		return self;
	}
	/**
	 * Sets the default value for the {@link Data#start} property.
	 * @param value the default value
	 * @return this builder
	 */
	public DataBuilder withStart( Date value) {
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
			DataBuilder result = (DataBuilder)super.clone();
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
	public DataBuilder but() {
		return (DataBuilder)clone();
	}

	/**
	 * Creates a new {@link Data} based on this builder's settings.
	 * @return the created Data
	 */
	public Data build() {
		Data result = new Data( this.value$start$java$util$Date ,this.value$end$java$util$Date );

		return result;
	}

}