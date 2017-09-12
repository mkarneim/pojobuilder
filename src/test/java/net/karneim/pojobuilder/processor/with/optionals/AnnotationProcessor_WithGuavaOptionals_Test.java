package net.karneim.pojobuilder.processor.with.optionals;

import static net.karneim.pojobuilder.PbAssertions.assertThat;

import org.junit.Test;

import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.testenv.JavaProject.Compilation;

/**
 * @feature The {@link net.karneim.pojobuilder.processor.AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_WithGuavaOptionals_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder contains withParam(Optional&lt;X&gt;) methods
   */
  @Test
  public void testShouldGenerateWithOptionalMethodsForBasicFieldAccess() {
    // Given:
    sourceFor(PojoWithGuavaOptionalBasicFieldAccess.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(PojoWithGuavaOptionalBasicFieldAccessBuilder.class)
        .compiled(PojoWithGuavaOptionalBasicFieldAccessBuilder.class).reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder withParam(Optional&lt;X&gt;) methods should not generated if the member is already an
   *           Optional
   */
  @Test
  public void testShouldGenerateWithOptionalMethodsForOptionalFieldAccess() {
    // Given:
    sourceFor(PojoWithGuavaOptionalOptionalFieldAccess.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(PojoWithGuavaOptionalOptionalFieldAccessBuilder.class)
        .compiled(PojoWithGuavaOptionalOptionalFieldAccessBuilder.class).reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder contains withParam(Optional&lt;X&gt;) methods
   */
  @Test
  public void testShouldGenerateWithOptionalMethodsForBasicSetters() {
    // Given:
    sourceFor(PojoWithGuavaOptionalBasicSetters.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(PojoWithGuavaOptionalBasicSettersBuilder.class)
        .compiled(PojoWithGuavaOptionalBasicSettersBuilder.class).reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder contains withParam(Optional&lt;X&gt;) methods
   */
  @Test
  public void testShouldGenerateWithOptionalMethodsForOptionalSetters() {
    // Given:
    sourceFor(PojoWithGuavaOptionalOptionalSetters.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(PojoWithGuavaOptionalOptionalSettersBuilder.class)
        .compiled(PojoWithGuavaOptionalOptionalSettersBuilder.class).reported(Compilation.Success);
  }

}
