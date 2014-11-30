package net.karneim.pojobuilder.processor.with.innerclass;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.processor.with.innerclass.OuterPojo.InnerPojo;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_InnerClass_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder is created for a inner class.
   */
  @Test
  public void testShouldGenerateBuilderForInnerClass() throws Exception {
    // Given:
    String pojoClassname = InnerPojo.class.getName();
    String factoryClassname = PojoFactory.class.getName();
    String builderClassname = InnerPojoBuilder.class.getName();
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojoClassname));
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, factoryClassname));

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
