package net.karneim.pojobuilder.processor.with.settername;

import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @feature The name pattern of the generated with-methods can be configured.
 */
public class AnnotationProcessor_WithMethodNamePattern_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the name pattern is "set*"
   */
  @Test
  public void testPatternIsSetAsterisk() throws Exception {
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
   * @scenario the name pattern is "*"
   */
  @Test
  public void testPatternIsAsterisk() throws Exception {
    // Given:
    sourceFor(Pojo2.class);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .has(generatedSameSourceAs(Pojo2Builder.class))
        .has(compiled(Pojo2Builder.class));
    assertThat(success).isTrue();
  }
}
