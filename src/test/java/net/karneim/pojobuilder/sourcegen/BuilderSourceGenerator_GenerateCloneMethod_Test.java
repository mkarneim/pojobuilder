package net.karneim.pojobuilder.sourcegen;

import com.squareup.javawriter.JavaWriter;
import net.karneim.pojobuilder.model.*;
import net.karneim.pojobuilder.testenv.TestBase;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.EnumSet;

import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PUBLIC;
import static org.assertj.core.api.Assertions.assertThat;

public class BuilderSourceGenerator_GenerateCloneMethod_Test extends TestBase {

  private StringWriter out;
  private JavaWriter writer;
  private BuilderSourceGenerator underTest;
  private BuilderM builder;

  @Before
  public void init() {
    out = new StringWriter();
    writer = new JavaWriter(out);
    underTest = new BuilderSourceGenerator(writer);
  }

  @Before
  public void given() {
    TypeM pojoType = new TypeM("com.example.output", "Sample");

    builder = new BuilderM();
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
    builder.setCloneMethod(new CloneMethodM());
  }

  @Test
  public void testShouldGenerateCloneMethod() throws Exception {
    // Given:
    // When:
    underTest.generateSource(builder);
    // Then:
    String actual = out.toString().replace("\r", "");
    logDebug(actual);
    String expected = loadResourceFromClasspath("GenerateCopyMethod.expected.txt");

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testShouldGenerateCloneMethodWithoutCatch() throws Exception {
    // Given:
    builder.getCloneMethod().setCatchesCloneException(false);
    // When:
    underTest.generateSource(builder);
    // Then:
    String actual = out.toString().replace("\r", "");
    logDebug(actual);
    String expected = loadResourceFromClasspath("GenerateCloneMethod.withoutCatch.expected.txt");

    assertThat(actual).isEqualTo(expected);
  }


}
