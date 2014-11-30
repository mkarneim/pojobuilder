package net.karneim.pojobuilder.processor.with.validator;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @feature We can configure the {@link GeneratePojoBuilder} annotation to generate a call to a
 * validator (that must be implemented manually be the user).
 * @see GeneratePojoBuilder#withValidator()
 */
public class AnnotationProcessor_Validator_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario a validator class that has a matching 'validate' method is configured
   */
  @Test
  public void validatorWithMatchingValidateMethod() throws Exception {
    // Given:
    sourceFor(Pojo.class);
    String builderClassname = PojoBuilder.class.getName();

    // When:
    boolean success = prj.compile();

    // Then:
    String actual = getContent(prj.findGeneratedSource(builderClassname));
    logDebug(actual);
    assertThat(success).isTrue();

    String expected =
        loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname));
    assertThat(actual).isEqualTo(expected);
    assertThat(prj.findClass(builderClassname)).isNotNull();
  }

  /**
   * @throws Exception
   * @scenario a validator class that has no 'validate' method is configured
   */
  @Test
  public void validatorWithoutValidateMethod() throws Exception {
    // Given:
    sourceFor(Pojo2.class);

    // When:
    boolean success = prj.compile();

    // Then:
    assertThat(success).isFalse();
  }

}
