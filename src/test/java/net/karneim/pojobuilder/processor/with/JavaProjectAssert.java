package net.karneim.pojobuilder.processor.with;

import java.io.IOException;

import com.google.common.base.Throwables;
import net.karneim.pojobuilder.testenv.Extension;
import net.karneim.pojobuilder.testenv.FileHelper;
import net.karneim.pojobuilder.testenv.JavaProject;
import net.karneim.pojobuilder.testenv.FileHelper.FakeAnnotationReplacement;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

/**
 * FEST assertion for PojoBuilder compilation environment
 */
public class JavaProjectAssert extends AbstractAssert<JavaProjectAssert, JavaProject> {

  private FileHelper fileHelper;
  private final String srcDir;

  public JavaProjectAssert(JavaProject javaProject, String srcDir) {
    super(javaProject, JavaProjectAssert.class);
    this.srcDir = srcDir;
    this.fileHelper = new FileHelper(javaProject.isSourceVersionJava9OrGreater()
        ? FakeAnnotationReplacement.JAVA_9
        : FakeAnnotationReplacement.CLASSIC);
  }

  public JavaProjectAssert generatedSameSourceAs(Class<?> target) {
    String actualSource = null;
    String expectedSource = null;
    try {
      actualSource = fileHelper.getContent(actual.findGeneratedSource(target.getName()));
      expectedSource = fileHelper.loadJavaSourceFromFilesystem(srcDir, target.getName());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    Assertions.assertThat(actualSource).isEqualTo(expectedSource);
    return this;
  }

  /**
   * For generation gap where the pre-existence of the manual class stops its from being generated we must assert the
   * content against a file. This is done by a simple naming contract - there <b>must</b> be a file called
   * [classname].java.txt.
   *
   * @param classname The full classname of the builder class
   */
  public JavaProjectAssert generatedSameSourceAs(String classname) {
    // assert filename.endsWith(".java.txt") : "this assertion only for .java.txt pattern, see others usages!";
    String actualSource = null;
    String expectedSource = null;
    try {
      actualSource = fileHelper.getContent(actual.findGeneratedSource(classname));
      expectedSource = fileHelper.loadJavaSourceFromFilesystem(srcDir, classname, Extension.JAVA_TXT);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    Assertions.assertThat(actualSource).isEqualTo(expectedSource);
    return this;
  }

  public JavaProjectAssert didNotGenerateSourceFor(String classname) {
    Object actualSource = null;
    try {
      actualSource = actual.findGeneratedSource(classname);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    Assertions.assertThat(actualSource).isNull();
    return this;
  }

  public JavaProjectAssert didNotGenerateSourceFor(Class<?> target) {
    return didNotGenerateSourceFor(target.getName());
  }

  public JavaProjectAssert compiled(String classname) {
    try {
      Assertions.assertThat(actual.findClass(classname)).isNotNull();
      return this;
    } catch (Exception e) {
      throw Throwables.propagate(e);
    }
  }

  public JavaProjectAssert compiled(Class<?> target) {
    return compiled(target.getName());
  }

  public JavaProjectAssert reported(JavaProject.Compilation status) {
    Assertions.assertThat(actual.getStatus()).isEqualTo(status);
    return this;
  }

}
