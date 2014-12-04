package net.karneim.pojobuilder.processor.with;

import net.karneim.pojobuilder.testenv.JavaProject;
import net.karneim.pojobuilder.testenv.TestBase;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.io.IOException;

/**
 * FEST assertion for PojoBuilder compilation environment
 */
public class JavaProjectAssert extends AbstractAssert<JavaProjectAssert,JavaProject> {

  private TestBase base = new TestBase() {};

  public JavaProjectAssert(JavaProject javaProject) {
    super(javaProject, JavaProjectAssert.class);
  }

  public JavaProjectAssert generatedSameSourceAs(Class target) {
    String actualSource = null;
    String expectedSource = null;
    try {
      actualSource = TestBase.getContent(actual.findGeneratedSource(target.getName()));
      expectedSource = base.loadResourceFromFilesystem(TestBase.TESTDATA_DIRECTORY, base.getSourceFilename(target.getName()));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    Assertions.assertThat(expectedSource).isEqualTo(actualSource);
    return this;
  }

//  public JavaProjectAssert generatedSameSourceAs(String filename) {
//    String actualSource = null;
//    String expectedSource = null;
//    try {
//      actualSource = TestBase.getContent(actual.findGeneratedSource(target.getName()));
//      expectedSource = loadResourceFromClasspath(filename);
//    } catch (Exception e) {
//      throw new RuntimeException(e);
//    }
//    Assertions.assertThat(expectedSource).isEqualTo(actualSource);
//    return this;
//  }

  public JavaProjectAssert didNotGenerateSourceFor(Class target) {
    String actualSource = null;
    try {
      actualSource = base.getContent(actual.findGeneratedSource(target.getName()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    Assertions.assertThat(actualSource).isNull();
    return this;

  }

  public JavaProjectAssert compiled(Class target) {
    try {
      Assertions.assertThat(actual.findClass(target.getName())).isNotNull();
      return this;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
