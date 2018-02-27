package net.karneim.pojobuilder.sourcegen;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.StringWriter;

import com.squareup.javawriter.JavaWriter;
import net.karneim.pojobuilder.model.ManualBuilderM;
import net.karneim.pojobuilder.model.TypeM;
import net.karneim.pojobuilder.model.TypeVariableM;
import net.karneim.pojobuilder.testenv.TestBase;
import org.junit.Before;
import org.junit.Test;

public class ManualBuilderSourceGenerator_GenerateMinimalManualBuilder_Test extends TestBase {
  StringWriter out;
  JavaWriter writer;
  ManualBuilderSourceGenerator underTest;

  @Before
  public void init() {
    out = new StringWriter();
    writer = new JavaWriter(out);
    underTest = new ManualBuilderSourceGenerator(writer, new TypeM("javax.annotation.processing", "Generated"));
  }

  @Test
  public void testGenerateSourceWithMinimalManualBuilder() throws Exception {
    // Given:  @formatter:off
    ManualBuilderM builder = new ManualBuilderM();
    builder.setPojoType(new TypeM("com.example.output", "Sample"));
    builder.setType(new TypeM("com.example.output","SampleBuilder"));
    builder.setBaseType(new TypeM("com.example.output","AbstractSampleBuilder"));

    // When:
    underTest.generateSource(builder);

    // Then: @formatter:on
    String actual = out.toString().replace("\r", "");
    logDebug(actual);
    String expected = loadResourceFromClasspath("GenerateMinimalManualBuilder.expected.txt");

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testGenerateSourceWithMinimalManualBuilderHavingTypeParameter() throws Exception {
    // Given:  @formatter:off
    ManualBuilderM builder = new ManualBuilderM();
    TypeVariableM T = new TypeVariableM("T")
      .whichExtends(new TypeM("java.lang","Number"));
    builder.setPojoType(new TypeM("com.example.output", "Sample")
      .withTypeParameter(T)
    );
    builder.setType(new TypeM("com.example.output","SampleBuilder")
      .withTypeParameter(T)
    );
    builder.setBaseType(new TypeM("com.example.output","AbstractSampleBuilder")
      .withTypeParameter(T)
    );

    // When:
    underTest.generateSource(builder);

    // Then: @formatter:on
    String actual = out.toString().replace("\r", "");
    logDebug(actual);
    String expected = loadResourceFromClasspath("GenerateMinimalManualBuilderHavingTypeParameter.expected.txt");

    assertThat(actual).isEqualTo(expected);
  }

}
