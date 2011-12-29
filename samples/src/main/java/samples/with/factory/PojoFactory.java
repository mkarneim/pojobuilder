package samples.with.factory;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.PropertyNames;

public class PojoFactory {

    @GeneratePojoBuilder
<<<<<<< HEAD
    @PropertyNames({ "firstname", "surname" })
    public static Contact createContact(String aFirstname, String aSurname) {
        Contact result = new Contact(aSurname, aFirstname);
=======
    @PropertyNames({ "lastname", "firstname" })
    public static Contact createContact(String aLastname, String aFirstname) {
        // reordering the arguments just for fun
        Contact result = new Contact(aFirstname, aLastname);
>>>>>>> 1fc315b61a33b0dabfe0035a1baa79c1ad3f0c68
        return result;
    }

    @GeneratePojoBuilder
    public static Note createNote() {
        return new Note();
    }
}
