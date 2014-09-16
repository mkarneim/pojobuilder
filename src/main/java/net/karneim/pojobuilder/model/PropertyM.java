package net.karneim.pojobuilder.model;

public class PropertyM {
  private TypeM propertyType;
  private String propertyName;
  private ConstructorParameterM writableViaConstructorParameter;
  private SetterMethodM writableViaSetterMethod;
  private MethodM readableViaGetterMethod;
  private FactoryMethodParameterM writableViaFactoryMethodParameter;
  private FieldAccessM fieldAccess;

  public PropertyM(String propertyName, TypeM propertyType) {
    this.propertyType = propertyType;
    this.propertyName = propertyName;
  }

  public TypeM getPropertyType() {
    return propertyType;
  }

  public String getPropertyName() {
    return propertyName;
  }

  public TypeM getParameterizedBuilderInterfaceType(TypeM interfaceType) {
    if (propertyType.isPrimitive()) {
      PrimitiveTypeM primType = (PrimitiveTypeM) propertyType;
      TypeM result =
          new TypeM(interfaceType.getPackageName(), interfaceType.getSimpleName()).withTypeParameter(primType
              .getBoxClass());
      return result;
    } else {
      TypeM result =
          new TypeM(interfaceType.getPackageName(), interfaceType.getSimpleName()).withTypeParameter(propertyType);
      return result;
    }
  }

  public ConstructorParameterM getConstructorParameter() {
    return writableViaConstructorParameter;
  }

  public PropertyM writableVia(ConstructorParameterM constructorParameter) {
    this.writableViaConstructorParameter = constructorParameter;
    return this;
  }

  public boolean isWritableViaConstructor() {
    // TODO add method isWritableViaConstructorBy( TypeM accessingClass)
    return getConstructorParameter() != null;
  }

  public PropertyM writableVia(SetterMethodM setterMethod) {
    this.writableViaSetterMethod = setterMethod;
    return this;
  }

  public SetterMethodM getSetterMethod() {
    return writableViaSetterMethod;
  }

  public boolean isWritableViaSetterMethod() {
    return writableViaSetterMethod != null;
  }

  public PropertyM readableVia(MethodM getterMethod) {
    this.readableViaGetterMethod = getterMethod;
    return this;
  }

  public MethodM getGetterMethod() {
    return readableViaGetterMethod;
  }

  public boolean isReadableViaGetterMethod() {
    return readableViaGetterMethod != null;
  }

  public PropertyM accessibleVia(FieldAccessM fieldAccess) {
    this.fieldAccess = fieldAccess;
    return this;
  }

  public FieldAccessM getFieldAccess() {
    return fieldAccess;
  }

  public boolean isAccessibleViaFieldAccess() {
    return fieldAccess != null;
  }

  public FactoryMethodParameterM getFactoryMethodParameter() {
    return writableViaFactoryMethodParameter;
  }

  public PropertyM writableVia(FactoryMethodParameterM param) {
    this.writableViaFactoryMethodParameter = param;
    return this;
  }

  public boolean isWritableViaFactoryMethod() {
    // TODO add method isWritableViaFactoryMethodBy( TypeM accessingClass)
    return this.writableViaFactoryMethodParameter != null;
  }

  public boolean isWritableViaSetterMethodBy(TypeM accessingClass) {
    return isWritableViaSetterMethod() && getSetterMethod().isAccessibleFor(accessingClass);
  }

  public boolean isWritableViaFieldAccessBy(TypeM accessingClass) {
    return isAccessibleViaFieldAccess() && getFieldAccess().isWritableFor(accessingClass);
  }

  public boolean isWritableBy(TypeM accessingClass) {
    return isWritableViaFieldAccessBy(accessingClass) || isWritableViaSetterMethodBy(accessingClass)
        || isWritableViaConstructor() || isWritableViaFactoryMethod();
  }

  public boolean isReadableViaGetterMethodBy(TypeM accessingClass) {
    return isReadableViaGetterMethod() && getGetterMethod().isAccessibleFor(accessingClass);
  }

  public boolean isReadableViaFieldAccessBy(TypeM accessingClass) {
    return isAccessibleViaFieldAccess() && getFieldAccess().isReadableFor(accessingClass);
  }

  public String getValueFieldName() {
    String typeIdentifier = getTypeIdentifierForFieldName();
    return String.format("value$%s$%s", getPropertyName(), typeIdentifier);
  }

  public String getIsSetFieldName() {
    String typeIdentifier = getTypeIdentifierForFieldName();
    return String.format("isSet$%s$%s", getPropertyName(), typeIdentifier);
  }

  public String getBuilderFieldName() {
    String typeIdentifier = getTypeIdentifierForFieldName();
    return String.format("builder$%s$%s", getPropertyName(), typeIdentifier);
  }

  private String getTypeIdentifierForFieldName() {
    return getPropertyType().getName().replaceAll("\\.", "\\$").replaceAll("\\[\\]", "\\$L");
  }

  public String getWithMethodName() {
    return String.format("with%s", fcUpperCase(getPropertyName()));
  }

  private String fcUpperCase(String text) {
    if (text == null) {
      return null;
    }
    return text.substring(0, 1).toUpperCase().concat(text.substring(1));
  }

  public WriteAccess getPreferredWriteAccessFor(TypeM accessingClass) {
    if (isWritableViaConstructor()) {
      return getConstructorParameter();
    }
    if (isWritableViaFactoryMethod()) {
      return getFactoryMethodParameter();
    }
    if (isWritableViaSetterMethodBy(accessingClass)) {
      return getSetterMethod();
    }
    if (isWritableViaFieldAccessBy(accessingClass)) {
      return getFieldAccess();
    }
    return null;
  }

  @Override
  public String toString() {
    return "PropertyM [propertyType=" + propertyType + ", propertyName=" + propertyName
        + ", writableViaConstructorParameter=" + writableViaConstructorParameter + ", writableViaSetterMethod="
        + writableViaSetterMethod + ", readableViaGetterMethod=" + readableViaGetterMethod
        + ", writableViaFactoryMethodParameter=" + writableViaFactoryMethodParameter + ", fieldAccess=" + fieldAccess
        + "]";
  }


}
