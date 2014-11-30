package net.karneim.pojobuilder.processor.with.intopackage;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.processor.with.intopackage.builder.SampleBean3Builder;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_IntoPackage_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder is created into a specific package that has been configured via @GeneratePojoBuilder
   * annotation
   */
  @Test
  public void testShouldGenerateBuilderIntoConfiguredPackage() throws Exception {
    // Given:
    sourceFor(SampleBean3.class);
    String builderClassname = SampleBean3Builder.class.getName();

    // When:
    boolean success = prj.compile();

    // Then:
    assertThat(success).isTrue();
    String actual = getContent(prj.findGeneratedSource(builderClassname));
    logDebug(actual);

    String expected = loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname));
    assertThat(actual).isEqualTo(expected);
    assertThat(prj.findClass(builderClassname)).isNotNull();
  }

}
