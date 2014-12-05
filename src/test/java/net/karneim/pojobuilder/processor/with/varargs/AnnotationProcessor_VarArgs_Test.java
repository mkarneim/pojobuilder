package net.karneim.pojobuilder.processor.with.varargs;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static net.karneim.pojobuilder.PbAssertions.assertThat;
import static net.karneim.pojobuilder.testenv.JavaProject.Compilation;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_VarArgs_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the pojo a constructor and some setter-methods using a varargs paramete.
   */
  @Test
  public void testVarargsInConstructorAndSetterMethods() {
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
   * @scenario using varargs parameter in the factory method and some setter methods
   */
  @Test
  public void testVarargsInFactoryMethodAndSetterMethods() {
    // Given:
    sourceForFactoryMethod(PojoFactory.class, null);
    // When:
    prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(OtherPojoBuilder.class)
        .compiled(OtherPojoBuilder.class)
        .reported(Compilation.Success);
  }

}
