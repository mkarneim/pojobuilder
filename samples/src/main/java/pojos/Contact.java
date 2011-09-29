package pojos;

import java.beans.ConstructorProperties;
import java.util.Date;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class Contact { 
	private final String lastname;
	private final String firstname;
	private Date birthdate;
	private String phone;
	private String email;

	@ConstructorProperties({ "firstname","lastname" })
	public Contact(String aFirstname, String aLastname) {
		this.lastname = aLastname;
		this.firstname = aFirstname;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

}
