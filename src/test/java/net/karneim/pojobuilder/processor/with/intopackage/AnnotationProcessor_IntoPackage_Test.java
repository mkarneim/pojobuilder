package net.karneim.pojobuilder.processor.with.intopackage;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.processor.with.intopackage.builder.SampleBean3Builder;
import org.junit.Test;

import static net.karneim.pojobuilder.PbAssertions.assertThat;
import static net.karneim.pojobuilder.testenv.JavaProject.Compilation;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_IntoPackage_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder is created into a specific package that been configured via @GeneratePojoBuildr
   * annotation
   */
  @Test
  public void testShouldGenerateBuilderIntoConfiguredPackage() {
    // Given:
    sourceFor(SampleBean3.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(SampleBean3Builder.class)
        .compiled(SampleBean3Builder.class)
        .reported(Compilation.Success);
  }

}
