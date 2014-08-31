package net.karneim.pojobuilder.processor.with.generics;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class Pair<A extends Comparable<A>, B extends Number> {
    private A valueA;
    private B valueB;

    public A getValueA() {
        return valueA;
    }

    public void setValueA(A valueA) {
        this.valueA = valueA;
    }

    public B getValueB() {
        return valueB;
    }

    public void setValueB(B valueB) {
        this.valueB = valueB;
    }

}
