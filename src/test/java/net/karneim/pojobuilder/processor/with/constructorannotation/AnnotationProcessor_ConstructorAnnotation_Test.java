package net.karneim.pojobuilder.processor.with.constructorannotation;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.testenv.JavaProject.Compilation;
import org.junit.Test;

import static net.karneim.pojobuilder.PbAssertions.assertThat;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_ConstructorAnnotation_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder is created for a pojo annotated with @GeneratePojoBuilder on constructor-level
   */
  @Test
  public void testShouldGenerateBuilderForAnnotatedClassOnConstructorLevel() {
    // Given:
    sourceFor(Pojo1.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(Pojo1Builder.class)
        .compiled(Pojo1Builder.class)
        .reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder is created for a pojo annotated with @GeneratePojoBuilder and @ConstructorProperties on
   * constructor-level
   */
  @Test
  public void testShouldGenerateBuilderForAnnotatedClassOnConstructorLevelWithConstructorPropertiesAnno()
      {
    // Given:
    sourceFor(Pojo2.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(Pojo2Builder.class)
        .compiled(Pojo2Builder.class)
        .reported(Compilation.Success);
  }

}
