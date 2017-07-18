package net.karneim.pojobuilder.processor.with.staticfactorymethod;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withFactoryMethod = "*", withPublicConstructor = false)
public class Trouble {
  public char a;
}
