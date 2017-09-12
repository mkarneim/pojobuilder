package net.karneim.pojobuilder.sourcegen;

import static javax.lang.model.element.Modifier.ABSTRACT;
import static javax.lang.model.element.Modifier.PROTECTED;
import static javax.lang.model.element.Modifier.PUBLIC;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import javax.annotation.Generated;
import javax.lang.model.element.Modifier;

import com.google.common.base.Defaults;
import com.squareup.javawriter.JavaWriter;

import net.karneim.pojobuilder.model.ArgumentListM;
import net.karneim.pojobuilder.model.ArrayTypeM;
import net.karneim.pojobuilder.model.BuildMethodM;
import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.CloneMethodM;
import net.karneim.pojobuilder.model.CopyMethodM;
import net.karneim.pojobuilder.model.FactoryMethodM;
import net.karneim.pojobuilder.model.ImportTypesM;
import net.karneim.pojobuilder.model.PrimitiveTypeM;
import net.karneim.pojobuilder.model.PropertyListM;
import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.model.StaticFactoryMethodM;
import net.karneim.pojobuilder.model.TypeM;
import net.karneim.pojobuilder.model.ValidatorM;
import net.karneim.pojobuilder.model.WriteAccess.Type;

public class BuilderSourceGenerator {

  private final JavaWriter writer;
  private final List<String> warnings = new ArrayList<String>();

  public BuilderSourceGenerator(JavaWriter writer) {
    this.writer = writer;
  }

  public List<String> getWarnings() {
    return warnings;
  }

  private void addWarning(String messageFormat, Object... args) {
    warnings.add(String.format(messageFormat, args));
  }

  public void generateSource(BuilderM builder) throws IOException, ClassNotFoundException {
    checkNotNull(builder.getPojoType(), "builder.getPojoType() must not be null");
    checkNotNull(builder.getType(), "builder.getBuilderType() must not be null");
    checkNotNull(builder.getProperties(), "builder.getProperties() must not be null");
    generateSource(builder.getType(), builder.isAbstract(), builder.getSelfType(), builder.getBaseType(),
        builder.getInterfaceType(), builder.hasBuilderProperties(), builder.getPojoType(), builder.getProperties(),
        builder.getBuildMethod(), builder.getFactoryMethod(), builder.getCopyMethod(), builder.getValidator(),
        builder.getOptionalType(), builder.getStaticFactoryMethod(), builder.getCloneMethod());
  }

  private void checkNotNull(Object obj, String errorMessage) {
    if (obj == null) {
      throw new NullPointerException(errorMessage);
    }
  }

