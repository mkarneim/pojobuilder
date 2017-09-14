package net.karneim.pojobuilder.model;

import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PROTECTED;
import static javax.lang.model.element.Modifier.PUBLIC;

import java.util.Set;

import javax.lang.model.element.Modifier;

public class MethodM {
  private String name;
  private Set<Modifier> modifiers;
  private TypeM declaringClass;

  public MethodM(String name, Set<Modifier> modifiers) {
    this.name = name;
    this.modifiers = modifiers;
  }

  public String getName() {
    return name;
  }

  public Set<Modifier> getModifiers() {
    return modifiers;
  }

  public TypeM getDeclaringClass() {
    return declaringClass;
  }

  public MethodM declaredIn(TypeM type) {
    this.declaringClass = type;
    return this;
  }

  public boolean isAccessibleFor(TypeM accessingClass) {
    if (accessingClass == null) {
      throw new NullPointerException("accessingClass==null!");
    }
    if (declaringClass == null) {
      throw new IllegalStateException(String.format("Missing declaringClass in MethodM %s", name));
    }
    // TODO check modifiers PROTECTED or package protected etc.
    if (modifiers.contains(PRIVATE)) {
      return false;
    }
    if (modifiers.contains(PROTECTED)) {
      return accessingClass.isInPackage(declaringClass.getPackageName());
    }
    if (modifiers.contains(PUBLIC)) {
      return true;
    }
    return accessingClass.isInPackage(declaringClass.getPackageName());
  }

  @Override
  public String toString() {
    return "MethodM [name=" + name + ", modifiers=" + modifiers + ", declaringClass=" + declaringClass + "]";
  }



}
