package samples.with.generics;

import java.io.File;
import java.util.Set;
import samples.with.generics.Project;
import javax.annotation.Generated;

/**
 * The {@link ProjectBuilder} is a Builder for {@link Project} objects.
 *
 * <p>
 *     This class has been generated.
 *     Please DO NOT MODIFIY this file
 *     since it will be overwritten 
 *     by the PojoBuilder generator.
 * </p>
 */
@Generated("PojoBuilder")
public class ProjectBuilder implements Cloneable {
	protected ProjectBuilder self;
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
			ProjectBuilder result = (ProjectBuilder)super.clone();
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