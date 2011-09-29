import pojos.Contact;
import pojos.ContactBuilder;

public class Main {

	public static void main(String[] args) {
		// Building a single object
		Contact james = new ContactBuilder().withFirstname("James")
				.withLastname("Gosling").build();

		// Mass building 
		Contact[] someContacts = new ContactBuilder()
				.withFirstnameFrom("Alice", "Bob", "Charly")
				.withLastnameFrom("Myers", "Smith", "Bell", "Hendrix", "Peters")
				.buildArray( 100);

	}
}
