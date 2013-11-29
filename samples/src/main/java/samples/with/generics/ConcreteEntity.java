package samples.with.generics;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class ConcreteEntity extends AbstractEntity<Long> {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
