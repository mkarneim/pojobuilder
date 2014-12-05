package net.karneim.pojobuilder.processor.with.validator;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static net.karneim.pojobuilder.PbAssertions.assertThat;
import static net.karneim.pojobuilder.testenv.JavaProject.Compilation;

/**
 * @feature We can configure the {@link GeneratePojoBuilder} annotation to generate a call to a
 * validator (that must be implemented manually be the user).
 * @see GeneratePojoBuilder#withValidator()
 */
public class AnnotationProcessor_Validator_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario a validator class that a matching 'validate' method is configurd
   */
  @Test
  public void validatorWithMatchingValidateMethod() {
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
   * @scenario a validator class that no 'validate' method is configurd
   */
  @Test
  public void validatorWithoutValidateMethod() {
    // Given:
    sourceFor(Pojo2.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .reported(Compilation.Failure);
  }

}
