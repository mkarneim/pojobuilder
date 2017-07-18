package net.karneim.pojobuilder.sourcegen;

import static javax.lang.model.element.Modifier.ABSTRACT;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PROTECTED;
import static javax.lang.model.element.Modifier.PUBLIC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import javax.annotation.Generated;
import javax.lang.model.element.Modifier;

import com.squareup.javawriter.JavaWriter;
import net.karneim.pojobuilder.model.ArgumentListM;
import net.karneim.pojobuilder.model.ArrayTypeM;
import net.karneim.pojobuilder.model.BuildMethodM;
import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.CloneMethodM;
import net.karneim.pojobuilder.model.CopyMethodM;
import net.karneim.pojobuilder.model.FactoryMethodM;
import net.karneim.pojobuilder.model.ImportTypesM;
import net.karneim.pojobuilder.model.PropertyListM;
import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.model.StaticFactoryMethodM;
import net.karneim.pojobuilder.model.TypeM;
import net.karneim.pojobuilder.model.ValidatorM;
import net.karneim.pojobuilder.model.WriteAccess.Type;

public class BuilderSourceGenerator {

  private JavaWriter writer;
  private List<String> warnings = new ArrayList<String>();

  public BuilderSourceGenerator(JavaWriter writer) {
    this.writer = writer;
  }

  public List<String> getWarnings() {
    return warnings;
  }

  private void addWarning(String messageFormat, Object... args) {
    warnings.add(String.format(messageFormat, args));
  }

  public void generateSource(BuilderM builder) throws IOException {
    checkNotNull(builder.getPojoType(), "builder.getPojoType() must not be null");
    checkNotNull(builder.getType(), "builder.getBuilderType() must not be null");
    checkNotNull(builder.getProperties(), "builder.getProperties() must not be null");
    generateSource(builder.getType(), builder.isAbstract(), builder.getSelfType(),
        builder.getBaseType(), builder.getInterfaceType(), builder.hasBuilderProperties(),
        builder.getPojoType(), builder.getProperties(), builder.getBuildMethod(),
        builder.getFactoryMethod(), builder.getCopyMethod(), builder.getValidator(),
        builder.getOptionalType(), builder.getStaticFactoryMethod(), builder.hasPublicConstructor(), builder.getCloneMethod());
  }

  private void checkNotNull(Object obj, String errorMessage) {
    if (obj == null) {
      throw new NullPointerException(errorMessage);
    }
  }

  private void generateSource(TypeM builderType, boolean isAbstract, TypeM selfType, TypeM baseType,
      TypeM interfaceType, boolean hasBuilderProperties, TypeM pojoType, PropertyListM properties,
      BuildMethodM buildMethod, FactoryMethodM factoryMethod, CopyMethodM copyMethodM,
      ValidatorM validator, TypeM optionalType, StaticFactoryMethodM staticFactoryMethod,
      boolean hasPublicConstructor, CloneMethodM cloneMethod) throws IOException {
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
      emitPropertyFields(prop, interfaceType, hasBuilderProperties);
    }

    if (staticFactoryMethod != null) {
      emitStaticFactoryMethod(selfType, staticFactoryMethod, writer);
    }

    emitConstructor(builderType, selfType, hasPublicConstructor ? PUBLIC : PRIVATE);

