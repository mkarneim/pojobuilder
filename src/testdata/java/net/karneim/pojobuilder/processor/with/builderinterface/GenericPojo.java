package net.karneim.pojobuilder.processor.with.builderinterface;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class GenericPojo<P extends Number> {
  public String name;
  private P content;

  @GeneratePojoBuilder(withBuilderInterface = Builder.class, withBuilderProperties = true)
  public GenericPojo(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public P getContent() {
    return content;
  }

  public void setContent(P content) {
    this.content = content;
  }
}
