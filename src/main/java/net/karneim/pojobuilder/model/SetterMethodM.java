package net.karneim.pojobuilder.model;

import java.util.Set;

import javax.lang.model.element.Modifier;

public class SetterMethodM extends MethodM implements WriteAccess {
  private boolean varArgs;

  public SetterMethodM(String name, Set<Modifier> modifier) {
    super(name, modifier);
  }

  @Override
  public boolean isVarArgs() {
    return varArgs;
  }

  @Override
  public Type getType() {
    return Type.SETTER;
  }

  public SetterMethodM withVarArgs(boolean varArgs) {
    this.varArgs = varArgs;
    return this;
  }

  @Override
  public SetterMethodM declaredIn(TypeM type) {
    super.declaredIn(type);
    return this;
  }

  @Override
  public String toString() {
    return "SetterMethodM [varArgs=" + varArgs + ", getName()=" + getName() + ", getModifier()=" + getModifiers()
        + ", getDeclaringClass()=" + getDeclaringClass() + "]";
  }

}
