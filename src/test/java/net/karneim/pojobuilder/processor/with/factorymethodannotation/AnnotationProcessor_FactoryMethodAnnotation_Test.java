package net.karneim.pojobuilder.processor.with.factorymethodannotation;

import static org.assertj.core.api.Assertions.assertThat;
import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.testenv.JavaProject;
import net.karneim.pojobuilder.testenv.TestBase;
import net.karneim.pojobuilder.testenv.Util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_FactoryMethodAnnotation_Test extends TestBase {

  private JavaProject prj = new JavaProject(Util.createTempDir());

  @Before
  public void setupJavaProject() {
    // Enable the AnnotationProcessor
    prj.getProcessorClasses().add(AnnotationProcessor.class);
  }

  @After
  public void tearDownJavaProject() {
    prj.delete();
  }

  /**
   * @scenario the builder is created for a pojo (having no annotation) using som factory method annotated with @GeneratePojoBuilder
   * @throws Exception
   */
  @Test
  public void testShouldGenerateBuilderForAnnotatedFactoryMethod() throws Exception {
    // Given:
    String pojoClassname = Product.class.getName();
    String factoryClassname = ProductFactory.class.getName();
    String builderClassname = ProductBuilder.class.getName();
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

  /**
   * @scenario the builder is created for an interface using some annotated factory method.
   * @throws Exception
   */
  @Test
  public void testShouldGenerateBuilderForAnInterfaceUsingAnnotatedFactoryMethod() throws Exception {
    // Given:
    String pojoClassname = Resource.class.getName();
    String factoryClassname = ResourceFactory.class.getName();
    String builderClassname = ResourceBuilder.class.getName();
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

  /**
   * @scenario the builder is created for an parameterized interface using some annotated factory method.
   * @throws Exception
   */
  @Test
  public void testShouldGenerateBuilderForAParameterizedInterfaceUsingAnnotatedFactoryMethod() throws Exception {
    // Given:
    String pojoClassname = Container.class.getName();
    String factoryClassname = ContainerFactory.class.getName();
    String builderClassname1 = ContainerBuilder.class.getName();
    String builderClassname2 = FileContainerBuilder.class.getName();
    String builderClassname3 = GenericListContainerBuilder.class.getName();
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojoClassname));
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, factoryClassname));

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
    String pojoClassname = Pair.class.getName();
    String factoryClassname = PairFactory.class.getName();
    String builderClassname1 = PairBuilder.class.getName();
    String builderClassname2 = StringPairBuilder.class.getName();
    String builderClassname3 = TPairBuilder.class.getName();
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojoClassname));
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, factoryClassname));

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
