package samples;

import net.karneim.pojobuilder.processor.with.copymethod.Address;
import net.karneim.pojobuilder.processor.with.copymethod.AddressBuilder;

public class DemoMain {
  
  public static void main(String[] args) {
    basicSample();
    sampleWithCopyMethod();
  }
  
  private static void basicSample() {
    Contact james =
        new ContactBuilder()
          .withSurname("Bond")
          .withFirstname("James")
          .withEmail("007@secretservice.org")
          .build();
    System.out.println(james.toString());
  }
  
  private static void sampleWithCopyMethod() {
    Address address1 = new Address();
    address1.setStreet("221b Baker Street");
    address1.setCity("London");
    address1.setPostCode("NW1 3RX");
    System.out.println(address1);
    
    Address address2 = new AddressBuilder()
      .copy(address1)
      .build();
    System.out.println(address2);
  }

}
