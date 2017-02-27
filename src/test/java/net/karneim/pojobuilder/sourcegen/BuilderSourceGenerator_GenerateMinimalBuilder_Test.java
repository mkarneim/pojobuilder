package net.karneim.pojobuilder.sourcegen;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.StringWriter;

import net.karneim.pojobuilder.model.BuildMethodM;
import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.CloneMethodM;
import net.karneim.pojobuilder.model.TypeM;
import net.karneim.pojobuilder.testenv.TestBase;

import org.junit.Before;
import org.junit.Test;

import com.squareup.javawriter.JavaWriter;

public class BuilderSourceGenerator_GenerateMinimalBuilder_Test extends TestBase {
  StringWriter out;
  JavaWriter writer;
  BuilderSourceGenerator underTest;

  @Before
  public void init() {
    out = new StringWriter();
    writer = new JavaWriter(out);
    underTest = new BuilderSourceGenerator(writer);
  }

  @Test
  public void test() throws Exception {
    // Given:  @formatter:off
    TypeM pojoType = new TypeM("com.example.output", "Sample");
    
    BuilderM builder = new BuilderM();
    builder.setPojoType(pojoType);
    builder.setType(new TypeM("com.example.output","SampleBuilder"));
    builder.setSelfType(builder.getType());
    builder.setBuildMethod( new BuildMethodM());
    builder.setCloneMethod(new CloneMethodM());

    // When:
    underTest.generateSource(builder);
    
    // Then: @formatter:on
    String actual = out.toString().replace("\r", "");
    logDebug(actual);
    String expected = loadResourceFromClasspath("GenerateMinimalBuilder.expected.txt");

    assertThat(actual).isEqualTo(expected);
  }

}
