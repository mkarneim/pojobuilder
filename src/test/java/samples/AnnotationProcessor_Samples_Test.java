package samples;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.net.URL;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.testenv.JavaProject;
import net.karneim.pojobuilder.testenv.TestBase;
import net.karneim.pojobuilder.testenv.Util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import samples.dsl.Dsl;
import samples.dsl.DslBase;
import samples.dsl.DslTest;
import samples.dsl.IntegerBuilder;
import samples.dsl.LongBuilder;
import samples.dsl.StringBuilder;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_Samples_Test extends ProcessorTestSupport {

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

    String expected =
        loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname));
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

    String expected =
        loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname));
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

    String expected =
        loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname));
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

    String expected =
        loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname));
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

    String expected =
        loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(abstractBuilderClassname));
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

    String expected =
        loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname));
    assertThat(actual).isEqualTo(expected);
    assertThat(prj.findClass(builderClassname)).isNotNull();
  }

  /**
   * @scenario Generating a builder with builder interface and builder properties.
   * @throws Exception
   */
  @Test
  public void testShouldGenerateBuildersWithBuilderInterfaces() throws Exception {
    // Given:
    String pojo1Classname = Address.class.getName();
    String pojo2Classname = Recipient.class.getName();
    String pojo3Classname = Order.class.getName();
    String pojo4Classname = Item.class.getName();

    String builder1Classname = AddressBuilder.class.getName();
    String builder2Classname = RecipientBuilder.class.getName();
    String builder3Classname = OrderBuilder.class.getName();
    String builder4Classname = ItemBuilder.class.getName();

    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojo1Classname));
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojo2Classname));
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojo3Classname));
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojo4Classname));

    // When:
    boolean success = prj.compile();

    // Then:
    assertThat(success).isTrue();
    String actual1 = getContent(prj.findGeneratedSource(builder1Classname));
    logDebug(actual1);
    String actual2 = getContent(prj.findGeneratedSource(builder2Classname));
    logDebug(actual2);
    String actual3 = getContent(prj.findGeneratedSource(builder3Classname));
    logDebug(actual3);
    String actual4 = getContent(prj.findGeneratedSource(builder4Classname));
    logDebug(actual4);

    String expected1 =
        loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builder1Classname));
    assertThat(actual1).isEqualTo(expected1);
    assertThat(prj.findClass(builder1Classname)).isNotNull();
    String expected2 =
        loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builder2Classname));
    assertThat(actual2).isEqualTo(expected2);
    assertThat(prj.findClass(builder2Classname)).isNotNull();
    String expected3 =
        loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builder3Classname));
    assertThat(actual3).isEqualTo(expected3);
    assertThat(prj.findClass(builder3Classname)).isNotNull();
    String expected4 =
        loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builder4Classname));
    assertThat(actual4).isEqualTo(expected4);
    assertThat(prj.findClass(builder4Classname)).isNotNull();
  }

  /**
   * @scenario Generating a builder with validator.
   * @throws Exception
   */
  @Test
  public void testShouldGenerateCredentialsBuilder() throws Exception {
    // Given:
    String pojoClassname = Credentials.class.getName();
    String builderClassname = CredentialsBuilder.class.getName();
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojoClassname));

    // When:
    boolean success = prj.compile();

    // Then:
    assertThat(success).isTrue();
    String actual = getContent(prj.findGeneratedSource(builderClassname));
    logDebug(actual);

    String expected =
        loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname));
    assertThat(actual).isEqualTo(expected);
    assertThat(prj.findClass(builderClassname)).isNotNull();
  }

  /**
   * @scenario Generating a builder declared in {@link DslBase}.
   * @throws Exception
   */
  @Test
  public void testShouldGenerateBuildersInDslBase() throws Exception {
    // Given:
    String factoryClassname = DslBase.class.getName();
    String[] classes =
        new String[] {Dsl.class.getName(), DslTest.class.getName(), IntegerBuilder.class.getName(),
            LongBuilder.class.getName()};
    String builderClassname = StringBuilder.class.getName();
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, factoryClassname));
    for (String cls : classes) {
      prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, cls));
    }

    // When:
    boolean success = prj.compile();

    // Then:
    assertThat(success).isTrue();
    String actual = getContent(prj.findGeneratedSource(builderClassname));
    logDebug(actual);

    String expected =
        loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname));
    assertThat(actual).isEqualTo(expected);
    assertThat(prj.findClass(builderClassname)).isNotNull();
  }

  /**
   * @scenario Generating a builder having a builder factory method.
   * @throws Exception
   */
  @Test
  public void testShouldGenerateTaskBuilder() throws Exception {
    // Given:
    String pojoClassname = Task.class.getName();
    String builderClassname = TaskBuilder.class.getName();
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojoClassname));

    // When:
    boolean success = prj.compile();

    // Then:
    assertThat(success).isTrue();
    String actual = getContent(prj.findGeneratedSource(builderClassname));
    logDebug(actual);

    String expected =
        loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(builderClassname));
    assertThat(actual).isEqualTo(expected);
    assertThat(prj.findClass(builderClassname)).isNotNull();
  }
}
