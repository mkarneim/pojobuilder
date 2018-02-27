package net.karneim.pojobuilder.sourcegen;

import static javax.lang.model.element.Modifier.PUBLIC;

import java.io.IOException;
import java.util.EnumSet;

import javax.lang.model.element.Modifier;

import com.squareup.javawriter.JavaWriter;
import net.karneim.pojobuilder.Visibility;
import net.karneim.pojobuilder.model.ImportTypesM;
import net.karneim.pojobuilder.model.ManualBuilderM;
import net.karneim.pojobuilder.model.StaticFactoryMethodM;
import net.karneim.pojobuilder.model.TypeM;

public class ManualBuilderSourceGenerator {

  private JavaWriter writer;
  private TypeM generatedAnnotationType;

  public ManualBuilderSourceGenerator(JavaWriter writer, TypeM generatedAnnotationType) {
    this.writer = writer;
    this.generatedAnnotationType = generatedAnnotationType;
  }

  public void generateSource(ManualBuilderM builder) throws IOException {
    generateSource(builder.getType(), builder.getBaseType(), builder.getPojoType(), builder.getConstructorVisibility(),
        builder.getStaticFactoryMethod());
  }

  private void generateSource(TypeM builderType, TypeM baseType, TypeM pojoType, Visibility constructorVisibility,
      StaticFactoryMethodM staticFactoryMethod) throws IOException {
    // @formatter:off
    writer
        .emitPackage(builderType.getPackageName());
    ImportTypesM importTypes = pojoType.addToImportTypes(new ImportTypesM());
    baseType.addToImportTypes(importTypes);
    importTypes.removePackage(builderType.getPackageName());
    importTypes.removePackage("java.lang");
    generatedAnnotationType.addToImportTypes(importTypes);

    String builderTypeName = writer.compressType(builderType.getName());
    String pojoTypeName = writer.compressType(pojoType.getName());

    writer
        .emitImports(importTypes.getSortedDistinctClassnames())
        .emitEmptyLine()
        .emitJavadoc(
            "The {@link %s} is a Builder for {@link %s} objects.\n"
            +"<p> ATTENTION:\n"
            +"    This class has been generated.\n"
            +"    If you want to ADD HANDWRITTEN CODE,\n"
            +"    please MOVE THIS FILE out of the generated-sources folder\n"
            +"    in order to prevent it from being overwritten by the\n"
            +"    PojoBuilder generator!\n"
            +"</p>\n", builderTypeName, pojoTypeName)
        .emitAnnotation(generatedAnnotationType.getName(), JavaWriter.stringLiteral("PojoBuilder"))
        .beginType(builderType.getGenericTypeDefinition(), "class", EnumSet.of(PUBLIC), baseType.getGenericType());

    if (staticFactoryMethod != null) {
      BuilderSourceGenerator.emitStaticFactoryMethod(builderType, staticFactoryMethod, writer);
    }

    if ( constructorVisibility != Visibility.PUBLIC) {
      emitConstructor(builderType, constructorVisibility);
    }

    writer
        .emitEmptyLine()
        .emitJavadoc("Creates a new {@link %s}.", builderTypeName)
        .beginConstructor(EnumSet.of(Modifier.PUBLIC))
        .endConstructor();

    writer
        .endType();
    // @formatter:on
  }

  private void emitConstructor(TypeM builderType, Visibility visibility) throws IOException {
    String builderTypeName = writer.compressType(builderType.getName());
    Modifier modifier = visibility.asModifier();
    // @formatter:off
    writer
      .emitEmptyLine()
      .emitJavadoc("Creates a new {@link %s}.", builderTypeName).beginConstructor(modifier==null?EnumSet.noneOf(Modifier.class):EnumSet.of(modifier))
      .endConstructor();
    // @formatter:on
  }

}
