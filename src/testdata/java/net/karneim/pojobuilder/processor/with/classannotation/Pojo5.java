package net.karneim.pojobuilder.processor.with.classannotation;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class Pojo5 {

  private String name;
  private int number = 0;

  public Pojo5(String name) {
    this.name = name;
  }

  @SuppressWarnings("unused")
  private Pojo5() {
    this("default");
  }

  @SuppressWarnings("unused")
  private Pojo5(String name, int number) {
    this.name = name;
    this.number = number;
  }

  public String getName() {
    return name;
  }

  public int getNumber() {
    return number;
  }

}
