package net.karneim.pojobuilder.processor.with;

import com.google.common.base.Throwables;
import net.karneim.pojobuilder.processor.AnnotationProcessor;
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
   * Pojobuilder Input for a given annotated pojo
   */
  protected void sourceFor(Class pojo) {
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojo.getName()));
  }

  /**
   * For generation gap where the pre-existence of the manual class stops its from being generated we must assert the
   * content against a file. This is done by a simple naming contract - there <b>must</b> be a file called [classname].java.txt.
   * @param classname The full classname of the builder class
   */
  protected void sourceFor( String classname ) {
    try {
      prj.addSourceFile(classname, loadResourceFromFilesystem(TestBase.TESTDATA_DIRECTORY, getSourceFilename(classname)+".txt"));
    } catch (IOException e) {
      throw Throwables.propagate(e);
    }
  }

  /**
   * Pojobuilder Input for a given annotated factory method
   *
   * @param methodName null takes the first method found
   */
  protected void sourceFor(Class factory, String methodName) {
    sourceFor(factory);
    Method method = firstMatchingMethod(factory, methodName);
    sourceFor(method.getReturnType());
  }

  private static Method firstMatchingMethod(Class clazz, String methodName) {
    for (Method method : clazz.getDeclaredMethods()) {
      if (methodName == null || method.getName().equals(methodName)) {
        return method;
      }
    }
    throw new IllegalStateException(String.format("Method [%s] not found in %s", methodName, clazz));
  }


}
