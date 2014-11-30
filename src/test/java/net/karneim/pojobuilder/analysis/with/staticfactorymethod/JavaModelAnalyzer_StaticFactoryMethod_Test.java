package net.karneim.pojobuilder.analysis.with.staticfactorymethod;

import net.karneim.pojobuilder.analysis.Input;
import net.karneim.pojobuilder.analysis.Output;
import net.karneim.pojobuilder.analysis.with.AnalysisTestSupport;
import org.junit.Test;

import javax.lang.model.element.TypeElement;

import static org.assertj.core.api.Assertions.assertThat;

public class JavaModelAnalyzer_StaticFactoryMethod_Test extends AnalysisTestSupport {

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
    assertThat(output.getBuilderModel().getStaticFactoryMethod().getName()).isEqualTo("$WithArbitrarySfm");
  }

  @Test
  public void testWithGenerationGap() throws Exception {
    // Given:
    String pojoClassname = StaticFactoryMethodAnalyses.WithGenerationGap.class.getCanonicalName();
    TypeElement pojoType = elements.getTypeElement(pojoClassname);
    Input input = inputFactory.getInput(pojoType);

    // When:
    Output output = underTest.analyze(input);

    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getStaticFactoryMethod()).isNull();
    assertThat(output.getManualBuilderModel().getStaticFactoryMethod().getName()).isEqualTo("withGenerationGap");
  }

}
