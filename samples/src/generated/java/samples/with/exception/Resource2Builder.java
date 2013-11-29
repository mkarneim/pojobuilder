package samples.with.exception;

import java.net.MalformedURLException;
import samples.with.exception.Resource2;
import java.io.FileNotFoundException;
import javax.annotation.Generated;

/**
 * The {@link Resource2Builder} is a Builder for {@link Resource2} objects.
 *
 * <p>
 *     This class has been generated.
 *     Please DO NOT MODIFIY this file
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * </p>
 */
@Generated("PojoBuilder")
public class Resource2Builder implements Cloneable {
	protected Resource2Builder self;
	protected String value$filepath$java$lang$String; // mandatory constructor parameter
	protected boolean isSet$filepath$java$lang$String = false; 

	protected String value$mimeType$java$lang$String; 
	protected boolean isSet$mimeType$java$lang$String = false; 

	protected String value$urlString$java$lang$String; // mandatory constructor parameter
	protected boolean isSet$urlString$java$lang$String = false; 



	/**
	 * Creates a new {@link Resource2Builder}.
	 */
	public Resource2Builder() {
		self = (Resource2Builder)this;
	}

	/**
	 * Sets the default value for the {@link Resource2#filepath} property.
	DO STUFF
	 * @param value the default value
	 * @return this builder
	 */
	public Resource2Builder withFilepath( String value) {
		this.value$filepath$java$lang$String = value;
		this.isSet$filepath$java$lang$String = true;
		return self;
	}
	/**
	 * Sets the default value for the {@link Resource2#mimeType} property.
	DO STUFF
	 * @param value the default value
	 * @return this builder
	 */
	public Resource2Builder withMimeType( String value) {
		this.value$mimeType$java$lang$String = value;
		this.isSet$mimeType$java$lang$String = true;
		return self;
	}
	/**
	 * Sets the default value for the {@link Resource2#urlString} property.
	DO STUFF
	 * @param value the default value
	 * @return this builder
	 */
	public Resource2Builder withUrlString( String value) {
		this.value$urlString$java$lang$String = value;
		this.isSet$urlString$java$lang$String = true;
		return self;
	}


	/**
	 * Returns a clone of this builder.
	 * @return the clone
	 */
	@Override	
	public Object clone() {
		try {
			Resource2Builder result = (Resource2Builder)super.clone();
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
	public Resource2Builder but() {
		return (Resource2Builder)clone();
	}

	/**
	 * Creates a new {@link Resource2} based on this builder's settings.
	 * @return the created Resource2
	 */
	public Resource2 build() throws MalformedURLException ,FileNotFoundException {
		Resource2 result = new Resource2( this.value$urlString$java$lang$String ,this.value$filepath$java$lang$String );

		if ( this.isSet$mimeType$java$lang$String) {    
			result.setMimeType( this.value$mimeType$java$lang$String);		
		}

		return result;
	}

}