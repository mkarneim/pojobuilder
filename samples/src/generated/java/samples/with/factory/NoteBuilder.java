package samples.with.factory;



import samples.with.factory.Note;
import samples.with.factory.PojoFactory;


/**
 * The {@link NoteBuilder} is a Builder for {@link Note} objects.
 *
 * 
 *     Please DO NOT MODIFIY this class
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * 
 * @created by the PojoBuilder generator
 */
public class NoteBuilder implements Cloneable {
	protected final NoteBuilder self;
	protected String value$text$java$lang$String; 
	protected boolean isSet$text$java$lang$String = false; 



	/**
	 * Creates a new {@link NoteBuilder}.
	 */
	public NoteBuilder() {
		self = (NoteBuilder)this;
	}

	/**
	 * Sets the default value for the {@link Note#text} property.
	 * @param value the default value
	 * @return this builder
	 */
	public NoteBuilder withText( String value) {
		this.value$text$java$lang$String = value;
		this.isSet$text$java$lang$String = true;
		return self;
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
	public NoteBuilder but() {
		return (NoteBuilder)clone();
	}

	/**
	 * Creates a new {@link Note} based on this builder's settings.
	 * @return the created Note
	 */
	public Note build() {
		Note result = PojoFactory.createNote( );

		if ( this.isSet$text$java$lang$String) { 
			result.text = this.value$text$java$lang$String;	
		}

		return result;
	}

}