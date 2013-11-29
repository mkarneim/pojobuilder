package testdata.basic;

import net.karneim.pojobuilder.GeneratePojoBuilder;

import java.beans.ConstructorProperties;

@GeneratePojoBuilder
public class Sample extends Parent<Long> {

	private boolean active;
	public int number;

	@ConstructorProperties({ "constrField" })
	public Sample(String constrField) {

	}

	public void setActive(boolean aActive) {
		this.active = aActive;
	}

	public boolean isActive() {
		return active;
	}

}
