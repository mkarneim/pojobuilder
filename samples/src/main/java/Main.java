import pojos.Contact;
import pojos.ContactBuilder;

public class Main {

	public static void main(String[] args) {
		// Build a single contact
		Contact james = new ContactBuilder()
			.withName("James Bond")
			.withEmail("007@secretservice.org")
			.build();

		// Build 100 contacts with one of the given names and no email 
		Contact[] someContacts = new ContactBuilder()
				.withNameFrom("Alice", "Bob", "Charly")				
				.buildArray( 100);

	}
}
