package net.karneim.pojobuilder.analysis.with.staticfactorymethod;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import net.karneim.pojobuilder.Visibility;
import net.karneim.pojobuilder.analysis.Input;
import net.karneim.pojobuilder.analysis.Output;
import net.karneim.pojobuilder.analysis.with.AnalysisTestSupport;

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
  }

  @Test
  public void testWithPrivateConstructor() {
    // Given:
    Input input = inputFor(StaticFactoryMethodAnalyses.WithPrivateConstructor.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getConstructorVisibility()).isEqualTo(Visibility.PRIVATE);
  }

  @Test
  public void testWithPrivateConstructorAndGenerationGap() {
    // Given:
    Input input = inputFor(StaticFactoryMethodAnalyses.WithPrivateConstructorAndGenerationGap.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getStaticFactoryMethod()).isNull();
    assertThat(output.getBuilderModel().getConstructorVisibility()).isEqualTo(Visibility.PUBLIC);
    assertThat(output.getManualBuilderModel().getStaticFactoryMethod().getName()).isEqualTo("withPrivateConstructorAndGenerationGap");
    assertThat(output.getManualBuilderModel().getConstructorVisibility()).isEqualTo(Visibility.PRIVATE);
  }



}
