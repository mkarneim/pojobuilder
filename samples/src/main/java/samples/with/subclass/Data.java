package samples.with.subclass;

import java.beans.ConstructorProperties;
import java.util.Date;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class Data {
    private Date start;
    private Date end;

    @ConstructorProperties({ "start", "end" })
    public Data(Date start, Date end) {
        super();
        this.start = start;
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

}
