package net.karneim.pojobuilder.processor.with.utf8;

import static net.karneim.pojobuilder.PbAssertions.assertThat;

import org.junit.Test;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.testenv.JavaProject.Compilation;

/**
 * @feature The {@link AnnotationProcessor} generates builder class for class using utf8 characters in identifiers.
 */
public class AnnotationProcessor_Utf8_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the pojo has a classname with german umlaut and a property with german umlaut
   */
  @Test
  public void testShouldGenerateBuilderHavingUmlautCharacters() {
    // Given:
    sourceFor(PojoWithUmlaut.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(PojoWithUmlautBuilder.class)
        .compiled(PojoWithUmlautBuilder.class)
        .reported(Compilation.Success);
  }
}
