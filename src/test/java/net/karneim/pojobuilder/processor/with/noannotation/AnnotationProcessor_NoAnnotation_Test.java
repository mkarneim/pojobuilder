package net.karneim.pojobuilder.processor.with.noannotation;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static net.karneim.pojobuilder.PbAssertions.assertThat;
import static net.karneim.pojobuilder.testenv.JavaProject.Compilation;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_NoAnnotation_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario no builder is created for a pojo having no @GeneratePojoBuilder annotation
   */
  @Test
  public void testShouldNotGenerateBuilderForEmptyPojo() {
    // Given:
    sourceFor(EmptyPojo.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .didNotGenerateSourceFor("samples.with.noannotation.EmptyPojoBuilder")
        .reported(Compilation.Success);
  }

}
