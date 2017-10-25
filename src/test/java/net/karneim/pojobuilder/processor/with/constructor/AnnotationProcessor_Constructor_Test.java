package net.karneim.pojobuilder.processor.with.constructor;

import static net.karneim.pojobuilder.PbAssertions.assertThat;

import org.junit.Test;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.testenv.JavaProject.Compilation;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes with different constructor visibilities.
 */
public class AnnotationProcessor_Constructor_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder is created with a public constructor
   */
  @Test
  public void testShouldGenerateBuilderWithPublicConstructor() {
    // Given:
    sourceFor(SampleAnnotatedWithPublicConstructor.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(SampleAnnotatedWithPublicConstructorBuilder.class)
        .compiled(SampleAnnotatedWithPublicConstructor.class).reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder is created with a protected constructor
   */
  @Test
  public void testShouldGenerateBuilderWithProtectedConstructor() {
    // Given:
    sourceFor(SampleAnnotatedWithProtectedConstructor.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(SampleAnnotatedWithProtectedConstructorBuilder.class)
        .compiled(SampleAnnotatedWithProtectedConstructorBuilder.class).reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder is created with a private constructor
   */
  @Test
  public void testShouldGenerateBuilderWithPrivateConstructor() {
    // Given:
    sourceFor(SampleAnnotatedWithPrivateConstructor.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(SampleAnnotatedWithPrivateConstructorBuilder.class)
        .compiled(SampleAnnotatedWithPrivateConstructorBuilder.class).reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder is created with a package-private constructor
   */
  @Test
  public void testShouldGenerateBuilderWithPackagePrivateConstructor() {
    // Given:
    sourceFor(SampleAnnotatedWithPackagePrivateConstructor.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(SampleAnnotatedWithPackagePrivateConstructorBuilder.class)
        .compiled(SampleAnnotatedWithPackagePrivateConstructorBuilder.class).reported(Compilation.Success);
  }

}
