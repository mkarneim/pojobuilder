package net.karneim.pojobuilder.sourcegen;

import static javax.lang.model.element.Modifier.PUBLIC;

import java.io.IOException;
import java.util.EnumSet;

import javax.annotation.Generated;
import javax.lang.model.element.Modifier;

import net.karneim.pojobuilder.model.ImportTypesM;
import net.karneim.pojobuilder.model.ManualBuilderM;
import net.karneim.pojobuilder.model.TypeM;

import com.squareup.javawriter.JavaWriter;

public class ManualBuilderSourceGenerator {

  private JavaWriter writer;

  public ManualBuilderSourceGenerator(JavaWriter writer) {
    this.writer = writer;
  }

  public void generateSource(ManualBuilderM builder) throws IOException {
    generateSource(builder.getType(), builder.getBaseType(), builder.getPojoType());
  }

  private void generateSource(TypeM builderType, TypeM baseType, TypeM pojoType) throws IOException {
    // @formatter:off
    writer
        .emitPackage(builderType.getPackageName());
    ImportTypesM importTypes = pojoType.addToImportTypes(new ImportTypesM());
    importTypes.add(Generated.class);
    baseType.addToImportTypes(importTypes);
    importTypes.removePackage(builderType.getPackageName());
    importTypes.removePackage("java.lang");
    
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
        .emitAnnotation(Generated.class, JavaWriter.stringLiteral("PojoBuilder"))
        .beginType(builderType.getGenericType(), "class", EnumSet.of(PUBLIC), baseType.getGenericTypeDeclaration())
        .emitEmptyLine()
        .emitJavadoc("Creates a new {@link %s}.", builderTypeName)
        .beginConstructor(EnumSet.of(Modifier.PUBLIC))
        .endConstructor()
        .endType();
    // @formatter:on
  }

}
