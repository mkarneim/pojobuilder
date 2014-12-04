package net.karneim.pojobuilder.processor.with.factorymethodannotation;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_FactoryMethodAnnotation_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder is created for a pojo (having no annotation) using som factory method annotated with @GeneratePojoBuilder
   */
  @Test
  public void testShouldGenerateBuilderForAnnotatedFactoryMethod() throws Exception {
    // Given:
    sourceFor( ProductFactory.class, null);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .has(generatedSameSourceAs(ProductBuilder.class))
        .has(compiled(ProductBuilder.class));
    assertThat(success).isTrue();
  }

  /**
   * @throws Exception
   * @scenario the builder is created for an interface using some annotated factory method.
   */
  @Test
  public void testShouldGenerateBuilderForAnInterfaceUsingAnnotatedFactoryMethod() throws Exception {
    // Given:
    sourceFor( ResourceFactory.class, null);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .has(generatedSameSourceAs(ResourceBuilder.class))
        .has(compiled(ResourceBuilder.class));
    assertThat(success).isTrue();
  }

  /**
   * @throws Exception
   * @scenario the builder is created for an parameterized interface using some annotated factory method.
   */
  @Test
  public void testShouldGenerateBuilderForAParameterizedInterfaceUsingAnnotatedFactoryMethod() throws Exception {
    // Given:
    sourceFor(ContainerFactory.class, null);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .has(generatedSameSourceAs(ContainerBuilder.class))
        .has(compiled(ContainerBuilder.class))
        .has(generatedSameSourceAs(FileContainerBuilder.class))
        .has(compiled(FileContainerBuilder.class))
        .has(generatedSameSourceAs(GenericListContainerBuilder.class))
        .has(compiled(GenericListContainerBuilder.class));
    assertThat(success).isTrue();
  }

  @Test
  public void testPairFactory() throws Exception {
    // Given:
    sourceFor(PairFactory.class,null);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .has(generatedSameSourceAs(PairBuilder.class))
        .has(compiled(PairBuilder.class))
        .has(generatedSameSourceAs(StringPairBuilder.class))
        .has(compiled(StringPairBuilder.class))
        .has(generatedSameSourceAs(TPairBuilder.class))
        .has(compiled(TPairBuilder.class));
    assertThat(success).isTrue();
  }

}
