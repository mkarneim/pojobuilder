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
    String builderClassname = PojoBuilder.class.getName();

    // When:
    boolean success = prj.compile();

    // Then:
    String actual = getContent(prj.findGeneratedSource(builderClassname));
    logDebug(actual);
    assertThat(success).isTrue();

    String expected = loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname));
    assertThat(actual).isEqualTo(expected);
    assertThat(prj.findClass(builderClassname)).isNotNull();
  }

  /**
   * @throws Exception
   * @scenario using varargs parameter in the factory method and some setter methods
   */
  @Test
  public void testVarargsInFactoryMethodAndSetterMethods() throws Exception {
    // Given:
    sourceFor(PojoFactory.class, null);
    String builderClassname = OtherPojoBuilder.class.getName();

    // When:
    boolean success = prj.compile();

    // Then:
    assertThat(success).isTrue();
    String actual = getContent(prj.findGeneratedSource(builderClassname));
    logDebug(actual);

    String expected = loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname));
    assertThat(actual).isEqualTo(expected);
    assertThat(prj.findClass(builderClassname)).isNotNull();
  }

}
