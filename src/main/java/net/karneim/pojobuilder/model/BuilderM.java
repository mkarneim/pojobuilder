package net.karneim.pojobuilder.model;

import net.karneim.pojobuilder.Visibility;

public class BuilderM {
  private TypeM type;
  private boolean isAbstract;
  private TypeM selfType;
  private TypeM baseType;
  private TypeM pojoType;
  private PropertyListM properties = new PropertyListM();
  private FactoryMethodM factoryMethod;
  private CopyMethodM copyMethod;
  private BuildMethodM buildMethod;
  private ValidatorM validator;
  private TypeM interfaceType;
  private boolean hasBuilderProperties;
  private OptionalM optional;
  private StaticFactoryMethodM staticFactoryMethod;
  private Visibility constructorVisibility = Visibility.PUBLIC;
  private CloneMethodM cloneMethod;

  public TypeM getType() {
    return type;
  }

  public void setType(TypeM type) {
    this.type = type;
  }

  public boolean isAbstract() {
    return isAbstract;
  }

  public void setAbstract(boolean isAbstract) {
    this.isAbstract = isAbstract;
  }

  public TypeM getSelfType() {
    return selfType;
  }

  public void setSelfType(TypeM selfType) {
    this.selfType = selfType;
  }

  public TypeM getBaseType() {
    return baseType;
  }

  public void setBaseType(TypeM baseType) {
    this.baseType = baseType;
  }

  public TypeM getPojoType() {
    return pojoType;
  }

  public void setPojoType(TypeM pojoType) {
    this.pojoType = pojoType;
  }

  public PropertyListM getProperties() {
    return properties;
  }

  public void setProperties(PropertyListM properties) {
    this.properties = properties;
  }

  public FactoryMethodM getFactoryMethod() {
    return factoryMethod;
  }

  public void setFactoryMethod(FactoryMethodM factoryMethod) {
    this.factoryMethod = factoryMethod;
  }

  public CopyMethodM getCopyMethod() {
    return copyMethod;
  }

  public void setCopyMethod(CopyMethodM copyMethod) {
    this.copyMethod = copyMethod;
  }

  public BuildMethodM getBuildMethod() {
    return buildMethod;
  }

  public void setBuildMethod(BuildMethodM buildMethod) {
    this.buildMethod = buildMethod;
  }

  public ValidatorM getValidator() {
    return validator;
  }

  public void setValidator(ValidatorM validator) {
    this.validator = validator;
  }

  public TypeM getInterfaceType() {
    return interfaceType;
  }

  public void setInterfaceType(TypeM interfaceType) {
    this.interfaceType = interfaceType;
  }

  public boolean hasBuilderProperties() {
    return hasBuilderProperties;
  }

  public void setHasBuilderProperties(boolean hasBuilderProperties) {
    this.hasBuilderProperties = hasBuilderProperties;
  }

  public OptionalM getOptional() {
    return optional;
  }

  public void setOptional(OptionalM optional) {
    this.optional = optional;
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

  public void setConstructorVisibility(Visibility constructorVisibility) {
    this.constructorVisibility = constructorVisibility;
  }

  public CloneMethodM getCloneMethod() {
    return cloneMethod;
  }

  public void setCloneMethod(CloneMethodM cloneMethod) {
    this.cloneMethod = cloneMethod;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "BuilderM [type=" + type + ", isAbstract=" + isAbstract + ", selfType=" + selfType + ", baseType=" + baseType
        + ", pojoType=" + pojoType + ", properties=" + properties + ", factoryMethod=" + factoryMethod + ", copyMethod="
        + copyMethod + ", buildMethod=" + buildMethod + ", validator=" + validator + ", interfaceType=" + interfaceType
        + ", hasBuilderProperties=" + hasBuilderProperties + ", optional=" + optional + ", staticFactoryMethod="
        + staticFactoryMethod + ", constructorVisibility=" + constructorVisibility + ", cloneMethod=" + cloneMethod
        + "]";
  }

}
