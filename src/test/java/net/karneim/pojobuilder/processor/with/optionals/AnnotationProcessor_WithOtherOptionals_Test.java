package net.karneim.pojobuilder.processor.with.optionals;

import static net.karneim.pojobuilder.PbAssertions.assertThat;

import org.junit.Test;

import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.testenv.JavaProject.Compilation;

/**
 * @feature The {@link net.karneim.pojobuilder.processor.AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_WithOtherOptionals_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder contains withParam(Optional&lt;X&gt;) methods
   */
  @Test
  public void testShouldGenerateWithOptionalMethodsForBasicFieldAccess() {
    // Given:
    sourceFor(PojoWithOtherOptionalBasicFieldAccess.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(PojoWithOtherOptionalBasicFieldAccessBuilder.class)
        .compiled(PojoWithOtherOptionalBasicFieldAccessBuilder.class).reported(Compilation.Success);
  }

}
