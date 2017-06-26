package net.karneim.pojobuilder.processor.with.classannotation;

import static net.karneim.pojobuilder.PbAssertions.assertThat;

import org.junit.Test;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.testenv.JavaProject.Compilation;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_ClassAnnotation_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder is created for a pojo annotated with @GeneratePojoBuilder on class-level
   */
  @Test
  public void testShouldGenerateBuilderForAnnotatedClassOnCLassLevel() {
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
   * @scenario the builder is created for a pojo annotated with @GeneratePojoBuilder on class-level
   */
  @Test
  public void testShouldGenerateBuilderForAnnotatedClassOnCLassLevel_even_when_there_are_multiple_constructors() {
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

  /**
   * @throws Exception
   * @scenario the builder is created for a pojo annotated with @GeneratePojoBuilder on class-level
   */
  @Test
  public void testShouldGenerateBuilderForAnnotatedClassOnCLassLevel_even_when_there_is_a_single_constructor_having_parameters() {
    // Given:
    sourceFor(Pojo3.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(Pojo3Builder.class)
        .compiled(Pojo3Builder.class)
        .reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder is created for a pojo annotated with @GeneratePojoBuilder on class-level
   */
  @Test
  public void testShouldGenerateBuilderForAnnotatedClassOnCLassLevel_when_there_is_a_single_parameterless_constructor() {
    // Given:
    sourceFor(Pojo4.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(Pojo4Builder.class)
        .compiled(Pojo4Builder.class)
        .reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder is created for a pojo annotated with @GeneratePojoBuilder on class-level
   */
  @Test
  public void testShouldGenerateBuilderForAnnotatedClassOnCLassLevel_when_there_is_a_single_accessible_constructor() {
    // Given:
    sourceFor(Pojo5.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(Pojo5Builder.class)
        .compiled(Pojo5Builder.class)
        .reported(Compilation.Success);
  }
}
