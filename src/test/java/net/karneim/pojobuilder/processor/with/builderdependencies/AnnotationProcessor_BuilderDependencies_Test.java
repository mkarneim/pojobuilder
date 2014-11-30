package net.karneim.pojobuilder.processor.with.builderdependencies;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_BuilderDependencies_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario A pojo has a dependency on a not-yet-generated builder.
   */
  @Test
  public void testShouldGenerateBuilderForPojoWithBuilderDependencies() throws Exception {
    // Given:
    sourceFor(PojoE.class);
    String pojoFClassname = "net.karneim.pojobuilder.processor.with.builderdependencies.PojoF";// PojoF.class.getName();
    String factoryFClassname = "net.karneim.pojobuilder.processor.with.builderdependencies.PojoFFactory";// PojoFFactory.class.getName();
    String builderFClassname = "net.karneim.pojobuilder.processor.with.builderdependencies.PojoFBuilder";// PojoFBuilder.class.getName();
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
