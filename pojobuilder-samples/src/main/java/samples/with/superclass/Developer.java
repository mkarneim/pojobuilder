package samples.with.superclass;

import java.beans.ConstructorProperties;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class Developer extends Person {
	private String[] languages;

	@ConstructorProperties({ "id" })
	public Developer(Long id) {
		super(id, false);
	}

	public String[] getLanguages() {
		return languages;
	}

	public void setLanguages(String[] languages) {
		this.languages = languages;
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}

}
