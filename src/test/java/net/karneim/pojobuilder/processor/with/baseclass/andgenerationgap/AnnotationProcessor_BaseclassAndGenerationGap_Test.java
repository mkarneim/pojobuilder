package net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static net.karneim.pojobuilder.PbAssertions.assertThat;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_BaseclassAndGenerationGap_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the abstract builder is created with a simple base class.
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
        .generatedSameSourceAs("net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap.AbstractPojo1Builder")
        .generatedSameSourceAs("net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap.Pojo1Builder");
    assertThat(success).isTrue();
  }

  /**
   * @throws Exception
   * @scenario the abstract builder is created with a base class declaring generic build() method.
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
        .generatedSameSourceAs("net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap.AbstractPojo2Builder")
        .generatedSameSourceAs("net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap.Pojo2Builder");
    assertThat(success).isTrue();
  }

  /**
   * @throws Exception
   * @scenario the abstract builder is created with a base class declaring a raw build() method.
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
        .generatedSameSourceAs("net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap.AbstractPojo3Builder")
        .generatedSameSourceAs("net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap.Pojo3Builder");
    assertThat(success).isTrue();
  }
}
