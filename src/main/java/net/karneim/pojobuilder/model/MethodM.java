package net.karneim.pojobuilder.model;

import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PROTECTED;
import static javax.lang.model.element.Modifier.PUBLIC;

import java.util.Set;

import javax.lang.model.element.Modifier;

public class MethodM {
  private String name;
  private Set<Modifier> modifier;
  private TypeM declaringClass;

  public MethodM(String name, Set<Modifier> modifier) {
    super();
    this.name = name;
    this.modifier = modifier;
  }

  public String getName() {
    return name;
  }

  public Set<Modifier> getModifier() {
    return modifier;
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
    // TODO check modifier PROTECTED or package protected etc.
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



}
