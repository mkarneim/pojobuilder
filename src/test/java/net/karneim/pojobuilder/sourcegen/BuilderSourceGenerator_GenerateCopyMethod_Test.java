package net.karneim.pojobuilder.sourcegen;

import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PUBLIC;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.StringWriter;
import java.util.EnumSet;

import com.squareup.javawriter.JavaWriter;
import net.karneim.pojobuilder.model.BuildMethodM;
import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.CloneMethodM;
import net.karneim.pojobuilder.model.ConstructorParameterM;
import net.karneim.pojobuilder.model.CopyMethodM;
import net.karneim.pojobuilder.model.FieldAccessM;
import net.karneim.pojobuilder.model.MethodM;
import net.karneim.pojobuilder.model.PrimitiveTypeM;
import net.karneim.pojobuilder.model.PropertyListM;
import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.model.SetterMethodM;
import net.karneim.pojobuilder.model.TypeM;
import net.karneim.pojobuilder.testenv.TestBase;
import org.junit.Before;
import org.junit.Test;

public class BuilderSourceGenerator_GenerateCopyMethod_Test extends TestBase {
  StringWriter out;
  JavaWriter writer;
  BuilderSourceGenerator underTest;

  @Before
  public void init() {
    out = new StringWriter();
    writer = new JavaWriter(out);
    underTest = new BuilderSourceGenerator(writer, new TypeM("javax.annotation.processing", "Generated"));
  }

  @Test
  public void testShouldGenerateCopyMethod() throws Exception {
    // Given:  @formatter:off
    TypeM pojoType = new TypeM("com.example.output", "Sample");

    BuilderM builder = new BuilderM();
    builder.setPojoType(pojoType);
    builder.setProperties( new PropertyListM(
      new PropertyM("someBoolean", PrimitiveTypeM.BOOLEAN)
        .accessibleVia(new FieldAccessM(EnumSet.of(PUBLIC)).declaredIn(pojoType))
        .withMethodNamePattern("with*"),
      new PropertyM("someChar", PrimitiveTypeM.CHAR)
        .accessibleVia(new FieldAccessM(EnumSet.of(PRIVATE)).declaredIn(pojoType))
        .writableVia(new SetterMethodM("setSomeChar", EnumSet.of(PUBLIC)).declaredIn(pojoType))
        .readableVia(new MethodM("getSomeChar", EnumSet.of(PUBLIC)).declaredIn(pojoType))
        .withMethodNamePattern("with*"),
      new PropertyM("someString", new TypeM("java.lang","String"))
        .accessibleVia(new FieldAccessM(EnumSet.of(PUBLIC)))
        .writableVia(new SetterMethodM("setSomeString", EnumSet.of(PUBLIC)).declaredIn(pojoType))
        .readableVia(new MethodM("getSomeString", EnumSet.of(PUBLIC)).declaredIn(pojoType))
        .withMethodNamePattern("with*")
    ));
    builder.setType(new TypeM("com.example.output","SampleBuilder"));
    builder.setSelfType(builder.getType());
    builder.setCopyMethod(new CopyMethodM("copy"));
    builder.setBuildMethod( new BuildMethodM());
    builder.setCloneMethod( new CloneMethodM().setShouldCatchCloneNotSupportedException(true));

    // Assume: properties are returned in insertion order
    assertThat(builder.getProperties().iterator()).extracting("propertyName").containsExactly("someBoolean","someChar","someString");

    // When:
    underTest.generateSource(builder);

    // Then: @formatter:on
    String actual = out.toString().replace("\r", "");
    logDebug(actual);
    String expected = loadResourceFromClasspath("GenerateCopyMethod.expected.txt");

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testShouldSkipCopyMethod() throws Exception {
    // Given:  @formatter:off
    TypeM pojoType = new TypeM("com.example.output", "Sample");

    BuilderM builder = new BuilderM();
    builder.setPojoType(pojoType);
    builder.setProperties( new PropertyListM(
      new PropertyM("someBoolean", PrimitiveTypeM.BOOLEAN)
        .accessibleVia(new FieldAccessM(EnumSet.of(PRIVATE)).declaredIn(pojoType))
        .writableVia(new ConstructorParameterM(0))
        .withMethodNamePattern("with*"),
      new PropertyM("someChar", PrimitiveTypeM.CHAR)
        .accessibleVia(new FieldAccessM(EnumSet.of(PRIVATE)).declaredIn(pojoType))
        .writableVia(new ConstructorParameterM(1))
        .withMethodNamePattern("with*"),
      new PropertyM("someString", new TypeM("java.lang","String"))
        .accessibleVia(new FieldAccessM(EnumSet.of(PRIVATE)))
        .writableVia(new ConstructorParameterM(2))
        .withMethodNamePattern("with*")
    ));
    builder.setType(new TypeM("com.example.output","SampleBuilder"));
    builder.setSelfType(builder.getType());
    builder.setCopyMethod(new CopyMethodM("copy"));
    builder.setBuildMethod( new BuildMethodM());
    builder.setCloneMethod( new CloneMethodM().setShouldCatchCloneNotSupportedException(true));

    // Assume: properties are returned in insertion order
    assertThat(builder.getProperties().iterator()).extracting("propertyName").containsExactly("someBoolean","someChar","someString");

    // When:
    underTest.generateSource(builder);

    // Then: @formatter:on
    String actual = out.toString().replace("\r", "");
    logDebug(actual);
    String expected = loadResourceFromClasspath("GenerateCopyMethod_skipped.expected.txt");

    assertThat(actual).isEqualTo(expected);
    assertThat(underTest.getWarnings()).hasSize(1);
  }


}
