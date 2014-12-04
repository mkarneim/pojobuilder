package net.karneim.pojobuilder.processor.with.staticfactorymethod;

import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static net.karneim.pojobuilder.PbAssertions.assertThat;

/**
 * @feature The {@link net.karneim.pojobuilder.processor.AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_StaticFactoryMethod_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder is created with a factory method
   */
  @Test
  public void testShouldGenerateFactoryMethod() throws Exception {
    // Given:
    sourceFor(Trouble.class);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(TroubleBuilder.class)
        .compiled(TroubleBuilder.class);
    assertThat(success).isTrue();
  }

  /**
   * @throws Exception
   * @scenario the manual builder is created with a factory method
   */
  @Test
  public void testShouldGenerateFactoryMethodOnManualClass() throws Exception {
    // Given:
    sourceFor(Strife.class);
    String abstractBuilderClassname = "net.karneim.pojobuilder.processor.with.staticfactorymethod.AbstractStrifeBuilder";
    String manualBuilderClassname = "net.karneim.pojobuilder.processor.with.staticfactorymethod.StrifeBuilder";

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
