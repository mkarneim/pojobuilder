package net.karneim.pojobuilder.processor.with.optionals;

import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
  public void testShouldGenerateGuavaOptionalsForObjectTypes() throws Exception {
    // Given:
    sourceFor(PojoWithGuavaOptional.class);
    String builderClassname = PojoWithGuavaOptionalBuilder.class.getName();

    // When:
    boolean success = prj.compile();

    // Then:
    assertThat(success).isTrue();
    String actual = getContent(prj.findGeneratedSource(builderClassname));
    logDebug(actual);

    String expected =
        loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname));
    assertThat(actual).isEqualTo(expected);
    assertThat(prj.findClass(builderClassname)).isNotNull();
  }

  /**
   * @throws Exception
   * @scenario the builder withParam(Optional&lt;X&gt;) methods should not generated if the member
   * is already an Optional
   */
  @Test
  public void testShouldNotGenerateGuavaOptionalsForOptionalMembers() throws Exception {
    // Given:
    sourceFor(PojoWithGuavaOptional2.class);
    String builderClassname = PojoWithGuavaOptional2Builder.class.getName();

    // When:
    boolean success = prj.compile();

    // Then:
    assertThat(success).isTrue();
    String actual = getContent(prj.findGeneratedSource(builderClassname));
    logDebug(actual);

    String expected =
        loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname));
    assertThat(actual).isEqualTo(expected);
    assertThat(prj.findClass(builderClassname)).isNotNull();
  }

}
