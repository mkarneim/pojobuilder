package net.karneim.pojobuilder.processor.with.classannotation;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class Pojo2 {
  public String name;

  public Pojo2() {

  }

  public Pojo2(String name) {
    this.name = name;
  }

}
