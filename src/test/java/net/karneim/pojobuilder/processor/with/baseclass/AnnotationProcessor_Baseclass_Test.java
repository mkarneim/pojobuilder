package net.karneim.pojobuilder.processor.with.baseclass;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.testenv.JavaProject.Compilation;
import org.junit.Test;

import static net.karneim.pojobuilder.PbAssertions.assertThat;

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
    sourceFor(Pojo1.class,SimpleBaseBuilder.class);
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
   * @scenario the generated builder should place an @Override annotation onto the build()-method if one of it's super
   * types declares one.
   */
  @Test
  public void testShouldGenerateBuilderWithBaseClassThatDeclaresGenericBuildMethod() throws Exception {
    // Given:
    sourceFor(Pojo2.class,BaseBuilderWithGenericBuildMethod.class);
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
   * @scenario the generated builder should place an @Override annotation onto the build()-method if one of it's super
   * types declares one.
   */
  @Test
  public void testShouldGenerateBuilderWithBaseClassThatDeclaresRawBuildMethod() throws Exception {
    // Given:
    sourceFor(Pojo3.class,BaseBuilderWithRawBuildMethod.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(Pojo3Builder.class)
        .compiled(Pojo3Builder.class)
        .reported(Compilation.Success);
  }
}
