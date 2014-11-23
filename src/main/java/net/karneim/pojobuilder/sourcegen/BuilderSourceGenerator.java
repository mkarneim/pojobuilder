package net.karneim.pojobuilder.sourcegen;

import static javax.lang.model.element.Modifier.ABSTRACT;
import static javax.lang.model.element.Modifier.PROTECTED;
import static javax.lang.model.element.Modifier.PUBLIC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import javax.annotation.Generated;
import javax.lang.model.element.Modifier;

import net.karneim.pojobuilder.model.*;
import net.karneim.pojobuilder.model.WriteAccess.Type;

import com.squareup.javawriter.JavaWriter;

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

  public void generateSource(BuilderM builder) throws IOException {
    checkNotNull(builder.getPojoType(), "builder.getPojoType() must not be null");
    checkNotNull(builder.getType(), "builder.getBuilderType() must not be null");
    checkNotNull(builder.getProperties(), "builder.getProperties() must not be null");
    generateSource(builder.getType(), builder.isAbstract(), builder.getSelfType(),
        builder.getBaseType(), builder.getInterfaceType(), builder.hasBuilderProperties(),
        builder.getPojoType(), builder.getProperties(), builder.getBuildMethod(),
        builder.getFactoryMethod(), builder.getCopyMethod(), builder.getValidator(),
        builder.getOptionalType(), builder.getStaticFactoryMethod());
  }

  private void checkNotNull(Object obj, String errorMessage) {
    if (obj == null) {
      throw new NullPointerException(errorMessage);
    }
  }

  private void generateSource(TypeM builderType, boolean isAbstract, TypeM selfType,
                              TypeM baseType, TypeM interfaceType, boolean hasBuilderProperties, TypeM pojoType,
                              PropertyListM properties, BuildMethodM buildMethod, FactoryMethodM factoryMethod,
                              CopyMethodM copyMethodM, ValidatorM validator, TypeM optionalType, StaticFactoryMethodM staticFactoryMethod) throws IOException {
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
      baseclass = baseType.getGenericTypeDeclaration();
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
      interfaces = new String[] {interfaceType.getGenericTypeDeclaration(), "Cloneable"};
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
        .beginType(builderType.getGenericType(), "class", builderTypeModifier, baseclass, interfaces)
        .emitField(selfType.getGenericTypeDeclaration(), "self", EnumSet.of(PROTECTED));

    if ( validator != null) {
      emitValidatorField(validator);
    }
    
    for (PropertyM prop : properties) {
      emitPropertyFields(prop, interfaceType, hasBuilderProperties);
    }

    if (staticFactoryMethod != null) {
      emitStaticFactoryMethod(selfType, staticFactoryMethod);
    }

    emitConstructor(builderType, selfType);

    for (PropertyM prop : properties) {
      emitWithMethod(builderType, selfType, pojoType, prop);
      if ( optionalType!=null ) {
        emitWithOptionalMethod(builderType, selfType, pojoType, prop, optionalType);
      }
      if ( interfaceType != null && hasBuilderProperties) {
        emitWithMethodUsingBuilderInterface(builderType, selfType, interfaceType, pojoType, prop);
      }
    }
    emitCloneMethod(selfType);
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
    String validatorTypeDeclaration =
        writer.compressType(validator.getType().getGenericTypeDeclaration());
    String initialization = String.format("new %s()", validatorTypeDeclaration);
    writer.emitField(validatorTypeDeclaration, validator.getFieldName(), EnumSet.of(PROTECTED),
        initialization);
  }

  private void emitStaticFactoryMethod( TypeM selfType, StaticFactoryMethodM method ) throws IOException {
    String builderTypeDeclaration = writer.compressType(selfType.getGenericTypeDeclaration());
    String classname = writer.compressType(selfType.getName());

    // @formatter:off
    writer
        .emitEmptyLine()
        .emitJavadoc(
            "Factory Method to construct a %s\n@return a new %s", classname,classname
        )
        .beginMethod(builderTypeDeclaration, method.getName(), method.getModifiers())
          .emitStatement("return new %s()", classname)
        .endMethod();
    // @formatter:on
  }

  private void emitCopyMethod(TypeM builderType, TypeM selfType, TypeM pojoType,
      PropertyListM properties, CopyMethodM copyMethodM) throws IOException {
    properties = new PropertyListM(properties);
    String selfTypeDeclaration = writer.compressType(selfType.getGenericTypeDeclaration());
    String pojoTypeDeclaration = writer.compressType(pojoType.getGenericTypeDeclaration());
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
    String pojoTypeDeclaration = writer.compressType(pojoType.getGenericTypeDeclaration());
    String pojoClassname = writer.compressType(pojoType.getName());

    // @formatter:off
    writer
      .emitEmptyLine()    
      .emitJavadoc(
         "Creates a new {@link %s} based on this builder's settings.\n\n" 
        +"@return the created %s"
        , pojoClassname, pojoClassname);
    if ( buildMethod.isOverrides()) {
      writer
        .emitAnnotation(Override.class);
    }
    writer
      .beginMethod(pojoTypeDeclaration, "build", EnumSet.of(PUBLIC))
        .beginControlFlow("try");
    
    if ( !hasBuilderProperties) {
      if ( factoryMethod == null) {
        String arguments = properties.filterOutPropertiesWritableViaConstructorParameter(builderType).toArgumentString();
        writer
            .emitStatement("%s result = new %s(%s)", pojoTypeDeclaration, pojoTypeDeclaration, arguments);
      } else {
        String arguments = properties.filterOutPropertiesWritableViaFactoryMethodParameter(builderType).toArgumentString();
        String factoryClass = writer.compressType(factoryMethod.getDeclaringClass().getName());
        writer
            .emitStatement("%s result = %s.%s(%s)", pojoTypeDeclaration, factoryClass, factoryMethod.getName(), arguments);
      }
    } else {
      if ( factoryMethod == null) {
        ArgumentListM constructorArguments = properties.filterOutPropertiesWritableViaConstructorParameter(builderType);
        StringBuilder arguments = new StringBuilder();
        for (PropertyM prop : constructorArguments.sortByPosition().getPropertyList()) {
          writer
            .emitStatement("%s _%s = !%s && %s!=null?%s.build():%s",
                writer.compressType(prop.getPropertyType().getGenericTypeDeclaration()),
                prop.getConstructorParameter().getName(),
                prop.getIsSetFieldName(),
                prop.getBuilderFieldName(),
                prop.getBuilderFieldName(),
                prop.getValueFieldName()
              );
          if ( arguments.length()>0) {
            arguments.append(", ");
          }
          arguments.append(String.format("_%s", prop.getConstructorParameter().getName()));
        }
        writer
            .emitStatement("%s result = new %s(%s)", pojoTypeDeclaration, pojoTypeDeclaration, arguments.toString());
      } else {
        ArgumentListM constructorArguments = properties.filterOutPropertiesWritableViaFactoryMethodParameter(builderType);
        StringBuilder arguments = new StringBuilder();
        for (PropertyM prop : constructorArguments.sortByPosition().getPropertyList()) {
          writer
            .emitStatement("%s _%s = !%s && %s!=null?%s.build():%s",
                writer.compressType(prop.getPropertyType().getGenericType()),
                prop.getFactoryMethodParameter().getName(),
                prop.getIsSetFieldName(),
                prop.getBuilderFieldName(),
                prop.getBuilderFieldName(),
                prop.getValueFieldName()
              );
          if ( arguments.length()>0) {
            arguments.append(", ");
          }
          arguments.append(String.format("_%s", prop.getFactoryMethodParameter().getName()));
        }
        String factoryClass = writer.compressType(factoryMethod.getDeclaringClass().getName());
        writer
            .emitStatement("%s result = %s.%s(%s)", pojoTypeDeclaration, factoryClass, factoryMethod.getName(), arguments.toString());
      }
    }

    PropertyListM setterProperties = properties.filterOutPropertiesWritableBy(Type.SETTER, builderType);
    for( PropertyM prop : setterProperties) {
      writer
          .beginControlFlow("if (%s)", prop.getIsSetFieldName())
            .emitStatement("result.%s(%s)", prop.getSetterMethod().getName(), prop.getValueFieldName());
      if ( hasBuilderProperties) {
        writer
          .nextControlFlow("else if (%s!=null)", prop.getBuilderFieldName())
            .emitStatement("result.%s(%s.build())", prop.getSetterMethod().getName(), prop.getBuilderFieldName());
      }
      writer
          .endControlFlow();
    }

    PropertyListM writableProperties = properties.filterOutPropertiesWritableBy(Type.FIELD, builderType);
    for( PropertyM prop : writableProperties) {
      writer
          .beginControlFlow("if (%s)", prop.getIsSetFieldName())
            .emitStatement("result.%s = %s", prop.getPropertyName(), prop.getValueFieldName());
      if ( hasBuilderProperties) {
        writer
          .nextControlFlow("else if (%s!=null)", prop.getBuilderFieldName())
            .emitStatement("result.%s = %s.build()", prop.getPropertyName(), prop.getBuilderFieldName());
      }
      writer
          .endControlFlow();
    }
    //TODO inform user about any properties leftover 
    
    if (validator != null) {
    	writer.emitStatement("%s.%s(result)", validator.getFieldName(), validator.getMethodName());
    }
    writer
          .emitStatement("return result")
        .nextControlFlow("catch (RuntimeException ex)")
          .emitStatement("throw ex")
        .nextControlFlow("catch (Exception ex)")
          .emitStatement("throw new java.lang.reflect.UndeclaredThrowableException(ex)")
        .endControlFlow()
      .endMethod();
    // @formatter:on
  }

  private void emitWithMethodUsingBuilderInterface(TypeM builderType, TypeM selfType,
      TypeM interfaceType, TypeM pojoType, PropertyM prop) throws IOException {
    String builderFieldName = prop.getBuilderFieldName();
    String isSetFieldName = prop.getIsSetFieldName();
    String withMethodName = prop.getWithMethodName();
    String pojoTypeStr = writer.compressType(pojoType.getName());
    String parameterTypeStr =
        prop.getParameterizedBuilderInterfaceType(interfaceType).getGenericTypeDeclaration();

    // @formatter:off
    writer
      .emitEmptyLine()    
      .emitJavadoc(
          "Sets the default builder for the {@link %s#%s} property.\n\n"
        + "@param builder the default builder\n"
        + "@return this builder"
        , pojoTypeStr, prop.getPropertyName())
      .beginMethod(selfType.getGenericTypeDeclaration(), withMethodName, EnumSet.of(PUBLIC), parameterTypeStr, "builder")
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
    if (prop.getPropertyType().isArrayType()
        && prop.getPreferredWriteAccessFor(builderType).isVarArgs()) {
      ArrayTypeM arrayType = (ArrayTypeM) prop.getPropertyType();
      // TODO replace this when JavaWriter supports varargs
      // parameterTypeStr = arrayType.getGenericTypeDeclarationAsVarArgs();
      String paramTypeStr = arrayType.getGenericTypeDeclaration();
      parameterTypeStr = writer.compressType(paramTypeStr);
      parameterTypeStr = parameterTypeStr.substring(0, parameterTypeStr.length() - 2).concat("...");
    } else {
      parameterTypeStr = prop.getPropertyType().getGenericTypeDeclaration();
    }
    // @formatter:off
    writer
      .emitEmptyLine()    
      .emitJavadoc(
          "Sets the default value for the {@link %s#%s} property.\n\n"
        + "@param value the default value\n"
        + "@return this builder"
        , pojoTypeStr, prop.getPropertyName())
      .beginMethod(selfType.getGenericTypeDeclaration(), withMethodName, EnumSet.of(PUBLIC), parameterTypeStr, "value")
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
    String optionalParameterTypeStr = optionalParameterType.getGenericTypeDeclaration();
    optionalParameterTypeStr = writer.compressType(optionalParameterTypeStr);

    // @formatter:off
    writer
        .emitEmptyLine()
        .emitJavadoc(
            "Optionally sets the default value for the {@link %s#%s} property.\n\n"
                + "@param value the default value\n"
                + "@return this builder"
            , pojoTypeStr, prop.getPropertyName())
        .beginMethod(selfType.getGenericTypeDeclaration(), withMethodName, EnumSet.of(PUBLIC), optionalParameterTypeStr, "optionalValue")
        .emitStatement("return optionalValue.isPresent()?%s(optionalValue.get()):self", withMethodName)
        .endMethod();
    // @formatter:on
  }

  private void emitConstructor(TypeM builderType, TypeM selfType) throws IOException {
    String selfTypeStr = writer.compressType(selfType.getGenericTypeDeclaration());
    String builderTypeName = writer.compressType(builderType.getName());
    // @formatter:off
    writer
      .emitEmptyLine()
      .emitJavadoc("Creates a new {@link %s}.", builderTypeName).beginConstructor(EnumSet.of(PUBLIC))
      .emitStatement("self = (%s)this", selfTypeStr).endConstructor();
    // @formatter:on
  }

  private void emitPropertyFields(PropertyM prop, TypeM interfaceType, boolean hasBuilderProperties)
      throws IOException {
    String valueFieldName = prop.getValueFieldName();
    String isSetFieldName = prop.getIsSetFieldName();
    // @formatter:off
    writer
      .emitField(prop.getPropertyType().getGenericTypeDeclaration(), valueFieldName, EnumSet.of(PROTECTED));  
    writer    
      .emitField("boolean", isSetFieldName, EnumSet.of(PROTECTED));
    if ( interfaceType != null && hasBuilderProperties) {
      writer
        .emitField(prop.getParameterizedBuilderInterfaceType(interfaceType).getGenericTypeDeclaration(), prop.getBuilderFieldName(), EnumSet.of(PROTECTED));
    }
    // @formatter:on
  }

  private void emitButMethod(TypeM selfType) throws IOException {
    String builderTypeStr = writer.compressType(selfType.getGenericTypeDeclaration());
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

  private void emitCloneMethod(TypeM selfType) throws IOException {
    String builderTypeStr = writer.compressType(selfType.getGenericTypeDeclaration());
    // @formatter:off
    writer
      .emitEmptyLine()
      .emitJavadoc(
          "Returns a clone of this builder.\n\n" 
        + "@return the clone")
      .emitAnnotation(Override.class)
      .beginMethod("Object", "clone", EnumSet.of(PUBLIC))
        .beginControlFlow("try");
    if ( selfType.isGeneric()) {
      writer
          .emitAnnotation(SuppressWarnings.class, JavaWriter.stringLiteral("unchecked"));
    }
    writer
          .emitStatement("%s result = (%s)super.clone()", builderTypeStr, builderTypeStr)
          .emitStatement("result.self = result")
          .emitStatement("return result")
        .nextControlFlow("catch (CloneNotSupportedException e)")
          .emitStatement("throw new InternalError(e.getMessage())")
        .endControlFlow()
      .endMethod();
    // @formatter:on
  }

}
