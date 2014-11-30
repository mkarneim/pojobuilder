package net.karneim.pojobuilder.processor.with.noannotation;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_NoAnnotation_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario no builder is created for a pojo having no @GeneratePojoBuilder annotation
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
