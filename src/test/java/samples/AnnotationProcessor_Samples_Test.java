package samples;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.net.URL;

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
public class AnnotationProcessor_Samples_Test extends TestBase {

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
   * @scenario Generating a builder for {@link Contact}.
   * @throws Exception
   */
  @Test
  public void testShouldGenerateContactBuilder() throws Exception {
    // Given:
    String pojoClassname = Contact.class.getName();
    String builderClassname = ContactBuilder.class.getName();
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojoClassname));

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
   * @scenario Generating a builder for {@link Contact}.
   * @throws Exception
   */
  @Test
  public void testShouldGenerateUserBuilder() throws Exception {
    // Given:
    String pojoClassname = User.class.getName();
    String builderClassname = UserBuilder.class.getName();
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojoClassname));

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
   * @scenario Generating a builder for {@link File}.
   * @throws Exception
   */
  @Test
  public void testShouldGenerateFileBuilder() throws Exception {
    // Given:
    String factoryClassname = FileFactory.class.getName();
    String builderClassname = "samples.FileBuilder";// FileBuilder.class.getName();
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
   * @scenario Generating a builder for {@link URL}.
   * @throws Exception
   */
  @Test
  public void testShouldGenerateUrlBuilder() throws Exception {
    // Given:
    String factoryClassname = UrlFactory.class.getName();
    String builderClassname = "samples.UrlBuilder";// FileBuilder.class.getName();
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, factoryClassname));

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
   * @scenario Generating a builder with generation gap enabled.
   * @throws Exception
   */
  @Test
  public void testShouldGenerateAbstractPlayerBuilder() throws Exception {
    // Given:
    String pojoClassname = Player.class.getName();
    String builderClassname = PlayerBuilder.class.getName();
    String abstractBuilderClassname = AbstractPlayerBuilder.class.getName();
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojoClassname));
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, builderClassname));

    // When:
    boolean success = prj.compile();

    // Then:
    String actual = getContent(prj.findGeneratedSource(abstractBuilderClassname));
    logDebug(actual);
    assertThat(success).isTrue();

    String expected = loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(abstractBuilderClassname));
    assertThat(actual).isEqualTo(expected);
    assertThat(prj.findClass(builderClassname)).isNotNull();
  }

  /**
   * @scenario Generating a builder with copy method enabled.
   * @throws Exception
   */
  @Test
  public void testShouldGenerateTextEmailBuilder() throws Exception {
    // Given:
    String pojoClassname = TextEmail.class.getName();
    String builderClassname = TextEmailBuilder.class.getName();
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojoClassname));

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
   * @scenario Generating a builder with builder interface and builder properties.
   * @throws Exception
   */
  @Test
  public void testShouldGenerateRecipientBuilderAndAddressBuilder() throws Exception {
    // Given:
    String pojo1Classname = Address.class.getName();
    String pojo2Classname = Recipient.class.getName();
    String builder1Classname = AddressBuilder.class.getName();
    String builder2Classname = "samples.RecipientBuilder";// RecipientBuilder.class.getName();
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojo1Classname));
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojo2Classname));

    // When:
    boolean success = prj.compile();

    // Then:
    assertThat(success).isTrue();
    String actual1 = getContent(prj.findGeneratedSource(builder1Classname));
    logDebug(actual1);
    String actual2 = getContent(prj.findGeneratedSource(builder2Classname));
    logDebug(actual2);

    String expected1 = loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builder1Classname));
    assertThat(actual1).isEqualTo(expected1);
    assertThat(prj.findClass(builder1Classname)).isNotNull();
    String expected2 = loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builder2Classname));
    assertThat(actual2).isEqualTo(expected2);
    assertThat(prj.findClass(builder2Classname)).isNotNull();
  }
}
