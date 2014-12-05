package net.karneim.pojobuilder.processor.with.staticfactorymethod;

import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static net.karneim.pojobuilder.PbAssertions.assertThat;
import static net.karneim.pojobuilder.testenv.JavaProject.Compilation;

/**
 * @feature The {@link net.karneim.pojobuilder.processor.AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_StaticFactoryMethod_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder is created with a factory method
   */
  @Test
  public void testShouldGenerateFactoryMethod() throws Exception {
    // Given:
    sourceFor(Trouble.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(TroubleBuilder.class)
        .compiled(TroubleBuilder.class)
        .reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the manual builder is created with a factory method
   */
  @Test
  public void testShouldGenerateFactoryMethodOnManualClass() throws Exception {
    // Given:
    sourceFor(Strife.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs("net.karneim.pojobuilder.processor.with.staticfactorymethod.AbstractStrifeBuilder")
        .generatedSameSourceAs("net.karneim.pojobuilder.processor.with.staticfactorymethod.StrifeBuilder")
        .reported(Compilation.Success);
  }

}
