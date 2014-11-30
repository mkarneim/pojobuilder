package net.karneim.pojobuilder.analysis.with.fieldaccess;

import net.karneim.pojobuilder.analysis.Input;
import net.karneim.pojobuilder.analysis.Output;
import net.karneim.pojobuilder.analysis.with.AnalysisTestSupport;
import net.karneim.pojobuilder.model.PropertyListM.Key;
import net.karneim.pojobuilder.model.PropertyM;
import org.junit.Test;

import javax.lang.model.element.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

public class JavaModelAnalyzer_FieldAccess_Test extends AnalysisTestSupport {

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
        "net.karneim.pojobuilder.analysis.with.fieldaccess.PojoBuilder");
    assertThat(output.getBuilderModel().getProperties()).hasSize(1);
    PropertyM nameProperty = output.getBuilderModel().getProperties().get(new Key("name", "java.lang.String"));
    assertThat(nameProperty).isNotNull();
    assertThat(nameProperty.getFieldAccess()).isNotNull();
    assertThat(nameProperty.getFieldAccess().getModifier()).contains(Modifier.PUBLIC);
  }

}
