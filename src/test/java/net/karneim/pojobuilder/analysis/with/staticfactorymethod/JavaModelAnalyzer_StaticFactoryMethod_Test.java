package net.karneim.pojobuilder.analysis.with.staticfactorymethod;

import net.karneim.pojobuilder.analysis.Input;
import net.karneim.pojobuilder.analysis.Output;
import net.karneim.pojobuilder.analysis.with.AnalysisTestSupport;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JavaModelAnalyzer_StaticFactoryMethod_Test extends AnalysisTestSupport {

  @Test
  public void testWithoutSfm() {
    // Given:
    Input input = inputFor(StaticFactoryMethodAnalyses.WithoutSfm.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getStaticFactoryMethod()).isNull();
  }

  @Test
  public void testWithDecapitalisedSfm() {
    // Given:
    Input input = inputFor(StaticFactoryMethodAnalyses.WithDecapitalisedSfm.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getStaticFactoryMethod().getName()).isEqualTo("withDecapitalisedSfm");
    assertThat(output.getBuilderModel().hasPublicConstructor()).isEqualTo(true);
  }

  @Test
  public void testWithArbitrarySfm() {
    // Given:
    Input input = inputFor(StaticFactoryMethodAnalyses.WithArbitrarySfm.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getStaticFactoryMethod().getName()).isEqualTo("$WithArbitrarySfm");
    assertThat(output.getBuilderModel().hasPublicConstructor()).isEqualTo(true);
  }

  @Test
  public void testWithGenerationGap() {
    // Given:
    Input input = inputFor(StaticFactoryMethodAnalyses.WithGenerationGap.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getStaticFactoryMethod()).isNull();
    assertThat(output.getManualBuilderModel().getStaticFactoryMethod().getName()).isEqualTo("withGenerationGap");
    assertThat(output.getBuilderModel().hasPublicConstructor()).isEqualTo(true);
  }

  @Test
  public void testWithoutPublicConstructor() {
    // Given:
    Input input = inputFor(StaticFactoryMethodAnalyses.WithoutPublicConstructor.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().hasPublicConstructor()).isEqualTo(false);
  }

}