  private void generateSource(TypeM builderType, boolean isAbstract, TypeM selfType, TypeM baseType,
      TypeM interfaceType, boolean hasBuilderProperties, TypeM pojoType, PropertyListM properties,
      BuildMethodM buildMethod, FactoryMethodM factoryMethod, CopyMethodM copyMethodM, ValidatorM validator,
      TypeM optionalType, StaticFactoryMethodM staticFactoryMethod, CloneMethodM cloneMethod)
      throws IOException, ClassNotFoundException {
    properties = new PropertyListM(properties);
    properties.filterOutNonWritableProperties(builderType);

    ImportTypesM importTypes = pojoType.addToImportTypes(new ImportTypesM());
    if (factoryMethod != null) {
      factoryMethod.getDeclaringClass().addToImportTypes(importTypes);
    }
    properties.getTypes().addToImportTypes(importTypes);
    importTypes.add(Generated.class);

    if (optionalType != null) {
      optionalType.addToImportTypes(importTypes);
    }

    String baseclass;
    if (baseType == null || baseType.getName().equals("java.lang.Object")) {
      baseclass = null;
    } else {
      // baseclass = baseType.getName();
      baseclass = baseType.getGenericType();
      baseType.addToImportTypes(importTypes);
    }


    EnumSet<Modifier> builderTypeModifier;
    if (isAbstract) {
      builderTypeModifier = EnumSet.of(PUBLIC, ABSTRACT);
    } else {
      builderTypeModifier = EnumSet.of(PUBLIC);
    }
    // @formatter:off
    String[] interfaces;
    if ( interfaceType == null) {
      interfaces = new String[] {"Cloneable"};
    } else {
      interfaces = new String[] {interfaceType.getGenericType(), "Cloneable"};
      interfaceType.addToImportTypes(importTypes);
    }
    if ( validator != null) {
      validator.getType().addToImportTypes(importTypes);
    }

    importTypes.removePackage(builderType.getPackageName());
    importTypes.removePackage("java.lang");

    writer
        .emitPackage(builderType.getPackageName())
        .emitImports(importTypes.getSortedDistinctClassnames())
        .emitEmptyLine()
        .emitAnnotation(Generated.class, JavaWriter.stringLiteral("PojoBuilder"))
        .beginType(builderType.getGenericTypeDefinition(), "class", builderTypeModifier, baseclass, interfaces)
        .emitField(selfType.getGenericType(), "self", EnumSet.of(PROTECTED));

    if ( validator != null) {
      emitValidatorField(validator);
    }

    for (PropertyM prop : properties) {
      emitPropertyFields(prop, interfaceType, hasBuilderProperties, optionalType);
    }

    if (staticFactoryMethod != null) {
      emitStaticFactoryMethod(selfType, staticFactoryMethod, writer);
    }

    emitConstructor(builderType, selfType);

    for (PropertyM prop : properties) {
      emitWithMethod(builderType, selfType, pojoType, prop, optionalType);
      if (optionalType != null) {
        emitWithOptionalMethod(builderType, selfType, pojoType, prop, optionalType);
      }
      if (interfaceType != null && hasBuilderProperties) {
        emitWithMethodUsingBuilderInterface(builderType, selfType, interfaceType, pojoType, prop, optionalType);
      }
    }
    emitCloneMethod(selfType, cloneMethod);
    emitButMethod(selfType);
    if ( copyMethodM != null) {
      if ( properties.hasPropertiesReadablyBy(builderType)) {
        emitCopyMethod(builderType, selfType, pojoType, properties, copyMethodM);
      } else {
        addWarning("[PojoBuilder] Skipping the generation of %s method because none of the writable properties are readable!", copyMethodM.getName());
      }
    }
    emitBuildMethod(builderType, pojoType, interfaceType, hasBuilderProperties, optionalType, properties, factoryMethod, buildMethod, validator);
    writer.endType();
    // @formatter:on
  }

  private void emitValidatorField(ValidatorM validator) throws IOException {
    String validatorTypeDeclaration = writer.compressType(validator.getType().getGenericType());
    String initialization = String.format("new %s()", validatorTypeDeclaration);
    writer.emitField(validatorTypeDeclaration, validator.getFieldName(), EnumSet.of(PROTECTED), initialization);
  }

  static void emitStaticFactoryMethod(TypeM selfType, StaticFactoryMethodM method, JavaWriter writer)
      throws IOException {
    String builderTypeDeclaration = writer.compressType(selfType.getGenericType());
    String returnTypeDecl;
    if (selfType.isGeneric()) {
      String typeParameters = "<" + writer.compressType(selfType.getTypeParameters().toParameterString()) + ">";
      returnTypeDecl = typeParameters + " " + builderTypeDeclaration;
    } else {
      returnTypeDecl = builderTypeDeclaration;
    }
    // @formatter:off
    writer
        .emitEmptyLine()
        .emitJavadoc(
            "Factory Method to construct a %s\n\n@return a new %s", builderTypeDeclaration, builderTypeDeclaration
        )
        .beginMethod(returnTypeDecl, method.getName(), method.getModifiers())
          .emitStatement("return new %s()", builderTypeDeclaration)
        .endMethod();
    // @formatter:on
  }

  private void emitCopyMethod(TypeM builderType, TypeM selfType, TypeM pojoType, PropertyListM properties,
      CopyMethodM copyMethodM) throws IOException {
    properties = new PropertyListM(properties);
    String selfTypeDeclaration = writer.compressType(selfType.getGenericType());
    String pojoTypeDeclaration = writer.compressType(pojoType.getGenericType());
    // @formatter:off
    writer
      .emitEmptyLine()
      .emitJavadoc(
         "Copies the values from the given pojo into this builder.\n\n"
        +"@param pojo\n"
        +"@return this builder")
      .beginMethod(selfTypeDeclaration, copyMethodM.getName(), EnumSet.of(PUBLIC), pojoTypeDeclaration, "pojo");

    PropertyListM getterProperties = properties.filterOutPropertiesReadableViaGetterCall(builderType);
    for( PropertyM prop : getterProperties) {
      String withMethodName = prop.getWithMethodName();
      writer
      .emitStatement("%s(pojo.%s())", withMethodName, prop.getGetterMethod().getName());
    }

    PropertyListM readableFieldProperties = properties.filterOutPropertiesReadableViaFieldAccess(builderType);
    for( PropertyM prop : readableFieldProperties) {
      String withMethodName = prop.getWithMethodName();
      writer
      .emitStatement("%s(pojo.%s)", withMethodName, prop.getPropertyName());
    }

    writer
      .emitStatement("return self");
    writer
      .endMethod();
    // @formatter:on
  }

