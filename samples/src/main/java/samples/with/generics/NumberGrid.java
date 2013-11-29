package samples.with.generics;

import java.util.Collection;
import java.util.List;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class NumberGrid<E extends Number> {
    private List<Collection<E>> elements;

    public List<Collection<E>> getElements() {
        return elements;
    }

    public void setElements(List<Collection<E>> elements) {
        this.elements = elements;
    }

}
