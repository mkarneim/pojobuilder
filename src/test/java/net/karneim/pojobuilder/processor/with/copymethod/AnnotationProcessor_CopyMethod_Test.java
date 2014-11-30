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
    String builderClassname = PojoBuilder.class.getName();

    // When:
    boolean success = prj.compile();

    // Then:
    String actual = getContent(prj.findGeneratedSource(builderClassname));
    logDebug(actual);
    assertThat(success).isTrue();

    String expected = loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname));
    assertThat(actual).isEqualTo(expected);
    assertThat(prj.findClass(builderClassname)).isNotNull();
  }

  /**
   * @throws Exception
   * @scenario the builder is created with a copy method
   */
  @Test
  public void testShouldGenerateAddressBuilderWithCopyMethod() throws Exception {
    // Given:
    sourceFor(Address.class);
    String builderClassname = AddressBuilder.class.getName();

    // When:
    boolean success = prj.compile();

    // Then:
    String actual = getContent(prj.findGeneratedSource(builderClassname));
    logDebug(actual);
    assertThat(success).isTrue();

    String expected = loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname));
    assertThat(actual).isEqualTo(expected);
    assertThat(prj.findClass(builderClassname)).isNotNull();
  }

  /**
   * @throws Exception
   * @scenario the builder is created with a copy method
   */
  @Test
  public void testShouldGeneratePojoBuilderButSkipCopyMethod() throws Exception {
    // Given:
    sourceFor(Pojo2.class);
    String builderClassname = "net.karneim.pojobuilder.processor.with.copymethod.Pojo2Builder";

    // When:
    boolean success = prj.compile();

    // Then:
    String actual = getContent(prj.findGeneratedSource(builderClassname));
    logDebug(actual);
    assertThat(success).isTrue();

    String expected = loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname));
    assertThat(actual).isEqualTo(expected);
    assertThat(prj.findClass(builderClassname)).isNotNull();
  }

}
