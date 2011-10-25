package pojos;

import java.math.BigDecimal;

public class Item {
    
    public String text;
    public BigDecimal price;
    
    public Item(String text, BigDecimal price) {
        super();
        this.text = text;
        this.price = price;
    }
    public Item() {
        super();
    }
    
    
    
}
