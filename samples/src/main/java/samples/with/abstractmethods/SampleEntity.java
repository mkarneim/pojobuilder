package samples.with.abstractmethods;

import java.io.Serializable;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class SampleEntity extends Entity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    public SampleEntity() {
    }

    @Override
    public Long getKey() {
        return id;
    }

    @Override
    public void setKey(Long key) {
        this.id = key;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("SampleEntity with id=%s, name=%s", id, name);
    }

}
