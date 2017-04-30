package net.karneim.pojobuilder.model;

public class CloneMethodM {
  private boolean shouldCatchCloneNotSupportedException;

  public CloneMethodM() {}

  public boolean shouldCatchCloneNotSupportedException() {
    return shouldCatchCloneNotSupportedException;
  }

  public CloneMethodM setShouldCatchCloneNotSupportedException(boolean value) {
    this.shouldCatchCloneNotSupportedException = value;
    return this;
  }

  @Override
  public String toString() {
    return "BuildMethodM [shouldCatchCloneNotSupportedException="
        + shouldCatchCloneNotSupportedException + "]";
  }

}
