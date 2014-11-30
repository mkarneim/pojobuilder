package net.karneim.pojobuilder.processor.with.customannotation;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.processor.with.customannotation.builder.FluentPojoABBuilderB;
import net.karneim.pojobuilder.processor.with.customannotation.builder.FluentPojoABuilderA;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_CustomAnnotation_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario
   */
  @Test
  public void testShouldGenerateBuilderForPojoWithSingleCustomAnnotation() throws Exception {
    // Given:
    sourceFor(PojoA.class);
    sourceFor(MyCustomAnnotationA.class);
    String builderClassname = FluentPojoABuilderA.class.getName();

    // When:
    boolean success = prj.compile();

    // Then:
    String actual = getContent(prj.findGeneratedSource(builderClassname));
    logDebug(actual);
    assertThat(success).isTrue();

    String expected = loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname));
    assertThat(actual).isEqualTo(expected);
    assertThat(prj.findClass(builderClassname)).isNotNull();
  }

  /**
   * @throws Exception
   * @scenario
   */
  @Test
  public void testShouldGenerateBuilderForPojoWithMultipleCustomAnnotations() throws Exception {
    // Given:
    sourceFor(PojoAB.class);
    sourceFor(MyCustomAnnotationA.class);
    sourceFor(MyCustomAnnotationB.class);
    String builderClassname = FluentPojoABBuilderB.class.getName();

    // When:
    boolean success = prj.compile();

    // Then:
    String actual = getContent(prj.findGeneratedSource(builderClassname));
    logDebug(actual);
    assertThat(success).isTrue();

    String expected = loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname));
    assertThat(actual).isEqualTo(expected);
    assertThat(prj.findClass(builderClassname)).isNotNull();
  }

  /**
   * @throws Exception
   * @scenario
   */
  @Test
  public void testShouldGenerateBuilderForPojoWithMultipleCustomAnnotationsInAnnotationHierarchy() throws Exception {
    // Given:
    sourceFor(PojoC.class);
    sourceFor(MyCustomAnnotationA.class);
    sourceFor(MyCustomAnnotationB.class);
    sourceFor(MyCustomAnnotationC.class);
    String builderClassname = "net.karneim.pojobuilder.processor.with.customannotation.builder.FluentPojoCBuilderB";

    // When:
    boolean success = prj.compile();

    // Then:
    String actual = getContent(prj.findGeneratedSource(builderClassname));
    logDebug(actual);
    assertThat(success).isTrue();

    String expected = loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname));
    assertThat(actual).isEqualTo(expected);
    assertThat(prj.findClass(builderClassname)).isNotNull();
  }

}
