package net.karneim.pojobuilder.sourcegen;

import static javax.lang.model.element.Modifier.PUBLIC;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.StringWriter;
import java.util.EnumSet;

import net.karneim.pojobuilder.model.BuildMethodM;
import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.CloneMethodM;
import net.karneim.pojobuilder.model.FieldAccessM;
import net.karneim.pojobuilder.model.PrimitiveTypeM;
import net.karneim.pojobuilder.model.PropertyListM;
import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.model.TypeM;
import net.karneim.pojobuilder.model.TypeVariableM;
import net.karneim.pojobuilder.testenv.TestBase;

import org.junit.Before;
import org.junit.Test;

import com.squareup.javawriter.JavaWriter;

/**
 * @feature The generator generates protected field pairs for each property.
 */
public class BuilderSourceGenerator_GenerateFieldPairsForEachProperty_Test extends TestBase {
  StringWriter out;
  JavaWriter writer;
  BuilderSourceGenerator underTest;

  @Before
  public void init() {
    out = new StringWriter();
    writer = new JavaWriter(out);
    underTest = new BuilderSourceGenerator(writer, new TypeM("javax.annotation.processing", "Generated"));
  }

  /**
   * @scenario primitive properties
   * @throws Exception
   */
  @Test
  public void testPrimitiveProperties() throws Exception {
    // Given:  @formatter:off
    TypeM pojoType = new TypeM("com.example.output", "Sample");

    BuilderM builder = new BuilderM();
    builder.setPojoType(pojoType);
    builder.setProperties( new PropertyListM(
      new PropertyM("someBoolean", PrimitiveTypeM.BOOLEAN)
        .accessibleVia(new FieldAccessM(EnumSet.of(PUBLIC)))
        .withMethodNamePattern("with*"),
      new PropertyM("someChar", PrimitiveTypeM.CHAR)
        .accessibleVia(new FieldAccessM(EnumSet.of(PUBLIC)))
        .withMethodNamePattern("with*"),
      new PropertyM("someByte", PrimitiveTypeM.BYTE)
        .accessibleVia(new FieldAccessM(EnumSet.of(PUBLIC)))
        .withMethodNamePattern("with*"),
      new PropertyM("someShort", PrimitiveTypeM.SHORT)
        .accessibleVia(new FieldAccessM(EnumSet.of(PUBLIC)))
        .withMethodNamePattern("with*"),
      new PropertyM("someInt", PrimitiveTypeM.INT)
        .accessibleVia(new FieldAccessM(EnumSet.of(PUBLIC)))
        .withMethodNamePattern("with*"),
      new PropertyM("someLong", PrimitiveTypeM.LONG)
        .accessibleVia(new FieldAccessM(EnumSet.of(PUBLIC)))
        .withMethodNamePattern("with*"),
      new PropertyM("someFloat", PrimitiveTypeM.FLOAT)
        .accessibleVia(new FieldAccessM(EnumSet.of(PUBLIC)))
        .withMethodNamePattern("with*"),
      new PropertyM("someDouble", PrimitiveTypeM.DOUBLE)
        .accessibleVia(new FieldAccessM(EnumSet.of(PUBLIC)))
        .withMethodNamePattern("with*")
    ));

    builder.setType(new TypeM("com.example.output","SampleBuilder"));
    builder.setSelfType(builder.getType());
    builder.setBuildMethod( new BuildMethodM());
    builder.setCloneMethod( new CloneMethodM().setShouldCatchCloneNotSupportedException(true));

    // When:
    underTest.generateSource(builder);

    // Then: @formatter:on
    String actual = out.toString().replace("\r", "");
    logDebug(actual);
    String expected = loadResourceFromClasspath("PrimitiveProperties.expected.txt");

    assertThat(actual).isEqualTo(expected);
  }

