package net.karneim.pojobuilder.model;

import javax.lang.model.element.Modifier;

import static java.util.Collections.singleton;

public class CloneMethodM extends MethodM {

  private boolean catchesCloneException = true;

  public CloneMethodM() {
    super("clone", singleton(Modifier.PUBLIC));
  }

  public boolean isCatchesCloneException() {
    return catchesCloneException;
  }

  public void setCatchesCloneException(boolean catchesCloneException) {
    this.catchesCloneException = catchesCloneException;
  }
}
