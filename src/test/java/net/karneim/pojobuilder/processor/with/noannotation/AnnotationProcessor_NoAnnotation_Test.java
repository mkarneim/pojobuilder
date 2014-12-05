package net.karneim.pojobuilder.processor.with.noannotation;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static net.karneim.pojobuilder.PbAssertions.assertThat;

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
    sourceFor(EmptyPojo.class);
    String nameOfNotGeneratedBuilder = "samples.with.noannotation.EmptyPojoBuilder";
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .didNotGenerateSourceFor(nameOfNotGeneratedBuilder);
    assertThat(success).isTrue();
  }

}
