package net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap;

import static org.assertj.core.api.Assertions.assertThat;
import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.testenv.JavaProject;
import net.karneim.pojobuilder.testenv.TestBase;
import net.karneim.pojobuilder.testenv.Util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_BaseclassAndGenerationGap_Test extends TestBase {

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
   * @scenario the abstract builder is created with a simple base class.
   * @throws Exception
   */
  @Test
  public void testShouldGenerateBuilderWithSimpleBaseClass() throws Exception {
    // Given:
    String pojoClassname = Pojo1.class.getName();
    String baseClassname = SimpleBaseBuilder.class.getName();
    String abstractBuilderClassname =
        "net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap.AbstractPojo1Builder";
    String manualBuilderClassname = "net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap.Pojo1Builder";
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojoClassname));
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, baseClassname));

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
   * @scenario the abstract builder is created with a base class declaring generic build() method.
   * @throws Exception
   */
  @Test
  public void testShouldGenerateBuilderWithBaseClassThatDeclaresGenericBuildMethod() throws Exception {
    // Given:
    String pojoClassname = Pojo2.class.getName();
    String baseClassname = BaseBuilderWithGenericBuildMethod.class.getName();
    String abstractBuilderClassname =
        "net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap.AbstractPojo2Builder";
    String manualBuilderClassname = "net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap.Pojo2Builder";
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojoClassname));
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, baseClassname));

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
   * @scenario the abstract builder is created with a base class declaring a raw build() method.
   * @throws Exception
   */
  @Test
  public void testShouldGenerateBuilderWithBaseClassThatDeclaresRawBuildMethod() throws Exception {
    // Given:
    String pojoClassname = Pojo3.class.getName();
    String baseClassname = BaseBuilderWithRawBuildMethod.class.getName();
    String abstractBuilderClassname =
        "net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap.AbstractPojo3Builder";
    String manualBuilderClassname = "net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap.Pojo3Builder";
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojoClassname));
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, baseClassname));

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
