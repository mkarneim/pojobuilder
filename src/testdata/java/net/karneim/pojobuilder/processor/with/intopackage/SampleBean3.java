package net.karneim.pojobuilder.processor.with.intopackage;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class SampleBean3 {
  public String name;

  @GeneratePojoBuilder(intoPackage="*.builder")
  public SampleBean3(String name) {
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
