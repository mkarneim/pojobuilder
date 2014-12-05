package net.karneim.pojobuilder.processor.with.generationgap;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static net.karneim.pojobuilder.PbAssertions.assertThat;
import static net.karneim.pojobuilder.testenv.JavaProject.Compilation;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_GenerationGap_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario Should generate AbstractOrderBuilder and OrderBuilder
   */
  @Test
  public void testShouldGenerateAbstractPlayerBuilderAndPlayerBuilder() {
    // Given:
    sourceFor(Order.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs("net.karneim.pojobuilder.processor.with.generationgap.AbstractOrderBuilder")
        .generatedSameSourceAs("net.karneim.pojobuilder.processor.with.generationgap.OrderBuilder")
        .reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario Should generate {@link AbstractPlayerBuilder} but not {@link PlayerBuilder} since it already exists.
   */
  @Test
  public void testShouldGenerateOnlyAbstractPlayerBuilderButNotPlayerBuilder() {
    // Given:
    sourceFor(Player.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(AbstractPlayerBuilder.class)
        .compiled(AbstractPlayerBuilder.class)
        .didNotGenerateSourceFor(PlayerBuilder.class)
        .compiled(PlayerBuilder.class)
        .reported(Compilation.Success);
  }

}
