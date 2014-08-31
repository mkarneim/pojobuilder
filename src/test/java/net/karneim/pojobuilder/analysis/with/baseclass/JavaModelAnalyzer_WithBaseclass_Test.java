package net.karneim.pojobuilder.analysis.with.baseclass;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import net.karneim.pojobuilder.analysis.DirectivesFactory;
import net.karneim.pojobuilder.analysis.Input;
import net.karneim.pojobuilder.analysis.InputFactory;
import net.karneim.pojobuilder.analysis.JavaModelAnalyzer;
import net.karneim.pojobuilder.analysis.JavaModelAnalyzerUtil;
import net.karneim.pojobuilder.analysis.Output;
import net.karneim.pojobuilder.testenv.AddToSourceTree;
import net.karneim.pojobuilder.testenv.ProcessingEnvironmentRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({"src/testdata/java"})
public class JavaModelAnalyzer_WithBaseclass_Test {

  private ProcessingEnvironment env;
  private Elements elements;
  private InputFactory inputFactory;
  private JavaModelAnalyzer underTest;

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

  @Test
  public void testAnalyze() throws Exception {
    // Given:
    String pojoClassname = Pojo.class.getCanonicalName();
    TypeElement pojoType = elements.getTypeElement(pojoClassname);
    Input input = inputFactory.getInput(pojoType);

    // When:
    Output output = underTest.analyze(input);

    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getPojoType().getName()).isEqualTo(pojoClassname);
    assertThat(output.getBuilderModel().getType().getName()).isEqualTo(
        "net.karneim.pojobuilder.analysis.with.baseclass.PojoBuilder");
    assertThat(output.getBuilderModel().getBaseType().getName()).isEqualTo(
        "net.karneim.pojobuilder.analysis.with.baseclass.BuilderBaseclass");
  }


}
