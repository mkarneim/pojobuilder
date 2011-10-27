package pojos.sale;
import pojos.Contact;
import pojos.builder.ContactBuilder;

public class Main {

    public static void main(String[] args) {
        System.out.println("Running Sample.");
        // Build a single contact
        Contact james = new ContactBuilder().withName("James Bond").withEmail("007@secretservice.org").build();

        System.out.println("James:\n" + james);

    }
}
