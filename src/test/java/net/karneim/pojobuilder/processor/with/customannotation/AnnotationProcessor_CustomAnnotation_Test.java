package net.karneim.pojobuilder.processor.with.customannotation;

import static org.assertj.core.api.Assertions.assertThat;
import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.customannotation.builder.FluentPojoABBuilderB;
import net.karneim.pojobuilder.processor.with.customannotation.builder.FluentPojoABuilderA;
import net.karneim.pojobuilder.testenv.JavaProject;
import net.karneim.pojobuilder.testenv.TestBase;
import net.karneim.pojobuilder.testenv.Util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_CustomAnnotation_Test extends TestBase {

  private JavaProject prj = new JavaProject(Util.createTempDir());

  @Before
  public void setupJavaProject() {
    // Enable the AnnotationProcessor
    prj.getProcessorClasses().add(AnnotationProcessor.class);
  }

  @After
  public void tearDownJavaProject() {
    prj.delete();
  }

  /**
   * @scenario
   * @throws Exception
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
   * @scenario
   * @throws Exception
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
   * @scenario
   * @throws Exception
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
