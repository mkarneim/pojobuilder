package net.karneim.pojobuilder.analysis.with.name;

import net.karneim.pojobuilder.analysis.Input;
import net.karneim.pojobuilder.analysis.Output;
import net.karneim.pojobuilder.analysis.with.AnalysisTestSupport;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JavaModelAnalyzer_WithName_Test extends AnalysisTestSupport {

  @Test
  public void testAnalyze() {
    // Given:
    Input input = inputFor(Pojo.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getPojoType().getName()).isEqualTo(Pojo.class.getName());
    assertThat(output.getBuilderModel().getType().getName()).isEqualTo(
        "net.karneim.pojobuilder.analysis.with.name.FluentPojoBuilder");
  }

}
