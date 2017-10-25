package net.karneim.pojobuilder;

import javax.lang.model.element.Modifier;

public enum Visibility {
  PUBLIC(Modifier.PUBLIC), PROTECTED(Modifier.PROTECTED), PRIVATE(Modifier.PRIVATE), PACKAGE(null);

  // @Nullable
  private final Modifier modifier;

  private Visibility(Modifier modifier) {
    this.modifier = modifier;
  }

  public Modifier asModifier() {
    return modifier;
  }
}
