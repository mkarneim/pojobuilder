package net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.testenv.JavaProject.Compilation;
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
  public void testShouldGenerateBuilderWithSimpleBaseClass() {
    // Given:
    sourceFor(Pojo1.class,SimpleBaseBuilder.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs("net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap.AbstractPojo1Builder")
        .generatedSameSourceAs("net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap.Pojo1Builder")
        .reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the abstract builder is created with a base class declaring generic build() method.
   */
  @Test
  public void testShouldGenerateBuilderWithBaseClassThatDeclaresGenericBuildMethod() {
    // Given:
    sourceFor(Pojo2.class,BaseBuilderWithGenericBuildMethod.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs("net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap.AbstractPojo2Builder")
        .generatedSameSourceAs("net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap.Pojo2Builder")
        .reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the abstract builder is created with a base class declaring a raw build() method.
   */
  @Test
  public void testShouldGenerateBuilderWithBaseClassThatDeclaresRawBuildMethod() {
    // Given:
    sourceFor(Pojo3.class);
    sourceFor(BaseBuilderWithRawBuildMethod.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs("net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap.AbstractPojo3Builder")
        .generatedSameSourceAs("net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap.Pojo3Builder")
        .reported(Compilation.Success);
  }
}
