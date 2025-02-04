package samples;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withBuilderInterface = Builder.class, withBuilderProperties = true)
public class Recipient {
  private String name;
  private Address address;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

}
