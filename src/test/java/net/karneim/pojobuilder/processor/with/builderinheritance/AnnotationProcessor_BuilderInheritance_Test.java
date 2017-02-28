package net.karneim.pojobuilder.processor.with.builderinheritance;

import net.karneim.pojobuilder.analysis.Output;
import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.testenv.JavaProject.Compilation;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import javax.lang.model.element.Modifier;

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
   * @scenario builder for a factory method is never abstract
   */
  @Test
  public void testAbstractPojoFromFactoryGeneratesConcreteBuilder() {
    // Given:
    sourceForFactoryMethod(AbstractPojo.class, "instantiate");
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(ConcretePojoBuilder.class)
        .compiled(ConcretePojoBuilder.class)
        .reported(Compilation.Success);  }

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
