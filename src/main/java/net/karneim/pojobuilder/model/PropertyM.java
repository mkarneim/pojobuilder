package net.karneim.pojobuilder.model;

import java.util.Iterator;
import java.util.List;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.analysis.PropertyPattern;


public class PropertyM {
  private TypeM propertyType;
  private String propertyName;
  private String withMethodName;
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

  public boolean isField() {
    return isAccessibleViaFieldAccess();
  }

  public String getWithMethodName() {
    return withMethodName;
  }

  public PropertyM withMethodNamePattern(String methodNamePattern) {
    if (methodNamePattern.startsWith("*")) {
      withMethodName = methodNamePattern.replace("*", propertyName);
    } else {
      withMethodName = methodNamePattern.replace("*", fcUpperCase(propertyName));
    }
    return this;
  }

  private String fcUpperCase(String text) {
    if (text == null) {
      return null;
    }
    return text.substring(0, 1).toUpperCase().concat(text.substring(1));
  }

  public boolean isOptionalProperty(OptionalM optional) {
    return propertyType.getName().equals(optional.getType().getName());
  }

  /**
   * Returns the basic type of this property. If the property is an optional property in regard to the specified
   * optionalType, then the basic type is defined as the generic type parameter of the optional type. Otherwise, the
   * basic type is the property type itself.
   *
   * @param optional the Optional defined in {@link GeneratePojoBuilder#withOptionalProperties()}
   * @return the basic type of this property
   */
  public TypeM getBasicPropertyType(OptionalM optional) {
    if (optional == null || !isOptionalProperty(optional)) {
      return propertyType;
    }
    Iterator<TypeM> typeParameters = propertyType.getTypeParameters().iterator();
    if (typeParameters.hasNext()) {
      return typeParameters.next();
    } else {
      System.out.println("Property is of an optional type without type parameters: " + this);
      return new TypeM(Object.class);
    }
  }

  public TypeM getParameterizedBuilderInterfaceType(TypeM interfaceType, OptionalM optional) {
    TypeM basicType = getBasicPropertyType(optional);
    TypeM typeParam;
    if (basicType.isPrimitive()) {
      typeParam = ((PrimitiveTypeM) basicType).getBoxType();
    } else {
      typeParam = basicType;
    }
    return new TypeM(interfaceType.getPackageName(), interfaceType.getSimpleNames())
        .withTypeParameter(new TypeWildcardM().whichExtends(typeParam));
  }

  /**
   * The {@link TypeM} for an optional property with the property type as it's type parameter or the property type if
   * that is already an optional type.
   *
   * @param optional the Optional defined in {@link GeneratePojoBuilder#withOptionalProperties()}
   *
   * @return a {@link TypeM} for an optional property with the property type as it's type parameter or the property type
   *         if that is already an optional type
   */
  public TypeM getOptionalPropertyType(OptionalM optional) {
    if (isOptionalProperty(optional)) {
      return propertyType;
    }
    TypeM typeParam;
    if (propertyType.isPrimitive()) {
      typeParam = ((PrimitiveTypeM) propertyType).getBoxType();
    } else {
      typeParam = propertyType;
    }
    TypeM optionalType = optional.getType();
    TypeM result = new TypeM(optionalType.getPackageName(), optionalType.getSimpleNames())
        .withTypeParameter(new TypeWildcardM().whichExtends(typeParam));
    return result;
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

  public String getCallTo(BuildMethodM buildMethod) {
    return getBuilderFieldName() + "." + buildMethod.getName() + "()";
  }

  private String getTypeIdentifierForFieldName() {
    return getPropertyType().getName().replaceAll("\\.", "\\$").replaceAll("\\[\\]", "\\$L");
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

  public boolean matchesAnyOf(List<PropertyPattern> list) {
    for (PropertyPattern pattern : list) {
      if (matches(pattern)) {
        return true;
      }
    }
    return false;
  }

  private boolean matches(PropertyPattern pattern) {
    return pattern.contains(this);
  }

  @Override
  public String toString() {
    return "PropertyM [propertyType=" + propertyType + ", propertyName=" + propertyName + ", withMethodName="
        + withMethodName + ", writableViaConstructorParameter=" + writableViaConstructorParameter
        + ", writableViaSetterMethod=" + writableViaSetterMethod + ", readableViaGetterMethod="
        + readableViaGetterMethod + ", writableViaFactoryMethodParameter=" + writableViaFactoryMethodParameter
        + ", fieldAccess=" + fieldAccess + "]";
  }

}
