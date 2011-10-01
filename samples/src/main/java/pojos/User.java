package pojos;

import java.beans.ConstructorProperties;
import java.io.File;
import java.util.Date;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class User {
	private final long id;
	private final String name;
	private final Date created;
	private File homeDirectory;
	private String passwordHash;

	@ConstructorProperties({ "id", "name", "created" })
	public User(long aId, String aName, Date aCreated) {
		super();
		this.id = aId;
		this.name = aName;
		this.created = aCreated;
	}

	public File getHomeDirectory() {
		return homeDirectory;
	}

	/**
	 * Sets the uses's home directory.
	 * 
	 * @param aHomeDirectory
	 */
	public void setHomeDirectory(File aHomeDirectory) {
		this.homeDirectory = aHomeDirectory;
	}

	/**
	 * Alternate method for setting the user's home directory using a String.
	 * 
	 * @param aHomeDirectory
	 */
	public void setHomeDirectory(String aHomeDirectory) {
		this.homeDirectory = new File(aHomeDirectory);
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String aPasswordHash) {
		this.passwordHash = aPasswordHash;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Date getCreated() {
		return created;
	}

}
