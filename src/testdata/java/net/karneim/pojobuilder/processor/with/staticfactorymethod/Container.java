package net.karneim.pojobuilder.processor.with.staticfactorymethod;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withFactoryMethod = "*")
public class Container<T> {
  private T value;

  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    this.value = value;
  }

}
