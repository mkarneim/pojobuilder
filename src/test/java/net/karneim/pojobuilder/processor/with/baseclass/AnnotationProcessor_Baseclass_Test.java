package net.karneim.pojobuilder.processor.with.baseclass;

import static net.karneim.pojobuilder.PbAssertions.assertThat;

import org.junit.Test;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.testenv.JavaProject.Compilation;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_Baseclass_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder is created with a simple base class that has been configured
   *           via @GeneratePojoBuilder annotation
   */
  @Test
  public void testShouldGenerateBuilderWithSimpleBaseClass() {
    // Given:
    sourceFor(Pojo1.class, SimpleBaseBuilder.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(Pojo1Builder.class).compiled(Pojo1Builder.class)
        .reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the generated builder should place an @Override annotation onto the build()-method if
   *           one of it's super types declares one.
   */
  @Test
  public void testShouldGenerateBuilderWithBaseClassThatDeclaresGenericBuildMethod() {
    // Given:
    sourceFor(Pojo2.class, BaseBuilderWithGenericBuildMethod.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(Pojo2Builder.class).compiled(Pojo2Builder.class)
        .reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the generated builder should place an @Override annotation onto the build()-method if
   *           one of it's super types declares one.
   */
  @Test
  public void testShouldGenerateBuilderWithBaseClassThatDeclaresRawBuildMethod() {
    // Given:
    sourceFor(Pojo3.class, BaseBuilderWithRawBuildMethod.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(Pojo3Builder.class).compiled(Pojo3Builder.class)
        .reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder is created with a base class that has a clone method and that has been
   *           configured via @GeneratePojoBuilder annotation
   */
  @Test
  public void testShouldGenerateBuilderWithBaseClassHavingACloneMethod() {
    // Given:
    sourceFor(Pojo4.class, BaseBuilderWithCloneMethod.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(Pojo4Builder.class).compiled(Pojo4Builder.class)
        .reported(Compilation.Success);
  }
}
