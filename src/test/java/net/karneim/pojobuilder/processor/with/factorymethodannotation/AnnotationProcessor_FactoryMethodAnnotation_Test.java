package net.karneim.pojobuilder.processor.with.factorymethodannotation;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static net.karneim.pojobuilder.PbAssertions.assertThat;

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
        .generatedSameSourceAs(ProductBuilder.class)
        .compiled(ProductBuilder.class);
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
        .generatedSameSourceAs(ResourceBuilder.class)
        .compiled(ResourceBuilder.class);
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
        .generatedSameSourceAs(ContainerBuilder.class)
        .compiled(ContainerBuilder.class)
        .generatedSameSourceAs(FileContainerBuilder.class)
        .compiled(FileContainerBuilder.class)
        .generatedSameSourceAs(GenericListContainerBuilder.class)
        .compiled(GenericListContainerBuilder.class);
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
        .generatedSameSourceAs(PairBuilder.class)
        .compiled(PairBuilder.class)
        .generatedSameSourceAs(StringPairBuilder.class)
        .compiled(StringPairBuilder.class)
        .generatedSameSourceAs(TPairBuilder.class)
        .compiled(TPairBuilder.class);
    assertThat(success).isTrue();
  }

}
