package net.karneim.pojobuilder.processor.with.factorymethodannotation;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static net.karneim.pojobuilder.PbAssertions.assertThat;
import static net.karneim.pojobuilder.testenv.JavaProject.Compilation;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_FactoryMethodAnnotation_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder is created for a pojo (having no annotation) using som factory method annotated with @GeneratePojoBuilder
   */
  @Test
  public void testShouldGenerateBuilderForAnnotatedFactoryMethod() {
    // Given:
    sourceForFactoryMethod(ProductFactory.class, null);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(ProductBuilder.class)
        .compiled(ProductBuilder.class)
        .reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder is created for an interface using some annotated factory method.
   */
  @Test
  public void testShouldGenerateBuilderForAnInterfaceUsingAnnotatedFactoryMethod() {
    // Given:
    sourceForFactoryMethod(ResourceFactory.class, null);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(ResourceBuilder.class)
        .compiled(ResourceBuilder.class)
        .reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder is created for an parameterized interface using some annotated factory method.
   */
  @Test
  public void testShouldGenerateBuilderForAParameterizedInterfaceUsingAnnotatedFactoryMethod() {
    // Given:
    sourceForFactoryMethod(ContainerFactory.class, null);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(ContainerBuilder.class)
        .compiled(ContainerBuilder.class)
        .generatedSameSourceAs(FileContainerBuilder.class)
        .compiled(FileContainerBuilder.class)
        .generatedSameSourceAs(GenericListContainerBuilder.class)
        .compiled(GenericListContainerBuilder.class)
        .reported(Compilation.Success);
  }

  @Test
  public void testPairFactory() {
    // Given:
    sourceForFactoryMethod(PairFactory.class, null);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(PairBuilder.class)
        .compiled(PairBuilder.class)
        .generatedSameSourceAs(StringPairBuilder.class)
        .compiled(StringPairBuilder.class)
        .generatedSameSourceAs(TPairBuilder.class)
        .compiled(TPairBuilder.class)
        .reported(Compilation.Success);
  }

}