    for (PropertyM prop : properties) {
      emitWithMethod(builderType, selfType, pojoType, prop);
      if ( optionalType!=null ) {
        emitWithOptionalMethod(builderType, selfType, pojoType, prop, optionalType);
      }
      if ( interfaceType != null && hasBuilderProperties) {
        emitWithMethodUsingBuilderInterface(builderType, selfType, interfaceType, pojoType, prop);
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
    emitBuildMethod(builderType, pojoType, interfaceType, hasBuilderProperties, properties, factoryMethod, buildMethod, validator);
    writer.endType();
    // @formatter:on
  }

  private void emitValidatorField(ValidatorM validator) throws IOException {
    String validatorTypeDeclaration = writer.compressType(validator.getType().getGenericType());
    String initialization = String.format("new %s()", validatorTypeDeclaration);
    writer.emitField(validatorTypeDeclaration, validator.getFieldName(), EnumSet.of(PROTECTED),
        initialization);
  }

  static void emitStaticFactoryMethod(TypeM selfType, StaticFactoryMethodM method,
      JavaWriter writer) throws IOException {
    String builderTypeDeclaration = writer.compressType(selfType.getGenericType());
    String returnTypeDecl;
    if (selfType.isGeneric()) {
      String typeParameters =
          "<" + writer.compressType(selfType.getTypeParameters().toParameterString()) + ">";
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

  private void emitCopyMethod(TypeM builderType, TypeM selfType, TypeM pojoType,
      PropertyListM properties, CopyMethodM copyMethodM) throws IOException {
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

  private void emitBuildMethod(TypeM builderType, TypeM pojoType, TypeM interfaceType,
      boolean hasBuilderProperties, PropertyListM properties, FactoryMethodM factoryMethod,
      BuildMethodM buildMethod, ValidatorM validator) throws IOException {
    properties = new PropertyListM(properties);
    String pojoTypeDeclaration = writer.compressType(pojoType.getGenericType());
    String pojoClassname = writer.compressType(pojoType.getName());

    writer.emitEmptyLine().emitJavadoc(
        "Creates a new {@link %s} based on this builder's settings.\n\n" + "@return the created %s",
        pojoClassname, pojoClassname);
    if (buildMethod.isOverrides()) {
      writer.emitAnnotation(Override.class);
    }
    writer.beginMethod(pojoTypeDeclaration, buildMethod.getName(), EnumSet.of(PUBLIC))
        .beginControlFlow("try");

    if (!hasBuilderProperties) {
      if (factoryMethod == null) {
        String arguments = properties
            .filterOutPropertiesWritableViaConstructorParameter(builderType).toArgumentString();
        writer.emitStatement("%s result = new %s(%s)", pojoTypeDeclaration, pojoTypeDeclaration,
            arguments);
      } else {
        String arguments = properties
            .filterOutPropertiesWritableViaFactoryMethodParameter(builderType).toArgumentString();
        String factoryClass = writer.compressType(factoryMethod.getDeclaringClass().getName());
        writer.emitStatement("%s result = %s.%s(%s)", pojoTypeDeclaration, factoryClass,
            factoryMethod.getName(), arguments);
      }
    } else {
      if (factoryMethod == null) {
        ArgumentListM constructorArguments =
            properties.filterOutPropertiesWritableViaConstructorParameter(builderType);
        StringBuilder arguments = new StringBuilder();
        for (PropertyM prop : constructorArguments.sortByPosition().getPropertyList()) {
          String parameterFieldName = "_" + prop.getConstructorParameter().getName();
          emitParameterAssignmentWithBuilderProperty(prop, parameterFieldName,
              buildMethod.getName());
          if (arguments.length() > 0) {
            arguments.append(", ");
          }
          arguments.append(String.format("%s", parameterFieldName));
        }
        writer.emitStatement("%s result = new %s(%s)", pojoTypeDeclaration, pojoTypeDeclaration,
            arguments.toString());
      } else {
        ArgumentListM factoryMethodArguments =
            properties.filterOutPropertiesWritableViaFactoryMethodParameter(builderType);
        StringBuilder arguments = new StringBuilder();
        for (PropertyM prop : factoryMethodArguments.sortByPosition().getPropertyList()) {
          String parameterFieldName = "_" + prop.getFactoryMethodParameter().getName();
          emitParameterAssignmentWithBuilderProperty(prop, parameterFieldName,
              buildMethod.getName());
          if (arguments.length() > 0) {
            arguments.append(", ");
          }
          arguments.append(String.format("%s", parameterFieldName));
        }
        String factoryClass = writer.compressType(factoryMethod.getDeclaringClass().getName());
        writer.emitStatement("%s result = %s.%s(%s)", pojoTypeDeclaration, factoryClass,
            factoryMethod.getName(), arguments.toString());
      }
    }

    PropertyListM setterProperties =
        properties.filterOutPropertiesWritableBy(Type.SETTER, builderType);
    for (PropertyM prop : setterProperties) {
      writer.beginControlFlow("if (%s)", prop.getIsSetFieldName()).emitStatement("result.%s(%s)",
          prop.getSetterMethod().getName(), prop.getValueFieldName());
      if (hasBuilderProperties) {
        writer.nextControlFlow("else if (%s!=null)", prop.getBuilderFieldName()).emitStatement(
            "result.%s(%s.%s())", prop.getSetterMethod().getName(), prop.getBuilderFieldName(),
            buildMethod.getName());
      }
      writer.endControlFlow();
    }

    PropertyListM writableProperties =
        properties.filterOutPropertiesWritableBy(Type.FIELD, builderType);
    for (PropertyM prop : writableProperties) {
      writer.beginControlFlow("if (%s)", prop.getIsSetFieldName()).emitStatement("result.%s = %s",
          prop.getPropertyName(), prop.getValueFieldName());
      if (hasBuilderProperties) {
        writer.nextControlFlow("else if (%s!=null)", prop.getBuilderFieldName()).emitStatement(
            "result.%s = %s.%s()", prop.getPropertyName(), prop.getBuilderFieldName(),
            buildMethod.getName());
      }
      writer.endControlFlow();
    }
    // TODO inform user about any properties leftover
    if (validator != null) {
      writer.emitStatement("%s.%s(result)", validator.getFieldName(), validator.getMethodName());
    }
    writer.emitStatement("return result").nextControlFlow("catch (RuntimeException ex)")
        .emitStatement("throw ex").nextControlFlow("catch (Exception ex)")
        .emitStatement("throw new java.lang.reflect.UndeclaredThrowableException(ex)")
        .endControlFlow().endMethod();
  }

  private void emitParameterAssignmentWithBuilderProperty(PropertyM prop, String parameterFieldName,
      String buildMethodName) throws IOException {
    writer.emitStatement("%s %s", writer.compressType(prop.getPropertyType().getGenericType()),
        parameterFieldName);
    writer.beginControlFlow("if (!%s && %s!=null)", prop.getIsSetFieldName(),
        prop.getBuilderFieldName()).emitStatement("%s = %s.%s()", parameterFieldName,
            prop.getBuilderFieldName(), buildMethodName);
    writer.nextControlFlow("else").emitStatement("%s = %s", parameterFieldName,
        prop.getValueFieldName());
    writer.endControlFlow();
  }

  private void emitWithMethodUsingBuilderInterface(TypeM builderType, TypeM selfType,
      TypeM interfaceType, TypeM pojoType, PropertyM prop) throws IOException {
    String builderFieldName = prop.getBuilderFieldName();
    String isSetFieldName = prop.getIsSetFieldName();
    String withMethodName = prop.getWithMethodName();
    String pojoTypeStr = writer.compressType(pojoType.getName());
    String parameterTypeStr =
        prop.getParameterizedBuilderInterfaceType(interfaceType).getGenericType();

    // @formatter:off
    writer
      .emitEmptyLine()
      .emitJavadoc(
          "Sets the default builder for the {@link %s#%s} property.\n\n"
        + "@param builder the default builder\n"
        + "@return this builder"
        , pojoTypeStr, prop.getPropertyName())
      .beginMethod(selfType.getGenericType(), withMethodName, EnumSet.of(PUBLIC), parameterTypeStr, "builder")
        .emitStatement("this.%s = builder", builderFieldName)
        .emitStatement("this.%s = false", isSetFieldName)
        .emitStatement("return self")
      .endMethod();
    // @formatter:on
  }

  private void emitWithMethod(TypeM builderType, TypeM selfType, TypeM pojoType, PropertyM prop)
      throws IOException {
    String valueFieldName = prop.getValueFieldName();
    String isSetFieldName = prop.getIsSetFieldName();
    String withMethodName = prop.getWithMethodName();
    String pojoTypeStr = writer.compressType(pojoType.getName());
    String parameterTypeStr;
    TypeM propertyType = prop.getPropertyType();
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
    // @formatter:off
    writer
      .emitEmptyLine()
      .emitJavadoc(
          "Sets the default value for the {@link %s#%s} property.\n\n"
        + "@param value the default value\n"
        + "@return this builder"
        , pojoTypeStr, prop.getPropertyName())
      .beginMethod(selfType.getGenericType(), withMethodName, EnumSet.of(PUBLIC), parameterTypeStr, "value")
        .emitStatement("this.%s = value", valueFieldName)
        .emitStatement("this.%s = true", isSetFieldName)
        .emitStatement("return self")
      .endMethod();
    // @formatter:on
  }

  private void emitWithOptionalMethod(TypeM builderType, TypeM selfType, TypeM pojoType,
      PropertyM prop, TypeM optionalType) throws IOException {

    TypeM optionalParameterType = prop.getOptionalPropertyType(optionalType);
    if (optionalParameterType == null) {
      return;
    }

    String withMethodName = prop.getWithMethodName();
    String pojoTypeStr = writer.compressType(pojoType.getName());
    String optionalParameterTypeStr = optionalParameterType.getGenericType();
    optionalParameterTypeStr = writer.compressType(optionalParameterTypeStr);

    // @formatter:off
    writer
        .emitEmptyLine()
        .emitJavadoc(
            "Optionally sets the default value for the {@link %s#%s} property.\n\n"
                + "@param optionalValue the optional default value\n"
                + "@return this builder"
            , pojoTypeStr, prop.getPropertyName())
        .beginMethod(selfType.getGenericType(), withMethodName, EnumSet.of(PUBLIC), optionalParameterTypeStr, "optionalValue")
        .emitStatement("return optionalValue.isPresent()?%s(optionalValue.get()):self", withMethodName)
        .endMethod();
    // @formatter:on
  }

  private void emitConstructor(TypeM builderType, TypeM selfType, Modifier modifier) throws IOException {
    String selfTypeStr = writer.compressType(selfType.getGenericType());
    String builderTypeName = writer.compressType(builderType.getName());
    // @formatter:off
    writer
      .emitEmptyLine()
      .emitJavadoc("Creates a new {@link %s}.", builderTypeName).beginConstructor(EnumSet.of(modifier))
      .emitStatement("self = (%s)this", selfTypeStr).endConstructor();
    // @formatter:on
  }

  private void emitPropertyFields(PropertyM prop, TypeM interfaceType, boolean hasBuilderProperties)
      throws IOException {
    String valueFieldName = prop.getValueFieldName();
    String isSetFieldName = prop.getIsSetFieldName();
    // @formatter:off
    writer
      .emitField(prop.getPropertyType().getGenericType(), valueFieldName, EnumSet.of(PROTECTED));
    writer
      .emitField("boolean", isSetFieldName, EnumSet.of(PROTECTED));
    if ( interfaceType != null && hasBuilderProperties) {
      writer
        .emitField(prop.getParameterizedBuilderInterfaceType(interfaceType).getGenericType(), prop.getBuilderFieldName(), EnumSet.of(PROTECTED));
    }
    // @formatter:on
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
