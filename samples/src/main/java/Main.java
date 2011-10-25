import java.util.Arrays;

import pojos.Contact;
import pojos.builder.ContactBuilder;

public class Main {

	public static void main(String[] args) {
		System.out.println("Running Sample.");
		// Build a single contact
		Contact james = new ContactBuilder()
			.withName("James Bond")
			.withEmail("007@secretservice.org")
			.build();

		System.out.println("James:\n"+james);
		
		// Build 100 contacts each with one of the given names and no email 
		Contact[] someContacts = new ContactBuilder()
				.withNameFrom("Alice", "Bob", "Charly")				
				.buildArray( 100);
		
		System.out.println("Some contacts:\n"+Arrays.toString( someContacts));

	}
}
