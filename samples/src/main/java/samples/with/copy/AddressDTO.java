package samples.with.copy;

import java.beans.ConstructorProperties;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withCopyMethod=true)
public class AddressDTO { 
	public final String name;
	public final String street;
	public final String city;
	public final String postCode;
	public final boolean forSale;
	
	/**
	 * @param name
	 * @param street
	 * @param city
	 * @param postCode
	 */
	@ConstructorProperties({"name","street","city","postCode", "forSale"})
	public AddressDTO(String name, String street, String city, String postCode, boolean forSale) {
		super();
		this.name = name;
		this.street = street;
		this.city = city;
		this.postCode = postCode;
		this.forSale = forSale;
	}

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return the street
     */
    public String getStreet()
    {
        return street;
    }

    /**
     * @return the city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * @return the postCode
     */
    public String getPostCode()
    {
        return postCode;
    }

    /**
     * @return the forSale
     */
    public boolean isForSale()
    {
        return forSale;
    }
}
