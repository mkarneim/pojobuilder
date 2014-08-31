package net.karneim.pojobuilder.processor.with.builderinterface;

import static org.assertj.core.api.Assertions.assertThat;
import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.testenv.JavaProject;
import net.karneim.pojobuilder.testenv.TestBase;
import net.karneim.pojobuilder.testenv.Util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_BuilderInterface_Test extends TestBase {

  private JavaProject prj = new JavaProject(Util.createTempDir());

  @Before
  public void setupJavaProject() {
    // Enable the AnnotationProcessor
    prj.getProcessorClasses().add(AnnotationProcessor.class);
  }

  @After
  public void tearDownJavaProject() {
    prj.delete();
  }

  /**
   * @scenario Generates a builder implementing the specified builder interface. Generates a extra
   *           with-method for each property using an appropriately parameterized builder interface.
   * 
   * @throws Exception
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
   * @scenario Annotated factory method. Generates a builder implementing the specified builder
   *           interface. Generates a extra with-method for each property using an appropriately
   *           parameterized builder interface.
   * @throws Exception
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
   * @scenario Generates a generic builder implementing the specified builder interface. Generates a
   *           extra with-method for each property using an appropriately parameterized builder
   *           interface.
   * 
   * @throws Exception
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
