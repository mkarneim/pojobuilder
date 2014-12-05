package net.karneim.pojobuilder.processor.with.copymethod;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.testenv.JavaProject.Compilation;
import org.junit.Test;

import static net.karneim.pojobuilder.PbAssertions.assertThat;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_CopyMethod_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder is created with a copy method
   */
  @Test
  public void testShouldGeneratePojoBuilderWithCopyMethod() {
    // Given:
    sourceFor(Pojo.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(PojoBuilder.class)
        .compiled(PojoBuilder.class)
        .reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder is created with a copy method
   */
  @Test
  public void testShouldGenerateAddressBuilderWithCopyMethod() {
    // Given:
    sourceFor(Address.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(AddressBuilder.class)
        .compiled(AddressBuilder.class)
        .reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder is created with a copy method
   */
  @Test
  public void testShouldGeneratePojoBuilderButSkipCopyMethod() {
    // Given:
    sourceFor(Pojo2.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(Pojo2Builder.class)
        .compiled(Pojo2Builder.class)
        .reported(Compilation.Success);
  }

}
