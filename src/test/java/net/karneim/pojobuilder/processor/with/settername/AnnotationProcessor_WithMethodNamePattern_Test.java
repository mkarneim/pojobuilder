package net.karneim.pojobuilder.processor.with.settername;

import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @feature The name pattern of the generated with-methods can be configured.
 */
public class AnnotationProcessor_WithMethodNamePattern_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the name pattern is "set*"
   */
  @Test
  public void testPatternIsSetAsterisk() throws Exception {
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
   * @scenario the name pattern is "*"
   */
  @Test
  public void testPatternIsAsterisk() throws Exception {
    // Given:
    sourceFor(Pojo2.class);
    String builderClassname = Pojo2Builder.class.getName();

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
}
