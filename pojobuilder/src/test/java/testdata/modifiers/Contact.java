package testdata.modifiers;

import java.beans.ConstructorProperties;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class Contact {
	private final String surname;
	protected String firstname;
	String email;

	@ConstructorProperties({ "surname" })
	public Contact(String surname) {
		super();
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "Contact [surname=" + surname + ", firstname=" + firstname + ", email=" + email + "]";
	}

}
