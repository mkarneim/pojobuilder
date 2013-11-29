package testdata.generationgap;

import net.karneim.pojobuilder.GeneratePojoBuilder;

import java.util.List;

@GeneratePojoBuilder(withGenerationGap = true)
public class Order<T> {
	private String customer;
	private List<T> items;

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

}
