package net.karneim.pojobuilder.processor.with.constructorannotation;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class Pojo1 {
  public String name;

  @GeneratePojoBuilder
  public Pojo1(String name) {
    super();
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
