package samples.with.exception;

import samples.with.exception.Resource;
import java.net.MalformedURLException;
import javax.annotation.Generated;

/**
 * The {@link ResourceBuilder} is a Builder for {@link Resource} objects.
 *
 * <p>
 *     This class has been generated.
 *     Please DO NOT MODIFIY this file
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * </p>
 */
@Generated("PojoBuilder")
public class ResourceBuilder implements Cloneable {
	protected ResourceBuilder self;
	protected String value$mimeType$java$lang$String; 
	protected boolean isSet$mimeType$java$lang$String = false; 

	protected String value$urlString$java$lang$String; // mandatory constructor parameter
	protected boolean isSet$urlString$java$lang$String = false; 



	/**
	 * Creates a new {@link ResourceBuilder}.
	 */
	public ResourceBuilder() {
		self = (ResourceBuilder)this;
	}

	/**
	 * Sets the default value for the {@link Resource#mimeType} property.
	 * @param value the default value
	 * @return this builder
	 */
	public ResourceBuilder withMimeType( String value) {
		this.value$mimeType$java$lang$String = value;
		this.isSet$mimeType$java$lang$String = true;
		return self;
	}
	/**
	 * Sets the default value for the {@link Resource#urlString} property.
	 * @param value the default value
	 * @return this builder
	 */
	public ResourceBuilder withUrlString( String value) {
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
			ResourceBuilder result = (ResourceBuilder)super.clone();
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
	public ResourceBuilder but() {
		return (ResourceBuilder)clone();
	}

	/**
	 * Creates a new {@link Resource} based on this builder's settings.
	 * @return the created Resource
	 */
	public Resource build() throws MalformedURLException {
		Resource result = new Resource( this.value$urlString$java$lang$String );

		if ( this.isSet$mimeType$java$lang$String) {    
			result.setMimeType( this.value$mimeType$java$lang$String);		
		}

		return result;
	}

}