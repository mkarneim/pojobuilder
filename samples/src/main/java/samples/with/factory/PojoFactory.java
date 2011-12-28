package samples.with.factory;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.PropertyNames;

public class PojoFactory {

    @GeneratePojoBuilder
    @PropertyNames({ "lastname", "firstname" })
    public static Contact createContact(String aLastname, String aFirstname) {
        // reordering the arguments just for fun
        Contact result = new Contact(aFirstname, aLastname);
        return result;
    }

    @GeneratePojoBuilder
    public static Note createNote() {
        return new Note();
    }
}