  /**
   * @scenario object properties
   * @throws Exception
   */
  @Test
  public void testObjectProperties() throws Exception {
    // Given:  @formatter:off
    TypeM pojoType = new TypeM("com.example.output", "Sample");

    BuilderM builder = new BuilderM();
    builder.setPojoType(pojoType);
    builder.setProperties( new PropertyListM(
      new PropertyM("someString", new TypeM("java.lang","String"))
        .accessibleVia(new FieldAccessM(EnumSet.of(PUBLIC)))
        .withMethodNamePattern("with*"),
      new PropertyM("someFile", new TypeM("java.io","File"))
        .accessibleVia(new FieldAccessM(EnumSet.of(PUBLIC)))
        .withMethodNamePattern("with*"),
      new PropertyM("someInteger", new TypeM("java.lang","Integer"))
        .accessibleVia(new FieldAccessM(EnumSet.of(PUBLIC)))
        .withMethodNamePattern("with*"),
      new PropertyM("someBigDecimal", new TypeM("java.math","BigDecimal"))
        .accessibleVia(new FieldAccessM(EnumSet.of(PUBLIC)))
        .withMethodNamePattern("with*")
    ));

    builder.setType(new TypeM("com.example.output","SampleBuilder"));
    builder.setSelfType(builder.getType());
    builder.setBuildMethod( new BuildMethodM());
    builder.setCloneMethod( new CloneMethodM().setShouldCatchCloneNotSupportedException(true));

    // When:
    underTest.generateSource(builder);

    // Then: @formatter:on
    String actual = out.toString().replace("\r", "");
    logDebug(actual);
    String expected = loadResourceFromClasspath("ObjectProperties.expected.txt");

    assertThat(actual).isEqualTo(expected);
  }


  /**
   * @scenario generic properties
   * @throws Exception
   */
  @Test
  public void testGenericProperties() throws Exception {
    // Given:  @formatter:off
    TypeM pojoType = new TypeM("com.example.output", "Sample");

    BuilderM builder = new BuilderM();
    builder.setPojoType(pojoType);
    builder.setProperties( new PropertyListM(
      new PropertyM("someStringList", new TypeM("java.util","List")
        .withTypeParameter(new TypeM("java.lang","String")))
        .accessibleVia(new FieldAccessM(EnumSet.of(PUBLIC)))
        .withMethodNamePattern("with*"),
      new PropertyM("someMap", new TypeM("java.util","Map")
        .withTypeParameter(new TypeM("java.lang","String"),new TypeM("java.lang","Integer")))
        .accessibleVia(new FieldAccessM(EnumSet.of(PUBLIC)))
        .withMethodNamePattern("with*")
    ));

    builder.setType(new TypeM("com.example.output","SampleBuilder"));
    builder.setSelfType(builder.getType());
    builder.setBuildMethod( new BuildMethodM());
    builder.setCloneMethod( new CloneMethodM().setShouldCatchCloneNotSupportedException(true));

    // When:
    underTest.generateSource(builder);

    // Then: @formatter:on
    String actual = out.toString().replace("\r", "");
    logDebug(actual);
    String expected = loadResourceFromClasspath("GenericProperties.expected.txt");

    assertThat(actual).isEqualTo(expected);
  }

  /**
   * @scenario parameterized generic properties
   * @throws Exception
   */
  @Test
  public void testParameterizedGenericProperties() throws Exception {
    // Given:  @formatter:off
    TypeVariableM K = new TypeVariableM("K");
    TypeVariableM V = new TypeVariableM("V").whichExtends(new TypeM("java.lang","Number"));
    TypeM pojoType = new TypeM("com.example.output", "Sample").withTypeParameter( K, V);

    BuilderM builder = new BuilderM();
    builder.setPojoType(pojoType);
    builder.setProperties( new PropertyListM(
      new PropertyM("someMap", new TypeM("java.util","Map")
        .withTypeParameter(K, V))
        .accessibleVia(new FieldAccessM(EnumSet.of(PUBLIC)))
        .withMethodNamePattern("with*")
    ));

    builder.setType(new TypeM("com.example.output","SampleBuilder").withTypeParameter(K, V));
    builder.setSelfType(builder.getType());
    builder.setBuildMethod( new BuildMethodM());
    builder.setCloneMethod( new CloneMethodM().setShouldCatchCloneNotSupportedException(true));

    // When:
    underTest.generateSource(builder);

    // Then: @formatter:on
    String actual = out.toString().replace("\r", "");
    logDebug(actual);
    String expected = loadResourceFromClasspath("ParameterizedGenericProperties.expected.txt");

    System.out.println(actual);
    assertThat(actual).isEqualTo(expected);
  }

}
