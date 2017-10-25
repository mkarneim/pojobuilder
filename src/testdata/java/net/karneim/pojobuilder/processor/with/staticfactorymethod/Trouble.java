package net.karneim.pojobuilder.processor.with.staticfactorymethod;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.Visibility;

@GeneratePojoBuilder(withFactoryMethod = "*", withConstructor = Visibility.PRIVATE)
public class Trouble {
  public char a;
}
