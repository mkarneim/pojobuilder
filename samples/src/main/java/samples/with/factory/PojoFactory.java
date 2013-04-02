package samples.with.factory;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.FactoryProperties;

public class PojoFactory {

	@GeneratePojoBuilder
	@FactoryProperties({ "firstname", "surname" })
	public static Contact createContact(String aFirstname, String aSurname) {
		Contact result = new Contact(aSurname, aFirstname);
		return result;
	}

	@GeneratePojoBuilder
	public static Note createNote() {
		return new Note();
	}
}
