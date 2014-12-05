package net.karneim.pojobuilder.processor.with.settername;

import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static net.karneim.pojobuilder.PbAssertions.assertThat;
import static net.karneim.pojobuilder.testenv.JavaProject.Compilation;


/**
 * @feature The name pattern of the generated with-methods can be configured.
 */
public class AnnotationProcessor_WithMethodNamePattern_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the name pattern is "set*"
   */
  @Test
  public void testPatternIsSetAsterisk()  {
    // Given:
    sourceFor(Pojo.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(PojoBuilder.class)
        .compiled(PojoBuilder.class)
        .reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the name pattern is "*"
   */
  @Test
  public void testPatternIsAsterisk() {
    // Given:
    sourceFor(Pojo2.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(Pojo2Builder.class)
        .compiled(Pojo2Builder.class)
        .reported(Compilation.Success);
  }
}
