package net.karneim.pojobuilder.model;

import java.util.Set;

import javax.lang.model.element.Modifier;

public class FactoryMethodM extends MethodM {

  public FactoryMethodM(String name, Set<Modifier> modifier) {
    super(name, modifier);
  }

  @Override
  public FactoryMethodM declaredIn(TypeM typeM) {
    super.declaredIn(typeM);
    return this;
  }

}
