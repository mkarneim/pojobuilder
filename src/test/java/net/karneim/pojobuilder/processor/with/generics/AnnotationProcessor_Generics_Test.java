package net.karneim.pojobuilder.processor.with.generics;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static net.karneim.pojobuilder.PbAssertions.assertThat;
import static net.karneim.pojobuilder.testenv.JavaProject.Compilation;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_Generics_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder is created with appropriate generic properties.
   */
  @Test
  public void testShouldGenerateBuilderWithGenericProperties() {
    // Given:
    sourceFor(Pojo.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(PojoBuilder.class)
        .compiled(PojoBuilder.class)
        .reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder is created with appropriate generic properties.
   */
  @Test
  public void testShouldGeneratePairBuilder() {
    // Given:
    sourceFor(Pair.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(PairBuilder.class)
        .compiled(PairBuilder.class)
        .reported(Compilation.Success);
  }
}
