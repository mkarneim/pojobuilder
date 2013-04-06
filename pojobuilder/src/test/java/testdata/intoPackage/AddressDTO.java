package testdata.intoPackage;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(intoPackage = "testdata.builder")
public class AddressDTO {
	public String name;
	public String street;
	public String city;
	public String postCode;

}
