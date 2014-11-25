package net.karneim.pojobuilder.analysis.with.staticfactorymethod;

import net.karneim.pojobuilder.analysis.*;
import net.karneim.pojobuilder.analysis.with.intopackage.Pojo;
import net.karneim.pojobuilder.testenv.AddToSourceTree;
import net.karneim.pojobuilder.testenv.ProcessingEnvironmentRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({"src/testdata/java"})
public class JavaModelAnalyzer_StaticFactoryMethod_Test {

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
  public void testWithoutSfm() throws Exception {
    // Given:
    String pojoClassname = StaticFactoryMethodAnalyses.WithoutSfm.class.getCanonicalName();
    TypeElement pojoType = elements.getTypeElement(pojoClassname);
    Input input = inputFactory.getInput(pojoType);

    // When:
    Output output = underTest.analyze(input);

    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getPojoType().getName()).isEqualTo(pojoClassname);
    assertThat(output.getBuilderModel().getStaticFactoryMethod()).isNull();
  }

  @Test
  public void testWithDecapitalisedSfm() throws Exception {
    // Given:
    String pojoClassname = StaticFactoryMethodAnalyses.WithDecapitalisedSfm.class.getCanonicalName();
    TypeElement pojoType = elements.getTypeElement(pojoClassname);
    Input input = inputFactory.getInput(pojoType);

    // When:
    Output output = underTest.analyze(input);

    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getPojoType().getName()).isEqualTo(pojoClassname);
    assertThat(output.getBuilderModel().getStaticFactoryMethod().getName()).isEqualTo("withDecapitalisedSfm");
  }

  @Test
  public void testWithArbitrarySfm() throws Exception {
    // Given:
    String pojoClassname = StaticFactoryMethodAnalyses.WithArbitrarySfm.class.getCanonicalName();
    TypeElement pojoType = elements.getTypeElement(pojoClassname);
    Input input = inputFactory.getInput(pojoType);

    // When:
    Output output = underTest.analyze(input);

    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getPojoType().getName()).isEqualTo(pojoClassname);
    assertThat(output.getBuilderModel().getStaticFactoryMethod().getName()).isEqualTo("$WithArbitrarySfm");
  }


}
