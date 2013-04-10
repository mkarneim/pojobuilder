package samples.with.constructorproperties;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.Date;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class Contact implements Serializable {

	private static final long serialVersionUID = 1L;
	private final String firstname;
	private final String surname;
	private final Date dateOfBirth;

	@ConstructorProperties({ "firstname", "surname", "dateOfBirth" })
	public Contact(String firstname, String surname, Date dateOfBirth) {
		super();
		this.firstname = firstname;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getSurname() {
		return surname;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	@Override
	public String toString() {
		return "Contact [firstname=" + firstname + ", surname=" + surname + ", dateOfBirth=" + dateOfBirth + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

}
