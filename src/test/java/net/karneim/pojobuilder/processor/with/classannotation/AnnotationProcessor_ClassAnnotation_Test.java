package net.karneim.pojobuilder.processor.with.classannotation;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_ClassAnnotation_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder is created for a pojo annotated with @GeneratePojoBuilder on class-level
   */
  @Test
  public void testShouldGenerateBuilderForAnnotatedClassOnCLassLevel() throws Exception {
    // Given:
    sourceFor(Pojo.class);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .has(generatedSameSourceAs(PojoBuilder.class))
        .has(compiled(PojoBuilder.class));
    assertThat(success).isTrue();
  }

}
