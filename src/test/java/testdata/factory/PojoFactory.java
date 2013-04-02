package testdata.factory;

import net.karneim.pojobuilder.FactoryProperties;
import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.PropertyNames;

@SuppressWarnings("deprecation")
public class PojoFactory {

    @GeneratePojoBuilder
	@PropertyNames({ "firstname", "surname" })
	public static Contact createContact(String aFirstname, String aSurname) {
		Contact result = new Contact(aSurname, aFirstname);
		return result;
	}

    @GeneratePojoBuilder
    @FactoryProperties({ "firstname", "surname" })
    public static Contact createContact2(String aFirstname, String aSurname) {
        Contact result = new Contact(aSurname, aFirstname);
        return result;
    }

    @GeneratePojoBuilder
    @FactoryProperties({ "firstname", "surname" })
    @PropertyNames({ "firstname", "surname" })
    public static Contact createContact3(String aFirstname, String aSurname) {
        Contact result = new Contact(aSurname, aFirstname);
        return result;
    }

	@GeneratePojoBuilder
	public static Note createNote() {
		return new Note();
	}
}
