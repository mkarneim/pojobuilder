package net.karneim.pojobuilder.processor.with.baseclass;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_Baseclass_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder is created with a simple base class that has been configured via @GeneratePojoBuilder
   * annotation
   */
  @Test
  public void testShouldGenerateBuilderWithSimpleBaseClass() throws Exception {
    // Given:
    sourceFor(Pojo1.class);
    sourceFor(SimpleBaseBuilder.class);
    String builderClassname = Pojo1Builder.class.getName();

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

  /**
   * @throws Exception
   * @scenario the generated builder should place an @Override annotation onto the build()-method if one of it's super
   * types declares one.
   */
  @Test
  public void testShouldGenerateBuilderWithBaseClassThatDeclaresGenericBuildMethod() throws Exception {
    // Given:
    sourceFor(Pojo2.class);
    sourceFor(BaseBuilderWithGenericBuildMethod.class);
    String builderClassname = Pojo2Builder.class.getName();

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
   * @scenario the generated builder should place an @Override annotation onto the build()-method if one of it's super
   * types declares one.
   */
  @Test
  public void testShouldGenerateBuilderWithBaseClassThatDeclaresRawBuildMethod() throws Exception {
    // Given:
    sourceFor(Pojo3.class);
    sourceFor(BaseBuilderWithRawBuildMethod.class);
    String builderClassname = Pojo3Builder.class.getName();

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
}
