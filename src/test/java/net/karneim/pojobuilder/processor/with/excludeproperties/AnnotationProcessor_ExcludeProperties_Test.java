package net.karneim.pojobuilder.processor.with.excludeproperties;

import static net.karneim.pojobuilder.PbAssertions.assertThat;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.processor.with.includeproperties.Pojo2;
import net.karneim.pojobuilder.processor.with.includeproperties.Pojo2Builder;
import net.karneim.pojobuilder.processor.with.includeproperties.Pojo3;
import net.karneim.pojobuilder.processor.with.includeproperties.Pojo3Builder;
import net.karneim.pojobuilder.processor.with.includeproperties.Pojo3Factory;
import net.karneim.pojobuilder.testenv.JavaProject.Compilation;

import org.junit.Test;

/**
 * @feature Properties can be excluded from the generated builder.
 */
public class AnnotationProcessor_ExcludeProperties_Test extends ProcessorTestSupport {

  /**
   * @scenario Some properties are excluded
   */
  @Test
  public void testShouldGenerateBuilderWithoutExcludedProperties() {
    // Given:
    sourceFor(Pojo1.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(Pojo1Builder.class).compiled(Pojo1Builder.class)
        .reported(Compilation.Success);
  }

  /**
   * @scenario Mandatory properties (used as constructor parameter) must not be explicitly excluded.
   */
  @Test
  public void testShouldNotExcludeMandatoryConstructorParameters() {
    // Given:
    sourceFor(Pojo2.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(Pojo2Builder.class).compiled(Pojo2Builder.class)
        .reported(Compilation.Success);
  }

  /**
   * @scenario Mandatory properties (used as factory method parameter) must not be explicitly excluded.
   */
  @Test
  public void testShouldNotExcludeMandatoryFactoryMethodParameters() {
    // Given:
    sourceFor(Pojo3.class, Pojo3Factory.class);

    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(Pojo3Builder.class).compiled(Pojo3Builder.class)
        .reported(Compilation.Success);
  }
}
