package samples.with.generationgap.handwritten;

public class UseContactBuilder {
    public static void main(String[] args) {

        ContactBuilder builder = new ContactBuilder();
        builder.withName("Jean");
        builder.withEmailAddress("jean.paul@example.com");

        Contact c = builder.build();
        System.out.println(c.toString());

    }
}
