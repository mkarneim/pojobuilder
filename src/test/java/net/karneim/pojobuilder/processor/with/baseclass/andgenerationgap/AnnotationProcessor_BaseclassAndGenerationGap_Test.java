package net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_BaseclassAndGenerationGap_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the abstract builder is created with a simple base class.
   */
  @Test
  public void testShouldGenerateBuilderWithSimpleBaseClass() throws Exception {
    // Given:
    sourceFor(Pojo1.class);
    sourceFor(SimpleBaseBuilder.class);
    String abstractBuilderClassname =
        "net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap.AbstractPojo1Builder";
    String manualBuilderClassname = "net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap.Pojo1Builder";

    // When:
    boolean success = prj.compile();

    // Then:
    String actual1 = getContent(prj.findGeneratedSource(abstractBuilderClassname));
    logDebug(actual1);
    String actual2 = getContent(prj.findGeneratedSource(manualBuilderClassname));
    logDebug(actual2);
    assertThat(success).isTrue();

    String expected1 = loadResourceFromClasspath("AbstractPojo1Builder.expected.txt");
    assertThat(actual1).isEqualTo(expected1);
    assertThat(prj.findClass(abstractBuilderClassname)).isNotNull();

    String expected2 = loadResourceFromClasspath("Pojo1Builder.expected.txt");
    assertThat(actual2).isEqualTo(expected2);
    assertThat(prj.findClass(manualBuilderClassname)).isNotNull();
  }

  /**
   * @throws Exception
   * @scenario the abstract builder is created with a base class declaring generic build() method.
   */
  @Test
  public void testShouldGenerateBuilderWithBaseClassThatDeclaresGenericBuildMethod() throws Exception {
    // Given:
    sourceFor(Pojo2.class);
    sourceFor(BaseBuilderWithGenericBuildMethod.class);
    String abstractBuilderClassname =
        "net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap.AbstractPojo2Builder";
    String manualBuilderClassname = "net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap.Pojo2Builder";

    // When:
    boolean success = prj.compile();

    // Then:
    assertThat(success).isTrue();
    String actual1 = getContent(prj.findGeneratedSource(abstractBuilderClassname));
    logDebug(actual1);
    String actual2 = getContent(prj.findGeneratedSource(manualBuilderClassname));
    logDebug(actual2);

    String expected1 = loadResourceFromClasspath("AbstractPojo2Builder.expected.txt");
    assertThat(actual1).isEqualTo(expected1);
    assertThat(prj.findClass(abstractBuilderClassname)).isNotNull();

    String expected2 = loadResourceFromClasspath("Pojo2Builder.expected.txt");
    assertThat(actual2).isEqualTo(expected2);
    assertThat(prj.findClass(manualBuilderClassname)).isNotNull();
  }

  /**
   * @throws Exception
   * @scenario the abstract builder is created with a base class declaring a raw build() method.
   */
  @Test
  public void testShouldGenerateBuilderWithBaseClassThatDeclaresRawBuildMethod() throws Exception {
    // Given:
    sourceFor(Pojo3.class);
    sourceFor(BaseBuilderWithRawBuildMethod.class);
    String abstractBuilderClassname =
        "net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap.AbstractPojo3Builder";
    String manualBuilderClassname = "net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap.Pojo3Builder";

    // When:
    boolean success = prj.compile();

    // Then:
    assertThat(success).isTrue();
    String actual1 = getContent(prj.findGeneratedSource(abstractBuilderClassname));
    logDebug(actual1);
    String actual2 = getContent(prj.findGeneratedSource(manualBuilderClassname));
    logDebug(actual2);

    String expected1 = loadResourceFromClasspath("AbstractPojo3Builder.expected.txt");
    assertThat(actual1).isEqualTo(expected1);
    assertThat(prj.findClass(abstractBuilderClassname)).isNotNull();

    String expected2 = loadResourceFromClasspath("Pojo3Builder.expected.txt");
    assertThat(actual2).isEqualTo(expected2);
    assertThat(prj.findClass(manualBuilderClassname)).isNotNull();
  }
}
