package net.karneim.pojobuilder.processor.with.copymethod;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_CopyMethod_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder is created with a copy method
   */
  @Test
  public void testShouldGeneratePojoBuilderWithCopyMethod() throws Exception {
    // Given:
    sourceFor(Pojo.class);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .has(generatedSameSourceAs(PojoBuilder.class))
        .has(compiled(PojoBuilder.class));
    assertThat(success).isTrue();
  }

  /**
   * @throws Exception
   * @scenario the builder is created with a copy method
   */
  @Test
  public void testShouldGenerateAddressBuilderWithCopyMethod() throws Exception {
    // Given:
    sourceFor(Address.class);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .has(generatedSameSourceAs(AddressBuilder.class))
        .has(compiled(AddressBuilder.class));
    assertThat(success).isTrue();
  }

  /**
   * @throws Exception
   * @scenario the builder is created with a copy method
   */
  @Test
  public void testShouldGeneratePojoBuilderButSkipCopyMethod() throws Exception {
    // Given:
    sourceFor(Pojo2.class);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .has(generatedSameSourceAs(Pojo2Builder.class))
        .has(compiled(Pojo2Builder.class));
    assertThat(success).isTrue();
  }

}
