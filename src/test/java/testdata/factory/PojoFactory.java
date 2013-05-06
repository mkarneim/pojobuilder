package testdata.factory;

import net.karneim.pojobuilder.FactoryProperties;
import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.PropertyNames;

@SuppressWarnings("deprecation")
public class PojoFactory {

    /**
     * Test factory method with deprecated property-naming annotation
     */
    @GeneratePojoBuilder
	@PropertyNames({ "firstname", "surname" })
	public static Contact createContact(String aFirstname, String aSurname) {
		Contact result = new Contact(aSurname, aFirstname);
		return result;
	}

    /**
     * Test factory method with property-naming annotation
     */
    @GeneratePojoBuilder
    @FactoryProperties({ "firstname", "surname" })
    public static Contact createContact2(String aFirstname, String aSurname) {
        Contact result = new Contact(aSurname, aFirstname);
        return result;
    }

    /**
     * Test factory method fails with both annotations
     */
    @GeneratePojoBuilder
    @FactoryProperties({ "firstname", "surname" })
    @PropertyNames({ "firstname", "surname" })
    public static Contact createContact3(String aFirstname, String aSurname) {
        Contact result = new Contact(aSurname, aFirstname);
        return result;
    }

    /**
     * Test factory method with implicit-property-naming
     */
    @GeneratePojoBuilder
    public static Contact createContactImplicit(String firstname, String surname) {
        Contact result = new Contact(surname, firstname);
        return result;
    }

    /**
     * Test factory method with no properties
     */
	@GeneratePojoBuilder
	public static Note createNote() {
		return new Note();
	}
}