  private void emitBuildMethod(TypeM builderType, TypeM pojoType, TypeM interfaceType, boolean hasBuilderProperties,
      TypeM optionalType, PropertyListM properties, FactoryMethodM factoryMethod, BuildMethodM buildMethod,
      ValidatorM validator) throws IOException {
    properties = new PropertyListM(properties);
    String pojoTypeDeclaration = writer.compressType(pojoType.getGenericType());
    String pojoClassname = writer.compressType(pojoType.getName());

    writer.emitEmptyLine().emitJavadoc(
        "Creates a new {@link %s} based on this builder's settings.\n\n" + "@return the created %s", pojoClassname,
        pojoClassname);
    if (buildMethod.isOverrides()) {
      writer.emitAnnotation(Override.class);
    }
    writer.beginMethod(pojoTypeDeclaration, buildMethod.getName(), EnumSet.of(PUBLIC)).beginControlFlow("try");

    if (!hasBuilderProperties && optionalType == null) {
      if (factoryMethod == null) {
        String arguments =
            properties.filterOutPropertiesWritableViaConstructorParameter(builderType).toArgumentString();
        writer.emitStatement("%s result = new %s(%s)", pojoTypeDeclaration, pojoTypeDeclaration, arguments);
      } else {
        String arguments =
            properties.filterOutPropertiesWritableViaFactoryMethodParameter(builderType).toArgumentString();
        String factoryClass = writer.compressType(factoryMethod.getDeclaringClass().getName());
        writer.emitStatement("%s result = %s.%s(%s)", pojoTypeDeclaration, factoryClass, factoryMethod.getName(),
            arguments);
      }
    } else {
      if (factoryMethod == null) {
        ArgumentListM constructorArguments = properties.filterOutPropertiesWritableViaConstructorParameter(builderType);
        StringBuilder arguments = new StringBuilder();
        for (PropertyM prop : constructorArguments.sortByPosition().getPropertyList()) {
          String parameterFieldName = "_" + prop.getConstructorParameter().getName();
          emitParameterAssignment(prop, parameterFieldName, buildMethod.getName(), optionalType);
          if (arguments.length() > 0) {
            arguments.append(", ");
          }
          arguments.append(String.format("%s", parameterFieldName));
        }
        writer.emitStatement("%s result = new %s(%s)", pojoTypeDeclaration, pojoTypeDeclaration, arguments.toString());
      } else {
        ArgumentListM factoryMethodArguments =
            properties.filterOutPropertiesWritableViaFactoryMethodParameter(builderType);
        StringBuilder arguments = new StringBuilder();
        for (PropertyM prop : factoryMethodArguments.sortByPosition().getPropertyList()) {
          String parameterFieldName = "_" + prop.getFactoryMethodParameter().getName();
          emitParameterAssignment(prop, parameterFieldName, buildMethod.getName(), optionalType);
          if (arguments.length() > 0) {
            arguments.append(", ");
          }
          arguments.append(String.format("%s", parameterFieldName));
        }
        String factoryClass = writer.compressType(factoryMethod.getDeclaringClass().getName());
        writer.emitStatement("%s result = %s.%s(%s)", pojoTypeDeclaration, factoryClass, factoryMethod.getName(),
            arguments.toString());
      }
    }

    PropertyListM setterProperties = properties.filterOutPropertiesWritableBy(Type.SETTER, builderType);
    for (PropertyM prop : setterProperties) {
      String setTemplate = "result." + prop.getSetterMethod().getName() + "(%s)";
      emitBuildPropertyStatement(hasBuilderProperties, buildMethod, optionalType, prop, setTemplate);
    }

    PropertyListM writableProperties = properties.filterOutPropertiesWritableBy(Type.FIELD, builderType);
    for (PropertyM prop : writableProperties) {
      String setTemplate = "result." + prop.getPropertyName() + " = %s";
      emitBuildPropertyStatement(hasBuilderProperties, buildMethod, optionalType, prop, setTemplate);
    }
    // TODO inform user about any properties leftover
    if (validator != null) {
      writer.emitStatement("%s.%s(result)", validator.getFieldName(), validator.getMethodName());
    }
    writer.emitStatement("return result").nextControlFlow("catch (RuntimeException ex)").emitStatement("throw ex")
        .nextControlFlow("catch (Exception ex)")
        .emitStatement("throw new java.lang.reflect.UndeclaredThrowableException(ex)").endControlFlow().endMethod();
  }

