import samples.Contact;
import samples.builder.ContactBuilder;

public class Main {

    public static void main(String[] args) {
        System.out.println("Running Sample.");
        // Build a contact
        Contact james = new ContactBuilder().withSurname("Bond").withFirstname("James")
                .withEmail("007@secretservice.org").build();

        System.out.println("James:\n" + james);

    }
}
