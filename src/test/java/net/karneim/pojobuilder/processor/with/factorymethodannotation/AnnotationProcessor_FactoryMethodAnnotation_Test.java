package net.karneim.pojobuilder.processor.with.factorymethodannotation;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_FactoryMethodAnnotation_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder is created for a pojo (having no annotation) using som factory method annotated with @GeneratePojoBuilder
   */
  @Test
  public void testShouldGenerateBuilderForAnnotatedFactoryMethod() throws Exception {
    // Given:
    sourceFor( ProductFactory.class, null);
    String builderClassname = ProductBuilder.class.getName();

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
   * @scenario the builder is created for an interface using some annotated factory method.
   */
  @Test
  public void testShouldGenerateBuilderForAnInterfaceUsingAnnotatedFactoryMethod() throws Exception {
    // Given:
    sourceFor( ResourceFactory.class, null);
    String builderClassname = ResourceBuilder.class.getName();

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
   * @scenario the builder is created for an parameterized interface using some annotated factory method.
   */
  @Test
  public void testShouldGenerateBuilderForAParameterizedInterfaceUsingAnnotatedFactoryMethod() throws Exception {
    // Given:
    sourceFor(ContainerFactory.class, null);
    String builderClassname1 = ContainerBuilder.class.getName();
    String builderClassname2 = FileContainerBuilder.class.getName();
    String builderClassname3 = GenericListContainerBuilder.class.getName();

    // When:
    boolean success = prj.compile();

    // Then:
    assertThat(success).isTrue();
    String actual1 = getContent(prj.findGeneratedSource(builderClassname1));
    logDebug(actual1);
    String actual2 = getContent(prj.findGeneratedSource(builderClassname2));
    logDebug(actual2);
    String actual3 = getContent(prj.findGeneratedSource(builderClassname3));
    logDebug(actual3);

    String expected1 = loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname1));
    assertThat(actual1).isEqualTo(expected1);
    assertThat(prj.findClass(builderClassname1)).isNotNull();

    String expected2 = loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname2));
    assertThat(actual2).isEqualTo(expected2);
    assertThat(prj.findClass(builderClassname2)).isNotNull();

    String expected3 = loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname3));
    assertThat(actual3).isEqualTo(expected3);
    assertThat(prj.findClass(builderClassname3)).isNotNull();

  }

  @Test
  public void testPairFactory() throws Exception {
    // Given:
    sourceFor(PairFactory.class,null);
    String builderClassname1 = PairBuilder.class.getName();
    String builderClassname2 = StringPairBuilder.class.getName();
    String builderClassname3 = TPairBuilder.class.getName();

    // When:
    boolean success = prj.compile();

    // Then:
    assertThat(success).isTrue();
    String actual1 = getContent(prj.findGeneratedSource(builderClassname1));
    logDebug(actual1);
    String actual2 = getContent(prj.findGeneratedSource(builderClassname2));
    logDebug(actual2);
    String actual3 = getContent(prj.findGeneratedSource(builderClassname3));
    logDebug(actual3);

    String expected1 = loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname1));
    assertThat(actual1).isEqualTo(expected1);
    assertThat(prj.findClass(builderClassname1)).isNotNull();

    String expected2 = loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname2));
    assertThat(actual2).isEqualTo(expected2);
    assertThat(prj.findClass(builderClassname2)).isNotNull();

    String expected3 = loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname3));
    assertThat(actual3).isEqualTo(expected3);
    assertThat(prj.findClass(builderClassname3)).isNotNull();

  }

}
