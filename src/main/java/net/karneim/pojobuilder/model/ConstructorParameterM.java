package net.karneim.pojobuilder.model;

public class ConstructorParameterM implements WriteAccess, Positional {
  private int pos;
  private boolean varArgs;

  public ConstructorParameterM(int pos) {
    this.pos = pos;
  }

  @Override
  public int getPos() {
    return pos;
  }

  @Override
  public boolean isVarArgs() {
    return varArgs;
  }

  @Override
  public Type getType() {
    return Type.CONSTRUCTOR;
  }

  public ConstructorParameterM withVarArgs(boolean varArgs) {
    this.varArgs = varArgs;
    return this;
  }

  @Override
  public String toString() {
    return "ConstructorParameterM [pos=" + pos + ", varArgs=" + varArgs + "]";
  }
}
