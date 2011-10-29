package samples.with.builderpackage;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(intoPackage="samples.with.builderpackages.builder")
public class AddressDTO { 
	public String name;
	public String street;
	public String city;
	public String postCode;
	
}
