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
    String pojoClassname = Pojo.class.getName();
    String builderInterfaceClassname = Builder.class.getName();
    String builderClassname = PojoBuilder.class.getName();
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojoClassname));
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, builderInterfaceClassname));

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
    String pojoClassname = Pojo.class.getName();
    String factoryClassname = PojoFactory.class.getName();
    String builderInterfaceClassname = Builder.class.getName();
    String builderClassname = AnotherPojoBuilder.class.getName();
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojoClassname));
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, factoryClassname));
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, builderInterfaceClassname));

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
    String pojoClassname = GenericPojo.class.getName();
    String builderInterfaceClassname = Builder.class.getName();
    String builderClassname = GenericPojoBuilder.class.getName();
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojoClassname));
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, builderInterfaceClassname));

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
