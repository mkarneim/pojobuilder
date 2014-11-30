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
    String pojoClassname = PojoA.class.getName();
    String customAnnotationA = MyCustomAnnotationA.class.getName();
    String builderClassname = FluentPojoABuilderA.class.getName();
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojoClassname));
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, customAnnotationA));

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
    String pojoClassname = PojoAB.class.getName();
    String customAnnotationA = MyCustomAnnotationA.class.getName();
    String customAnnotationB = MyCustomAnnotationB.class.getName();
    String builderClassname = FluentPojoABBuilderB.class.getName();
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojoClassname));
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, customAnnotationA));
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, customAnnotationB));

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
    String pojoClassname = PojoC.class.getName();
    String customAnnotationA = MyCustomAnnotationA.class.getName();
    String customAnnotationB = MyCustomAnnotationB.class.getName();
    String customAnnotationC = MyCustomAnnotationC.class.getName();
    String builderClassname = "net.karneim.pojobuilder.processor.with.customannotation.builder.FluentPojoCBuilderB";
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojoClassname));
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, customAnnotationA));
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, customAnnotationB));
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, customAnnotationC));

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
