package net.karneim.pojobuilder.processor.with;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.testenv.JavaProject;
import net.karneim.pojobuilder.testenv.TestBase;
import net.karneim.pojobuilder.testenv.Util;
import org.junit.After;
import org.junit.Before;

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

}
