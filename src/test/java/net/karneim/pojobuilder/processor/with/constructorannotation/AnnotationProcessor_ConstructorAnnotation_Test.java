package net.karneim.pojobuilder.processor.with.constructorannotation;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_ConstructorAnnotation_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder is created for a pojo annotated with @GeneratePojoBuilder on constructor-level
   */
  @Test
  public void testShouldGenerateBuilderForAnnotatedClassOnConstructorLevel() throws Exception {
    // Given:
    sourceFor(Pojo1.class);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .has(generatedSameSourceAs(Pojo1Builder.class))
        .has(compiled(Pojo1Builder.class));
    assertThat(success).isTrue();
  }

  /**
   * @throws Exception
   * @scenario the builder is created for a pojo annotated with @GeneratePojoBuilder and @ConstructorProperties on
   * constructor-level
   */
  @Test
  public void testShouldGenerateBuilderForAnnotatedClassOnConstructorLevelWithConstructorPropertiesAnno()
      throws Exception {
    // Given:
    sourceFor(Pojo2.class);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .has(generatedSameSourceAs(Pojo2Builder.class))
        .has(compiled(Pojo2Builder.class));
    assertThat(success).isTrue();
  }

}
