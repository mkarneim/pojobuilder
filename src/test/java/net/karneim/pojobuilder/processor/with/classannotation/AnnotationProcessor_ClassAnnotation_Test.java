package net.karneim.pojobuilder.processor.with.classannotation;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.testenv.JavaProject.Compilation;
import org.junit.Test;

import static net.karneim.pojobuilder.PbAssertions.assertThat;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_ClassAnnotation_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder is created for a pojo annotated with @GeneratePojoBuilder on class-level
   */
  @Test
  public void testShouldGenerateBuilderForAnnotatedClassOnCLassLevel() throws Exception {
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

}
