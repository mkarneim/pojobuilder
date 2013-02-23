package samples.with.copy;

import java.beans.ConstructorProperties;
import java.util.Date;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withCopyMethod=true)
public class Contact {
	@SuppressWarnings("unused")
	private final String surname;
	protected String firstname;
	String email;
	public Date birthdate;
	public final boolean hasSurname;

	@ConstructorProperties({ "surname" })
	public Contact(String surname) {
		super();
		this.surname = surname;
		this.hasSurname = surname!=null;
	}

}
