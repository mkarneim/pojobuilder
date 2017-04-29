package net.karneim.pojobuilder.processor.with.builderinterface;

import static net.karneim.pojobuilder.PbAssertions.assertThat;

import org.junit.Test;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.testenv.JavaProject.Compilation;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_BuilderInterface_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario Generates a builder implementing the specified Builder interface. Generates a extra
   *           with-method for each property using an appropriately parameterized builder interface.
   */
  @Test
  public void testShouldGenerateBuilderWithBuilderInterface() {
    // Given:
    sourceFor(Pojo.class);
    sourceFor(Builder.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(PojoBuilder.class).compiled(PojoBuilder.class)
        .reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario Generates a builder implementing the specified Supplier interface. 
   */
  @Test
  public void testShouldGenerateBuilderWithSupplierInterface() {
    // Given:
    sourceFor(Pojo2.class);
    sourceFor(Supplier.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(Pojo2Builder.class).compiled(Pojo2Builder.class)
        .reported(Compilation.Success);
  }
  
  /**
   * @throws Exception
   * @scenario Generates a builder implementing the specified Supplier interface. Generates a extra
   *           with-method for each property using an appropriately parameterized builder interface.
   */
  @Test
  public void testShouldGenerateBuilderWithSupplierInterfaceAndBuilderProperties() {
    // Given:
    sourceFor(Pojo3.class);
    sourceFor(Supplier.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(Pojo3Builder.class).compiled(Pojo3Builder.class)
        .reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario Annotated factory method. Generates a builder implementing the specified builder
   *           interface. Generates a extra with-method for each property using an appropriately
   *           parameterized builder interface.
   */
  @Test
  public void testShouldGenerateBuilderWithBuilderInterfaceFromFactoryMethod() {
    // Given:
    sourceForFactoryMethod(PojoFactory.class, null);
    sourceFor(Builder.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(AnotherPojoBuilder.class)
        .compiled(AnotherPojoBuilder.class).reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario Generates a generic builder implementing the specified builder interface. Generates a
   *           extra with-method for each property using an appropriately parameterized builder
   *           interface.
   */
  @Test
  public void testShouldGenerateGenericBuilderWithBuilderInterface() {
    // Given:
    sourceFor(GenericPojo.class, Builder.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(GenericPojoBuilder.class)
        .compiled(GenericPojoBuilder.class).reported(Compilation.Success);
  }
}
