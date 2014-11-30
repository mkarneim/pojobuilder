package net.karneim.pojobuilder.processor.with.builderinterface;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_BuilderInterface_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario Generates a builder implementing the specified builder interface. Generates a extra
   * with-method for each property using an appropriately parameterized builder interface.
   */
  @Test
  public void testShouldGenerateBuilderWithBuilderInterface() throws Exception {
    // Given:
    sourceFor(Pojo.class);
    sourceFor(Builder.class);
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
   * @scenario Annotated factory method. Generates a builder implementing the specified builder
   * interface. Generates a extra with-method for each property using an appropriately
   * parameterized builder interface.
   */
  @Test
  public void testShouldGenerateBuilderWithBuilderInterfaceFromFactoryMethod() throws Exception {
    // Given:
    sourceFor(PojoFactory.class,null);
    sourceFor(Builder.class);
    String builderClassname = AnotherPojoBuilder.class.getName();

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
   * @scenario Generates a generic builder implementing the specified builder interface. Generates a
   * extra with-method for each property using an appropriately parameterized builder
   * interface.
   */
  @Test
  public void testShouldGenerateGenericBuilderWithBuilderInterface() throws Exception {
    // Given:
    sourceFor(GenericPojo.class);
    sourceFor(Builder.class);
    String builderClassname = GenericPojoBuilder.class.getName();

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
