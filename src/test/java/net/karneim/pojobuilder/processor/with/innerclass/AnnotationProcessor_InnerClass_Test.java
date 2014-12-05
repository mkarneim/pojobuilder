package net.karneim.pojobuilder.processor.with.innerclass;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static net.karneim.pojobuilder.PbAssertions.assertThat;
import static net.karneim.pojobuilder.testenv.JavaProject.Compilation;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_InnerClass_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder is created for a inner class.
   */
  @Test
  public void testShouldGenerateBuilderForInnerClass() throws Exception {
    // Given:
    sourceForFactoryMethod(PojoFactory.class, null);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(InnerPojoBuilder.class)
        .compiled(InnerPojoBuilder.class)
        .reported(Compilation.Success);
  }
}