  private void emitBuildPropertyStatement(boolean hasBuilderProperties, BuildMethodM buildMethod, TypeM optionalType,
      PropertyM prop, String setTemplate) throws IOException {
    if (optionalType == null) { // ohne Optionals
      writer.beginControlFlow("if (%s)", prop.getIsSetFieldName());
      writer.emitStatement(setTemplate, prop.getValueFieldName());
    } else if (prop.getPropertyType().isPrimitive()) { // Primitive (mit Optionals)
      writer.beginControlFlow("if (%s.isPresent())", prop.getValueFieldName());
      writer.emitStatement(setTemplate, prop.getValueFieldName() + ".get()");
    } else if (prop.isOptionalProperty(optionalType)) { // Optional (mit Optionals)
      writer.beginControlFlow("if (%s == null || %s.isPresent())", prop.getValueFieldName(), prop.getValueFieldName());
      writer.emitStatement(setTemplate, prop.getValueFieldName());
    } else { // Nicht Primitive (mit Optionals)
      writer.beginControlFlow("if (%s == null)", prop.getValueFieldName());
      String type = writer.compressType(prop.getPropertyType().getGenericType());
      writer.emitStatement(setTemplate, "(" + type + ") null");
      writer.nextControlFlow("else if (%s.isPresent())", prop.getValueFieldName());
      writer.emitStatement(setTemplate, prop.getValueFieldName() + ".get()");
    }
    if (hasBuilderProperties) {
      writer.nextControlFlow("else if (%s != null)", prop.getBuilderFieldName());
      writer.emitStatement(setTemplate, prop.getBuilderFieldName() + "." + buildMethod.getName() + "()");
    }
    writer.endControlFlow();
  }

  private void emitParameterAssignment(PropertyM prop, String parameterFieldName, String buildMethodName,
      TypeM optionalType) throws IOException {
    TypeM propertyType = prop.getPropertyType();
    String compressedType = writer.compressType(propertyType.getGenericType());
    String valueField = prop.getValueFieldName();
    if (optionalType == null) {
      writer.emitStatement("%s %s", compressedType, parameterFieldName);
      writer.beginControlFlow("if (!%s && %s != null)", prop.getIsSetFieldName(), prop.getBuilderFieldName())
          .emitStatement("%s = %s.%s()", parameterFieldName, prop.getBuilderFieldName(), buildMethodName);
      writer.nextControlFlow("else").emitStatement("%s = %s", parameterFieldName, valueField);
      writer.endControlFlow();
    } else {
      String defaultValue = "null";
      if (propertyType.isPrimitive()) {
        Class<?> type = ((PrimitiveTypeM) propertyType).getType();
        defaultValue = String.valueOf(Defaults.defaultValue(type));
      }
      writer.emitStatement("%s %s = %s", compressedType, parameterFieldName, defaultValue);
      if (prop.isOptionalProperty(optionalType)) {
        writer.beginControlFlow("if (%s == null || %s.isPresent())", valueField, valueField);
        writer.emitStatement("%s = %s", parameterFieldName, valueField);
      } else {
        writer.beginControlFlow("if (%s != null && %s.isPresent())", valueField, valueField);
        writer.emitStatement("%s = %s.get()", parameterFieldName, valueField);
      }
      writer.endControlFlow();
    }
  }

  private void emitWithMethodUsingBuilderInterface(TypeM builderType, TypeM selfType, TypeM interfaceType,
      TypeM pojoType, PropertyM prop, TypeM optionalType) throws IOException, ClassNotFoundException {
    String withMethodName = prop.getWithMethodName();
    String pojoTypeStr = writer.compressType(pojoType.getName());
    String parameterTypeStr = prop.getParameterizedBuilderInterfaceType(interfaceType).getGenericType();

    writer.emitEmptyLine();
    writer.emitJavadoc(
        "Sets the default builder for the {@link %s#%s} property.\n\n"//
            + "@param builder the default builder\n"//
            + "@return this builder"//
        , pojoTypeStr, prop.getPropertyName());
    writer.beginMethod(selfType.getGenericType(), withMethodName, EnumSet.of(PUBLIC), parameterTypeStr, "builder");
    writer.emitStatement("this.%s = builder", prop.getBuilderFieldName());
    if (optionalType == null) {
      writer.emitStatement("this.%s = false", prop.getIsSetFieldName());
    } else {
      writer.emitStatement("this.%s = %s", prop.getValueFieldName(), getOptionalAbsentCreation(optionalType));
    }
    writer.emitStatement("return self").endMethod();
  }

