package net.karneim.pojobuilder.analysis.with.generationgap;

import net.karneim.pojobuilder.analysis.Input;
import net.karneim.pojobuilder.analysis.Output;
import net.karneim.pojobuilder.analysis.with.AnalysisTestSupport;
import org.junit.Test;

import javax.lang.model.element.TypeElement;

import static org.assertj.core.api.Assertions.assertThat;

public class JavaModelAnalyzer_GenerationGap_Test extends AnalysisTestSupport {

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
        "net.karneim.pojobuilder.analysis.with.generationgap.AbstractPojoBuilder");
    assertThat(output.getBuilderModel().isAbstract()).isTrue();
    assertThat(output.getBuilderModel().getSelfType().getName()).isEqualTo(
        "net.karneim.pojobuilder.analysis.with.generationgap.PojoBuilder");
    assertThat(output.getManualBuilderModel().getType().getName()).isEqualTo(
        "net.karneim.pojobuilder.analysis.with.generationgap.PojoBuilder");
    assertThat(output.getManualBuilderModel().getBaseType().getName()).isEqualTo(
        "net.karneim.pojobuilder.analysis.with.generationgap.AbstractPojoBuilder");
  }

}
