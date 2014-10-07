package samples;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withBuilderInterface = Builder.class, withBuilderProperties = true)
public class Address {
  private String street;
  private String city;
  private String postCode;

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getPostCode() {
    return postCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  @Override
  public String toString() {
    return "Address [street=" + street + ", city=" + city + ", postCode=" + postCode + "]";
  }

}
