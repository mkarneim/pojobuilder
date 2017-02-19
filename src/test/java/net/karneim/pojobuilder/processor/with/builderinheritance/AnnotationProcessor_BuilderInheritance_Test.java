package net.karneim.pojobuilder.processor.with.builderinheritance;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.testenv.JavaProject.Compilation;
import org.junit.Test;

import static net.karneim.pojobuilder.PbAssertions.assertThat;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_BuilderInheritance_Test extends ProcessorTestSupport {


  /**
   * @scenario builder for an abstract pojo is itself abstract
   */
  @Test
  public void testAbstractPojoGeneratesAbstractBuilder() {
    // Given:
    sourceFor(AbstractPojo.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(AbstractPojoBuilder.class)
        .compiled(AbstractPojoBuilder.class)
        .reported(Compilation.Success);
  }

  /**
   * @scenario inheriting builders are created for a pojo annotated with @GeneratePojoBuilder baseclass of another builder
   */
  @Test
  public void testShouldGenerateAppleBuilderInheritingFruitBuilder() {
    // Given:
    sourceFor(Fruit.class, Apple.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(FruitBuilder.class)
        .generatedSameSourceAs(AppleBuilder.class)
        .compiled(FruitBuilder.class)
        .compiled(AppleBuilder.class)
        .reported(Compilation.Success);
  }

}