  private void emitWithMethod(TypeM builderType, TypeM selfType, TypeM pojoType, PropertyM prop, TypeM optionalType)
      throws IOException, ClassNotFoundException {
    String valueFieldName = prop.getValueFieldName();
    String isSetFieldName = prop.getIsSetFieldName();
    String withMethodName = prop.getWithMethodName();
    String pojoTypeStr = writer.compressType(pojoType.getName());
    String parameterTypeStr;
    TypeM propertyType = prop.getBasicPropertyType(optionalType);
    if (propertyType.isArrayType() && prop.getPreferredWriteAccessFor(builderType).isVarArgs()) {
      ArrayTypeM arrayType = (ArrayTypeM) propertyType;
      // TODO replace this when JavaWriter supports varargs
      // parameterTypeStr = arrayType.getGenericTypeDeclarationAsVarArgs();
      String paramTypeStr = arrayType.getGenericType();
      parameterTypeStr = writer.compressType(paramTypeStr);
      parameterTypeStr = parameterTypeStr.substring(0, parameterTypeStr.length() - 2).concat("...");
    } else {
      parameterTypeStr = propertyType.getGenericType();
    }
    writer.emitEmptyLine();
    writer.emitJavadoc(
        "Sets the default value for the {@link %s#%s} property.\n\n"//
            + "@param value the default value\n"//
            + "@return this builder"//
        , pojoTypeStr, prop.getPropertyName());
    writer.beginMethod(selfType.getGenericType(), withMethodName, EnumSet.of(PUBLIC), parameterTypeStr, "value");
    if (optionalType == null) {
      writer.emitStatement("this.%s = value", valueFieldName);
      writer.emitStatement("this.%s = true", isSetFieldName);
    } else if (propertyType.isPrimitive()) {
      writer.emitStatement("this.%s = %s", valueFieldName, getOptionalPresentCreation(optionalType, "value"));
    } else {
      // @formatter:off
      writer
        .beginControlFlow("if (%s)", "value == null")
          .emitStatement("this.%s = null", valueFieldName)
        .nextControlFlow("else")
          .emitStatement("this.%s = %s", valueFieldName, getOptionalPresentCreation(optionalType, "value"))
        .endControlFlow();
      // @formatter:on
    }
    writer.emitStatement("return self").endMethod();
  }

  private void emitWithOptionalMethod(TypeM builderType, TypeM selfType, TypeM pojoType, PropertyM prop,
      TypeM optionalType) throws IOException {
    String withMethodName = prop.getWithMethodName();
    String pojoTypeStr = writer.compressType(pojoType.getName());
    String optionalParameterTypeStr = prop.getOptionalPropertyType(optionalType).getGenericType();
    optionalParameterTypeStr = writer.compressType(optionalParameterTypeStr);

    writer.emitEmptyLine();
    writer.emitJavadoc(
        "Optionally sets the default value for the {@link %s#%s} property.\n\n"//
            + "@param optionalValue the optional default value\n"//
            + "@return this builder"//
        , pojoTypeStr, prop.getPropertyName());
    writer.beginMethod(selfType.getGenericType(), withMethodName, EnumSet.of(PUBLIC), optionalParameterTypeStr,
        "optionalValue");
    String condition;
    if (prop.getPropertyType().isPrimitive()) {
      condition = "optionalValue.isPresent()";
    } else {
      condition = "optionalValue == null || optionalValue.isPresent()";
    }
    // @formatter:off
    writer
      .beginControlFlow("if (%s)", condition)
        .emitStatement("this.%s = optionalValue", prop.getValueFieldName())
      .endControlFlow();
    // @formatter:on
    writer.emitStatement("return self").endMethod();
  }

  private void emitConstructor(TypeM builderType, TypeM selfType) throws IOException {
    String selfTypeStr = writer.compressType(selfType.getGenericType());
    String builderTypeName = writer.compressType(builderType.getName());
    // @formatter:off
    writer
      .emitEmptyLine()
      .emitJavadoc("Creates a new {@link %s}.", builderTypeName).beginConstructor(EnumSet.of(PUBLIC))
      .emitStatement("self = (%s)this", selfTypeStr).endConstructor();
    // @formatter:on
  }

