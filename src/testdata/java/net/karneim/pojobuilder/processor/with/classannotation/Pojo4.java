package net.karneim.pojobuilder.processor.with.classannotation;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class Pojo4 {
  public String name;

  public Pojo4(String name) {
    this.name = name;
  }

  public Pojo4() {
    this("default");
  }

}
