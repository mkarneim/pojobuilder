package samples.with.builderpackage;

import java.beans.ConstructorProperties;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(intoPackage = "*.builder")
public class Contact {
	private final String surname;
	private final String firstname;
	private String email;

	@ConstructorProperties({ "surname", "firstname" })
	public Contact(String aSurname, String aFirstname) {
		super();
		this.surname = aSurname;
		this.firstname = aFirstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSurname() {
		return surname;
	}

	public String getFirstname() {
		return firstname;
	}

	@Override
	public String toString() {
		return "Contact [surname=" + surname + ", firstname=" + firstname + ", email=" + email + "]";
	}

}