  private void emitPropertyFields(PropertyM prop, TypeM interfaceType, boolean hasBuilderProperties, TypeM optionalType)
      throws IOException, ClassNotFoundException {
    String valueFieldName = prop.getValueFieldName();
    if (optionalType == null) {
      String isSetFieldName = prop.getIsSetFieldName();
      writer.emitField(prop.getPropertyType().getGenericType(), valueFieldName, EnumSet.of(PROTECTED));
      writer.emitField("boolean", isSetFieldName, EnumSet.of(PROTECTED));
    } else {
      writer.emitField(prop.getOptionalPropertyType(optionalType).getGenericType(), valueFieldName,
          EnumSet.of(PROTECTED), getOptionalAbsentCreation(optionalType));
    }
    if (interfaceType != null && hasBuilderProperties) {
      writer.emitField(prop.getParameterizedBuilderInterfaceType(interfaceType).getGenericType(),
          prop.getBuilderFieldName(), EnumSet.of(PROTECTED));
    }
  }

  private String getOptionalAbsentCreation(TypeM optionalType) throws ClassNotFoundException {
    return optionalType.getSimpleName() + "." + getOptionalAbsentMethodName(optionalType) + "()";
  }

  private String getOptionalAbsentMethodName(TypeM optionalType) throws ClassNotFoundException {
    List<String> absentMethodNames = Arrays.asList("absent", "empty");
    String absentMethodName = null;
    Method[] methods = Class.forName(optionalType.getName()).getMethods();
    for (Method method : methods) {
      String methodName = method.getName();
      if (absentMethodNames.contains(methodName) && method.getParameterTypes().length == 0) {
        absentMethodName = methodName;
        break;
      }
    }
    return absentMethodName;
  }

  private Object getOptionalPresentCreation(TypeM optionalType, String string) throws ClassNotFoundException {
    return optionalType.getSimpleName() + "." + getOptionalPresentMethodName(optionalType) + "(" + string + ")";
  }

  private String getOptionalPresentMethodName(TypeM optionalType) throws ClassNotFoundException {
    List<String> presentMethodNames = Arrays.asList("of");
    String presentMethodName = null;
    Method[] methods = Class.forName(optionalType.getName()).getMethods();
    for (Method method : methods) {
      String methodName = method.getName();
      if (presentMethodNames.contains(methodName) && method.getParameterTypes().length == 1) {
        presentMethodName = methodName;
        break;
      }
    }
    return presentMethodName;
  }

  private void emitButMethod(TypeM selfType) throws IOException {
    String builderTypeStr = writer.compressType(selfType.getGenericType());
    // @formatter:off
    writer
      .emitEmptyLine()
      .emitJavadoc(
          "Returns a clone of this builder.\n\n"
        + "@return the clone");
    if ( selfType.isGeneric()) {
      writer
      .emitAnnotation(SuppressWarnings.class, JavaWriter.stringLiteral("unchecked"));
    }
    writer
      .beginMethod(builderTypeStr, "but", EnumSet.of(PUBLIC))
        .emitStatement("return (%s)clone()", builderTypeStr)
      .endMethod();
    // @formatter:on
  }

  private void emitCloneMethod(TypeM selfType, CloneMethodM cloneMethod) throws IOException {
    String builderTypeStr = writer.compressType(selfType.getGenericType());
    // @formatter:off
    writer
      .emitEmptyLine()
      .emitJavadoc(
          "Returns a clone of this builder.\n\n"
        + "@return the clone")
      .emitAnnotation(Override.class)
      .beginMethod("Object", "clone", EnumSet.of(PUBLIC));
    if ( cloneMethod.shouldCatchCloneNotSupportedException()) {
      writer.beginControlFlow("try");
    }
    if ( selfType.isGeneric()) {
      writer
          .emitAnnotation(SuppressWarnings.class, JavaWriter.stringLiteral("unchecked"));
    }
    writer
          .emitStatement("%s result = (%s)super.clone()", builderTypeStr, builderTypeStr)
          .emitStatement("result.self = result")
          .emitStatement("return result");
    if ( cloneMethod.shouldCatchCloneNotSupportedException()) {
      writer.nextControlFlow("catch (CloneNotSupportedException e)")
        .emitStatement("throw new InternalError(e.getMessage())")
        .endControlFlow();
    }
    writer.endMethod();
    // @formatter:on
  }

}
