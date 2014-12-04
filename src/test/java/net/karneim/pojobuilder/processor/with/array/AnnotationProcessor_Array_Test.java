package net.karneim.pojobuilder.processor.with.array;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_Array_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder is created with appropriate array properties.
   */
  @Test
  public void testShouldGenerateBuilderWithArrayProperties() throws Exception {
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

  /**
   * @throws Exception
   * @scenario the builder is created with appropriate generic array properties.
   */
  @Test
  public void testShouldGenerateBuilderWithGenericArrayProperties() throws Exception {
    // Given:
    sourceFor(GenericPojo.class);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .has(generatedSameSourceAs(GenericPojoBuilder.class))
        .has(compiled(GenericPojoBuilder.class));
    assertThat(success).isTrue();
  }
}
