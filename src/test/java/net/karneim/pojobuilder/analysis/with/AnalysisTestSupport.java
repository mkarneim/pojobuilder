package net.karneim.pojobuilder.analysis.with;

import net.karneim.pojobuilder.analysis.DirectivesFactory;
import net.karneim.pojobuilder.analysis.InputFactory;
import net.karneim.pojobuilder.analysis.JavaModelAnalyzer;
import net.karneim.pojobuilder.analysis.JavaModelAnalyzerUtil;
import net.karneim.pojobuilder.testenv.ProcessingEnvironmentRunner;
import org.junit.Before;
import org.junit.runner.RunWith;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.util.Elements;

/**
 * Holds common pattern around analysis test cases allowing them to focus on more literate tests
 */
@RunWith(ProcessingEnvironmentRunner.class)
public abstract class AnalysisTestSupport {

  protected ProcessingEnvironment env;
  protected Elements elements;
  protected InputFactory inputFactory;
  protected JavaModelAnalyzer underTest;

  @Before
  public void init() {
    env = ProcessingEnvironmentRunner.getProcessingEnvironment();
    elements = env.getElementUtils();
    JavaModelAnalyzerUtil javaModelAnalyzerUtil = new JavaModelAnalyzerUtil(env.getElementUtils(), env.getTypeUtils());
    inputFactory =
        new InputFactory(env.getTypeUtils(), new DirectivesFactory(env.getElementUtils(), env.getTypeUtils(),
            javaModelAnalyzerUtil));
    underTest =
        new JavaModelAnalyzer(env.getElementUtils(), env.getTypeUtils(), new JavaModelAnalyzerUtil(
            env.getElementUtils(), env.getTypeUtils()));
  }

}
