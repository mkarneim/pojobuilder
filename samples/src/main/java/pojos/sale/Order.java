package pojos.sale;


import net.karneim.pojobuilder.GeneratePojoBuilder;
import pojos.builder.BaseBuilder;

@GeneratePojoBuilder(withSuperclass=BaseBuilder.class)
public class Order {
    private String customer;
    private Item[] items;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }
    
    
}
