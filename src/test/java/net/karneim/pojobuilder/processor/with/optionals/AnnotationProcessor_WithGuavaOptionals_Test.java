package net.karneim.pojobuilder.processor.with.optionals;

import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static net.karneim.pojobuilder.PbAssertions.assertThat;
import static net.karneim.pojobuilder.testenv.JavaProject.Compilation;

/**
 * @feature The {@link net.karneim.pojobuilder.processor.AnnotationProcessor} generates builder
 * classes.
 */
public class AnnotationProcessor_WithGuavaOptionals_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder contains withParam(Optional&lt;X&gt;) methods
   */
  @Test
  public void testShouldGenerateGuavaOptionalsForObjectTypes() {
    // Given:
    sourceFor(PojoWithGuavaOptional.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(PojoWithGuavaOptionalBuilder.class)
        .compiled(PojoWithGuavaOptionalBuilder.class)
        .reported(Compilation.Success);
  }


  /**
   * @throws Exception
   * @scenario the builder withParam(Optional&lt;X&gt;) methods should not generated if the member
   * is already an Optional
   */
  @Test
  public void testShouldNotGenerateGuavaOptionalsForOptionalMembers() {
    // Given:
    sourceFor(PojoWithGuavaOptional2.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(PojoWithGuavaOptional2Builder.class)
        .compiled(PojoWithGuavaOptional2Builder.class)
        .reported(Compilation.Success);
  }

}
