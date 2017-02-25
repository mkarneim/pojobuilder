package net.karneim.pojobuilder.model;

import javax.lang.model.element.Modifier;

import static java.util.Collections.singleton;

public class CopyMethodM extends MethodM {

  public CopyMethodM(String name) {
    super(name, singleton(Modifier.PUBLIC));
  }

}
