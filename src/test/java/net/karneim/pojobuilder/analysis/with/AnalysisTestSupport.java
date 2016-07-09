package net.karneim.pojobuilder.analysis.with;

import net.karneim.pojobuilder.analysis.*;
import net.karneim.pojobuilder.testenv.ProcessingEnvironmentRunner;
import org.junit.Before;
import org.junit.runner.RunWith;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import java.util.List;

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

  /**
   * Pojobuilder Input for a given annotated pojo
   */
  protected Input inputFor(Class<?> pojo) {
    String pojoClassname = pojo.getCanonicalName();
    TypeElement pojoType = elements.getTypeElement(pojoClassname);
    return inputFactory.getInput(pojoType);
  }

  /**
   * Pojobuilder Input for a given annotated factory method
   */
  protected Input inputFor(Class<?> factory, String methodName) {
    String factoryClassname = factory.getCanonicalName();
    TypeElement factoryType = elements.getTypeElement(factoryClassname);
    List<ExecutableElement> methods = ElementFilter.methodsIn(elements.getAllMembers(factoryType));
    ExecutableElement methodEl = getFirstMethodByName(methodName, methods);
    return inputFactory.getInput(methodEl);
  }

  private static ExecutableElement getFirstMethodByName(String name, List<ExecutableElement> methods) {
    for (ExecutableElement e : methods) {
      if (name.equals(e.getSimpleName().toString())) {
        return e;
      }
    }
    return null;
  }


}
