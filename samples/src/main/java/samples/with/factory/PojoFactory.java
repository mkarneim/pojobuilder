package samples.with.factory;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.FactoryProperties;

public class PojoFactory {

    @GeneratePojoBuilder
    public static Note createNote() {
        return new Note();
    }

    @GeneratePojoBuilder
	public static Contact createContact(String firstname, String surname) {
		return new Contact(surname, firstname);
	}

    @GeneratePojoBuilder(withName="*Monteur")
    @FactoryProperties({ "firstname", "surname" })
    public static Contact construireContact(String prenom, String nomDeFamille) {
        return new Contact(prenom, nomDeFamille);
    }

}
