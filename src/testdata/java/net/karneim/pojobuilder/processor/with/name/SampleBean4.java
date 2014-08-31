package net.karneim.pojobuilder.processor.with.name;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class SampleBean4 {
  public String name;

  @GeneratePojoBuilder(withName="Fluent*Builder")
  public SampleBean4(String name) {
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
