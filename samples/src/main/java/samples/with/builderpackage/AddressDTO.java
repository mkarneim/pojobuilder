package samples.with.builderpackage;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(intoPackage="samples.with.builderpackage.builder")
public class AddressDTO { 
	public String name;
	public String street;
	public String city;
	public String postCode;
	
}
