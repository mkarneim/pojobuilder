package net.karneim.pojobuilder.processor.with.builderinheritance;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public abstract class AbstractPojo {
  public int field;

  @GeneratePojoBuilder(withName = "ConcretePojoBuilder")
  public static AbstractPojo instantiate(final int field2) {
    return new AbstractPojo() {
      {field = field2;}
    };
  }
}
