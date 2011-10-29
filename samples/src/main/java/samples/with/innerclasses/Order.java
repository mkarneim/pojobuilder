package samples.with.innerclasses;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.util.List;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class Order {
	private String customer;
	private Item[] items;
	
	@GeneratePojoBuilder
	public static class Item {
		private String title;
		private BigDecimal pricePerUnit;
		private int units;
		
		@ConstructorProperties({"title","pricePerUnit","units"})
		public Item(String title, BigDecimal pricePerUnit, int units) {
			super();
			this.title = title;
			this.pricePerUnit = pricePerUnit;
			this.units = units;
		}	
	}
	
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	
}
