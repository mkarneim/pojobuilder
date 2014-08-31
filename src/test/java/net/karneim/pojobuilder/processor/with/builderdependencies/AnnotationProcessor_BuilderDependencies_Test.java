package net.karneim.pojobuilder.processor.with.builderdependencies;

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
public class AnnotationProcessor_BuilderDependencies_Test extends TestBase {

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
   * @scenario A pojo has a dependency on a not-yet-generated builder.
   * @throws Exception
   */
  @Test
  public void testShouldGenerateBuilderForPojoWithBuilderDependencies() throws Exception {
    // Given:
    String pojoEClassname = PojoE.class.getName();
    String pojoFClassname = "net.karneim.pojobuilder.processor.with.builderdependencies.PojoF";// PojoF.class.getName();
    String factoryFClassname = "net.karneim.pojobuilder.processor.with.builderdependencies.PojoFFactory";// PojoFFactory.class.getName();
    String builderFClassname = "net.karneim.pojobuilder.processor.with.builderdependencies.PojoFBuilder";// PojoFBuilder.class.getName();
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojoEClassname));
    prj.addSourceFile(pojoFClassname, loadResourceFromClasspath("PojoF.java.txt"));
    prj.addSourceFile(factoryFClassname, loadResourceFromClasspath("PojoFFactory.java.txt"));

    // When:
    boolean success = prj.compile();

    // Then:
    String actualF = getContent(prj.findGeneratedSource(builderFClassname));
    logDebug(actualF);
    assertThat(success).isTrue();

    String expectedF = loadResourceFromClasspath("PojoFBuilder.java.txt");
    assertThat(actualF).isEqualTo(expectedF);
    assertThat(prj.findClass(builderFClassname)).isNotNull();
  }
}
