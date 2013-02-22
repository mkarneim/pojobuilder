package samples.with.superclass;

import java.beans.ConstructorProperties;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class Person {

	private final Long id;
	private final boolean artificial;
	private String name;

	@ConstructorProperties({ "id", "artificial" })
	public Person(Long id, boolean artificial) {
		super();
		this.id = id;
		this.artificial = artificial;
	}

	public boolean isArtificial() {
		return artificial;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
