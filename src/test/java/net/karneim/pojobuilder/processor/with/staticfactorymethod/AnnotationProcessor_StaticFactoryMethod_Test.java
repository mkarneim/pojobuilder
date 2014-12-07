package net.karneim.pojobuilder.processor.with.staticfactorymethod;

import static net.karneim.pojobuilder.PbAssertions.assertThat;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.testenv.JavaProject.Compilation;

import org.junit.Test;

/**
 * @feature The {@link net.karneim.pojobuilder.processor.AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_StaticFactoryMethod_Test extends ProcessorTestSupport {

  /**
   * @scenario the builder is created with a factory method
   */
  @Test
  public void testShouldGenerateFactoryMethod() {
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
   * @scenario the manual builder is created with a factory method
   */
  @Test
  public void testShouldGenerateFactoryMethodOnManualClass() {
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

  /**
   * @scenario the builder is created with a factory method
   */
  @Test
  public void testShouldGenerateFactoryMethodForGenericPojo() {
    // Given:
    sourceFor(Container.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(ContainerBuilder.class)
        .compiled(ContainerBuilder.class)
        .reported(Compilation.Success);
  }
}
