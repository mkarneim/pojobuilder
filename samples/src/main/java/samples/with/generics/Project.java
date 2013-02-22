package samples.with.generics;

import java.beans.ConstructorProperties;
import java.io.File;
import java.util.Set;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class Project {
	private String name;
	private Set<File> files;

	@ConstructorProperties({ "name" })
	public Project(String name) {
		super();
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Set<File> getFiles() {
		return files;
	}

	public void setFiles(Set<File> files) {
		this.files = files;
	}

}
