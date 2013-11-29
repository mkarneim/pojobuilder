package samples.with.generics;

import java.awt.Color;
import java.util.List;
import java.util.Set;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class Pallettes {
    private List<Set<Color>> elements;

    public List<Set<Color>> getElements() {
        return elements;
    }

    public void setElements(List<Set<Color>> elements) {
        this.elements = elements;
    }

}
