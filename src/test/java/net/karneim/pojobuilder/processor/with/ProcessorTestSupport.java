package net.karneim.pojobuilder.processor.with;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.testenv.JavaProject;
import net.karneim.pojobuilder.testenv.TestBase;
import net.karneim.pojobuilder.testenv.Util;
import org.assertj.core.api.Condition;
import org.junit.After;
import org.junit.Before;

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


  // If we need any more JavaProject conditions - let's just write a proper assertor (see FEST docs)
  private final class IdenticalSourceCondition extends Condition<JavaProject> {
    private final Class target;

    IdenticalSourceCondition(Class target) {
      super("generated identical source");
      this.target = target;
    }

    @Override
    public boolean matches(JavaProject value) {
      try {
        String actual = getContent(prj.findGeneratedSource(target.getName()));
        String expected = loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(target.getName()));
        return expected.equals(actual);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }

  private final class CompiledCondition extends Condition<JavaProject> {
    private final Class target;

    CompiledCondition(Class target) {
      super("compiled");
      this.target = target;
    }

    @Override
    public boolean matches(JavaProject value) {
      try {
        return prj.findClass(target.getName()) != null;
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }

  protected Condition<JavaProject> generatedSameSourceAs(Class target) {
    return new IdenticalSourceCondition(target);
  }

  protected Condition<JavaProject> compiled(Class target) {
    return new CompiledCondition(target);
  }

  /**
   * Pojobuilder Input for a given annotated pojo
   */
  protected void sourceFor(Class pojo) {
    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojo.getName()));
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
