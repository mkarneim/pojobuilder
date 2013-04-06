package samples.with.constructorproperties;

import java.beans.ConstructorProperties;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class AddressDTO {
	public final String name;
	public final String street;
	public final String city;
	public final String postCode;

	/**
	 * @param name
	 * @param street
	 * @param city
	 * @param postCode
	 */
	@ConstructorProperties({ "name", "street", "city", "postCode" })
	public AddressDTO(String name, String street, String city, String postCode) {
		super();
		this.name = name;
		this.street = street;
		this.city = city;
		this.postCode = postCode;
	}

}
