package net.karneim.pojobuilder.model;

import net.karneim.pojobuilder.Visibility;

public class ManualBuilderM {

  private TypeM pojoType;
  private TypeM type;
  private TypeM baseType;
  private StaticFactoryMethodM staticFactoryMethod;
  private Visibility constructorVisibility = Visibility.PUBLIC;

  public TypeM getPojoType() {
    return pojoType;
  }

  public void setPojoType(TypeM pojoType) {
    this.pojoType = pojoType;
  }

  public TypeM getType() {
    return type;
  }

  public void setType(TypeM type) {
    this.type = type;
  }

  public TypeM getBaseType() {
    return baseType;
  }

  public void setBaseType(TypeM baseType) {
    this.baseType = baseType;
  }

  public StaticFactoryMethodM getStaticFactoryMethod() {
    return staticFactoryMethod;
  }

  public void setStaticFactoryMethod(StaticFactoryMethodM staticFactoryMethod) {
    this.staticFactoryMethod = staticFactoryMethod;
  }

  public Visibility getConstructorVisibility() {
    return constructorVisibility;
  }

  public void setConstructorVisibility(Visibility visibility) {
    this.constructorVisibility = visibility;
  }

  @Override
  public String toString() {
    return "ManualBuilderM [pojoType=" + pojoType + ", type=" + type + ", baseType=" + baseType
        + ", staticFactoryMethod=" + staticFactoryMethod + ", constructorVisibility=" + constructorVisibility + "]";
  }

}
