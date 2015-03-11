package net.karneim.pojobuilder.processor.with.innerclass;

import static net.karneim.pojobuilder.PbAssertions.assertThat;
import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.testenv.JavaProject.Compilation;

import org.junit.Test;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_InnerClass_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder is created for a inner class.
   */
  @Test
  public void testShouldGenerateBuilderForInnerClass() {
    // Given:
    sourceForFactoryMethod(PojoFactory.class, null);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(InnerPojoBuilder.class).compiled(InnerPojoBuilder.class)
        .reported(Compilation.Success);
  }
}
