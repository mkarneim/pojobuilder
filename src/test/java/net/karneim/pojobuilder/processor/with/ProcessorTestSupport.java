package net.karneim.pojobuilder.processor.with;

import com.google.common.base.Throwables;
import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.testenv.Extension;
import net.karneim.pojobuilder.testenv.JavaProject;
import net.karneim.pojobuilder.testenv.TestBase;
import net.karneim.pojobuilder.testenv.Util;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Holds common pattern around {@link AnnotationProcessor} test cases allowing them to focus on more literate tests
 */
public abstract class ProcessorTestSupport extends TestBase {
  // TODO: marc Distinction between TestBase on ProcessorTestSupport is unclear (possibly non-existent)

  protected JavaProject prj;

  @Before
  public void setupJavaProject() {
    prj = new JavaProject(Util.createTempDir());
    // Enable the PojoBuilder AnnotationProcessor
    prj.getProcessorClasses().add(AnnotationProcessor.class);
  }

  @After
  public void tearDownJavaProject() {
    prj.delete();
  }

  /**
   * For generation gap where the pre-existence of the manual class stops its from being generated we must assert the
   * content against a file. This is done by a simple naming contract - there <b>must</b> be a file called [classname].java.txt.
   * @param classes Classes to include for direct compilation by this test
   */
  protected void sourceFor(Class<?>... classes) {
    for( Class<?> c: classes) {
      prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, c.getName()));
    }
  }

  /**
   * For generation gap where the pre-existence of the manual class stops its from being generated we must assert the
   * content against a file. This is done by a simple naming contract - there <b>must</b> be a file called [classname].java.txt.
   * @param classnames Classnames to include for direct compilation by this test
   */
  protected void sourceFor( String... classnames ) {
    try {
      for( String classname: classnames) {
        prj.addSourceFile(classname, loadJavaSourceFromFilesystem(TestBase.TESTDATA_DIRECTORY, classname, Extension.JAVA_TXT));
      }
    } catch (IOException e) {
      throw Throwables.propagate(e);
    }
  }

  /**
   * Pojobuilder Input for a given annotated factory method
   *
   * @param methodName null takes the first method found
   */
  protected void sourceForFactoryMethod(Class<?> factory, String methodName) {
    sourceFor(factory);
    Method method = firstMatchingMethod(factory, methodName);
    sourceFor(method.getReturnType());
  }

  private static Method firstMatchingMethod(Class<?> clazz, String methodName) {
    for (Method method : clazz.getDeclaredMethods()) {
      if (methodName == null || method.getName().equals(methodName)) {
        return method;
      }
    }
    throw new IllegalStateException(String.format("Method [%s] not found in %s", methodName, clazz));
  }

}
