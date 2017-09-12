package net.karneim.pojobuilder.model;

public class FactoryMethodParameterM implements WriteAccess, Positional {
  private int pos;
  private boolean varArgs;

  public FactoryMethodParameterM(int pos) {
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
    return Type.FACTORY;
  }

  public FactoryMethodParameterM withVarArgs(boolean varArgs) {
    this.varArgs = varArgs;
    return this;
  }

  @Override
  public String toString() {
    return "FactoryMethodParameterM [pos=" + pos + ", varArgs=" + varArgs + "]";
  }
}
