package samples.with.subclass;

import java.beans.ConstructorProperties;
import java.util.Date;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class MyData extends Data {
    private String name;

    @ConstructorProperties({ "start", "end" })
    public MyData(Date start, Date end) {
        super(start, end);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
