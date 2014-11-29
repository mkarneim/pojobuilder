package net.karneim.pojobuilder.processor.with.staticfactorymethod;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.copymethod.Address;
import net.karneim.pojobuilder.processor.with.copymethod.AddressBuilder;
import net.karneim.pojobuilder.processor.with.copymethod.Pojo;
import net.karneim.pojobuilder.processor.with.copymethod.PojoBuilder;
import net.karneim.pojobuilder.testenv.JavaProject;
import net.karneim.pojobuilder.testenv.TestBase;
import net.karneim.pojobuilder.testenv.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static net.karneim.pojobuilder.processor.with.staticfactorymethod.TroubleBuilder.trouble;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @feature The {@link net.karneim.pojobuilder.processor.AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_StaticFactoryMethod_Test extends TestBase {

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
   * @scenario the builder is created with a factory method
   * @throws Exception
   */
  @Test
  public void testShouldGenerateFactoryMethod() throws Exception {
    // Given:
    String pojoClassname = Trouble.class.getName();
    String builderClassname = TroubleBuilder.class.getName();
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojoClassname));

    // When:
    boolean success = prj.compile();

    // Then:
    String actual = getContent(prj.findGeneratedSource(builderClassname));
    logDebug(actual);
    assertThat(success).isTrue();

    String expected = loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname));
    assertThat(actual).isEqualTo(expected);
    assertThat(prj.findClass(builderClassname)).isNotNull();

    trouble().withA('T');
  }

  /**
   * @scenario the manual builder is created with a factory method
   * @throws Exception
   */
  @Test
  public void testShouldGenerateFactoryMethodOnManualClass() throws Exception {
    // Given:
    String pojoClassname = Strife.class.getName();
    String abstractBuilderClassname = "net.karneim.pojobuilder.processor.with.staticfactorymethod.AbstractStrifeBuilder";
    String manualBuilderClassname = "net.karneim.pojobuilder.processor.with.staticfactorymethod.StrifeBuilder";
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojoClassname));

    // When:
    boolean success = prj.compile();

    // Then:
    String actual1 = getContent(prj.findGeneratedSource(abstractBuilderClassname));
    logDebug(actual1);
    String actual2 = getContent(prj.findGeneratedSource(manualBuilderClassname));
    logDebug(actual2);
    assertThat(success).isTrue();

    String expected1 = loadResourceFromClasspath("AbstractStrifeBuilder.expected.txt");
    assertThat(actual1).isEqualTo(expected1);
    assertThat(prj.findClass(abstractBuilderClassname)).isNotNull();

    String expected2 = loadResourceFromClasspath("StrifeBuilder.expected.txt");
    assertThat(actual2).isEqualTo(expected2);
    assertThat(prj.findClass(manualBuilderClassname)).isNotNull();
  }

}
