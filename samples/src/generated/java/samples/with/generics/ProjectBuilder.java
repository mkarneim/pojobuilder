package samples.with.generics;



import java.io.File;
import java.util.Set;
import samples.with.generics.Project;


/**
 * The {@link ProjectBuilder} is a Builder for {@link Project} objects.
 *
 * 
 *     Please DO NOT MODIFIY this class
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * 
 * @created by the PojoBuilder generator
 */
public class ProjectBuilder implements Cloneable {
	protected final ProjectBuilder self;
	protected Set<File> value$files$java$util$Set; 
	protected boolean isSet$files$java$util$Set = false; 

	protected String value$name$java$lang$String; // mandatory constructor parameter
	protected boolean isSet$name$java$lang$String = false; 



	/**
	 * Creates a new {@link ProjectBuilder}.
	 */
	public ProjectBuilder() {
		self = (ProjectBuilder)this;
	}

	/**
	 * Sets the default value for the {@link Project#files} property.
	 * @param value the default value
	 * @return this builder
	 */
	public ProjectBuilder withFiles( Set<File> value) {
		this.value$files$java$util$Set = value;
		this.isSet$files$java$util$Set = true;
		return self;
	}
	/**
	 * Sets the default value for the {@link Project#name} property.
	 * @param value the default value
	 * @return this builder
	 */
	public ProjectBuilder withName( String value) {
		this.value$name$java$lang$String = value;
		this.isSet$name$java$lang$String = true;
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
	public ProjectBuilder but() {
		return (ProjectBuilder)clone();
	}

	/**
	 * Creates a new {@link Project} based on this builder's settings.
	 * @return the created Project
	 */
	public Project build() {
		Project result = new Project( this.value$name$java$lang$String );

		if ( this.isSet$files$java$util$Set) {    
			result.setFiles( this.value$files$java$util$Set);		
		}

		return result;
	}

}