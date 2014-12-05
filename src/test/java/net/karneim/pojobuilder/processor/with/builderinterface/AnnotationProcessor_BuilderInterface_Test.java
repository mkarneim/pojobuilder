package net.karneim.pojobuilder.processor.with.builderinterface;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.testenv.JavaProject.Compilation;
import org.junit.Test;

import static net.karneim.pojobuilder.PbAssertions.assertThat;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_BuilderInterface_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario Generates a builder implementing the specified builder interface. Generates a extra
   * with-method for each property using an appropriately parameterized builder interface.
   */
  @Test
  public void testShouldGenerateBuilderWithBuilderInterface() throws Exception {
    // Given:
    sourceFor(Pojo.class);
    sourceFor(Builder.class);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(PojoBuilder.class)
        .compiled(PojoBuilder.class)
        .reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario Annotated factory method. Generates a builder implementing the specified builder
   * interface. Generates a extra with-method for each property using an appropriately
   * parameterized builder interface.
   */
  @Test
  public void testShouldGenerateBuilderWithBuilderInterfaceFromFactoryMethod() throws Exception {
    // Given:
    sourceFor(PojoFactory.class, null);
    sourceFor(Builder.class);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(AnotherPojoBuilder.class)
        .compiled(AnotherPojoBuilder.class)
        .reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario Generates a generic builder implementing the specified builder interface. Generates a
   * extra with-method for each property using an appropriately parameterized builder
   * interface.
   */
  @Test
  public void testShouldGenerateGenericBuilderWithBuilderInterface() throws Exception {
    // Given:
    sourceFor(GenericPojo.class);
    sourceFor(Builder.class);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(GenericPojoBuilder.class)
        .compiled(GenericPojoBuilder.class)
        .reported(Compilation.Success);
  }
}
