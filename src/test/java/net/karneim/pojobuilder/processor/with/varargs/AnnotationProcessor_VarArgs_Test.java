package net.karneim.pojobuilder.processor.with.varargs;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_VarArgs_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the pojo has a constructor and some setter-methods using a varargs parameter.
   */
  @Test
  public void testVarargsInConstructorAndSetterMethods() throws Exception {
    // Given:
    sourceFor(Pojo.class);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .has(generatedSameSourceAs(PojoBuilder.class))
        .has(compiled(PojoBuilder.class));
    assertThat(success).isTrue();
  }

  /**
   * @throws Exception
   * @scenario using varargs parameter in the factory method and some setter methods
   */
  @Test
  public void testVarargsInFactoryMethodAndSetterMethods() throws Exception {
    // Given:
    sourceFor(PojoFactory.class, null);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .has(generatedSameSourceAs(OtherPojoBuilder.class))
        .has(compiled(OtherPojoBuilder.class));
    assertThat(success).isTrue();
  }

}
