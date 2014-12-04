package net.karneim.pojobuilder.processor.with.baseclass;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_Baseclass_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder is created with a simple base class that has been configured via @GeneratePojoBuilder
   * annotation
   */
  @Test
  public void testShouldGenerateBuilderWithSimpleBaseClass() throws Exception {
    // Given:
    sourceFor(Pojo1.class);
    sourceFor(SimpleBaseBuilder.class);
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
   * @scenario the generated builder should place an @Override annotation onto the build()-method if one of it's super
   * types declares one.
   */
  @Test
  public void testShouldGenerateBuilderWithBaseClassThatDeclaresGenericBuildMethod() throws Exception {
    // Given:
    sourceFor(Pojo2.class);
    sourceFor(BaseBuilderWithGenericBuildMethod.class);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .has(generatedSameSourceAs(Pojo2Builder.class))
        .has(compiled(Pojo2Builder.class));
    assertThat(success).isTrue();
  }

  /**
   * @throws Exception
   * @scenario the generated builder should place an @Override annotation onto the build()-method if one of it's super
   * types declares one.
   */
  @Test
  public void testShouldGenerateBuilderWithBaseClassThatDeclaresRawBuildMethod() throws Exception {
    // Given:
    sourceFor(Pojo3.class);
    sourceFor(BaseBuilderWithRawBuildMethod.class);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .has(generatedSameSourceAs(Pojo3Builder.class))
        .has(compiled(Pojo3Builder.class));
    assertThat(success).isTrue();
  }
}
