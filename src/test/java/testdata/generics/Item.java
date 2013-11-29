package testdata.generics;

import net.karneim.pojobuilder.GeneratePojoBuilder;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;

@GeneratePojoBuilder
public class Item {
    private String title;
    private BigDecimal pricePerUnit;
    private int units;

    @ConstructorProperties({ "title", "pricePerUnit", "units" })
    public Item(String title, BigDecimal pricePerUnit, int units) {
        super();
        this.title = title;
        this.pricePerUnit = pricePerUnit;
        this.units = units;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public int getUnits() {
        return units;
    }

}
