package net.karneim.pojobuilder.analysis.with.constructor;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import net.karneim.pojobuilder.Visibility;
import net.karneim.pojobuilder.analysis.Input;
import net.karneim.pojobuilder.analysis.Output;
import net.karneim.pojobuilder.analysis.with.AnalysisTestSupport;

public class JavaModelAnalyzer_StaticFactoryMethod_Test extends AnalysisTestSupport {

  @Test
  public void testWithPublicConstructor() {
    // Given:
    Input input = inputFor(ConstructorAnalyses.WithPublicConstructor.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getConstructorVisibility()).isEqualTo(Visibility.PUBLIC);
  }

  @Test
  public void testWithProtectedConstructor() {
    // Given:
    Input input = inputFor(ConstructorAnalyses.WithProtectedConstructor.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getConstructorVisibility()).isEqualTo(Visibility.PROTECTED);
  }

  @Test
  public void testWithPrivateConstructor() {
    // Given:
    Input input = inputFor(ConstructorAnalyses.WithPrivateConstructor.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getConstructorVisibility()).isEqualTo(Visibility.PRIVATE);
  }

  @Test
  public void testWithPackagePrivateConstructor() {
    // Given:
    Input input = inputFor(ConstructorAnalyses.WithPackagePrivateConstructor.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getConstructorVisibility()).isEqualTo(Visibility.PACKAGE);
  }

}
