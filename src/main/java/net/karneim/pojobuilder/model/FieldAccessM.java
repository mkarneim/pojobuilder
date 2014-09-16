package net.karneim.pojobuilder.model;

import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PROTECTED;
import static javax.lang.model.element.Modifier.PUBLIC;

import java.util.Set;

import javax.lang.model.element.Modifier;

public class FieldAccessM implements WriteAccess {

  private Set<Modifier> modifier;
  private TypeM declaringClass;

  public FieldAccessM(Set<Modifier> modifier) {
    this.modifier = modifier;
  }

  public Set<Modifier> getModifier() {
    return modifier;
  }

  public TypeM getDeclaringClass() {
    return declaringClass;
  }

  public FieldAccessM declaredIn(TypeM type) {
    this.declaringClass = type;
    return this;
  }

  @Override
  public boolean isVarArgs() {
    return false;
  }

  @Override
  public Type getType() {
    return Type.FIELD;
  }

  public boolean isWritableFor(TypeM accessingClass) {
    if (modifier.contains(FINAL)) {
      return false;
    }
    if (modifier.contains(PRIVATE)) {
      return false;
    }
    if (modifier.contains(PROTECTED)) {
      return accessingClass.isInPackage(declaringClass.getPackageName());
    }
    if (modifier.contains(PUBLIC)) {
      return true;
    }
    return accessingClass.isInPackage(declaringClass.getPackageName());
  }

  public boolean isReadableFor(TypeM accessingClass) {
    if (modifier.contains(PRIVATE)) {
      return false;
    }
    if (modifier.contains(PROTECTED)) {
      return accessingClass.isInPackage(declaringClass.getPackageName());
    }
    if (modifier.contains(PUBLIC)) {
      return true;
    }
    return accessingClass.isInPackage(declaringClass.getPackageName());
  }

  @Override
  public String toString() {
    return "FieldAccessM [modifier=" + modifier + ", declaringClass=" + declaringClass + "]";
  }

}
