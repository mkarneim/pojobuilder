package samples.bestpractice;

import java.util.Date;
import java.util.List;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class Order {
    public Date date;
    public long id;
    public List<Item> items;

    @Override
    public String toString() {
        return "Order [date=" + date + ", id=" + id + ", items=" + items + "]";
    }

}
