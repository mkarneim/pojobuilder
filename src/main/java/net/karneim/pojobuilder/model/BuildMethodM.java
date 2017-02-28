package net.karneim.pojobuilder.model;

import javax.lang.model.element.Modifier;

import java.util.EnumSet;

import static java.util.Collections.singleton;

public class BuildMethodM extends MethodM {

  public BuildMethodM() {
    super("build", singleton(Modifier.PUBLIC));
  }

  public BuildMethodM(Modifier modifier) {
    super("build", EnumSet.of(Modifier.PUBLIC, modifier));
  }
}
