package net.karneim.pojobuilder.processor.with.noannotation;

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
public class AnnotationProcessor_NoAnnotation_Test extends TestBase {

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
   * @scenario no builder is created for a pojo having no @GeneratePojoBuilder annotation
   * @throws Exception
   */
  @Test
  public void testShouldNotGenerateBuilderForEmptyPojo() throws Exception {
    // Given:
    String pojoClassname = EmptyPojo.class.getName();
    String nameOfNotGeneratedBuilder = "samples.with.noannotation.EmptyPojoBuilder";
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojoClassname));

    // When:
    boolean success = prj.compile();

    // Then:
    assertThat(success).isTrue();
    Class<?> pojoClass = prj.findClass(pojoClassname);
    assertThat(pojoClass).isNotNull();
    assertThat(prj.findGeneratedSource(nameOfNotGeneratedBuilder)).isNull();
  }


}
