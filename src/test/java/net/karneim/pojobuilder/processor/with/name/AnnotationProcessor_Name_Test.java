package net.karneim.pojobuilder.processor.with.name;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_Name_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder is created with a specific name that has been configured via @GeneratePojoBuilder annotation
   */
  @Test
  public void testShouldGenerateBuilderWithConfiguredName() throws Exception {
    // Given:
    sourceFor(SampleBean4.class);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .has(generatedSameSourceAs(FluentSampleBean4Builder.class))
        .has(compiled(FluentSampleBean4Builder.class));
    assertThat(success).isTrue();
  }
}
