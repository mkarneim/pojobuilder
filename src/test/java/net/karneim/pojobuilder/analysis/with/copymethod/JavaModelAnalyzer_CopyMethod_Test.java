package net.karneim.pojobuilder.analysis.with.copymethod;

import net.karneim.pojobuilder.analysis.Input;
import net.karneim.pojobuilder.analysis.Output;
import net.karneim.pojobuilder.analysis.with.AnalysisTestSupport;
import net.karneim.pojobuilder.model.PropertyListM.Key;
import net.karneim.pojobuilder.model.PropertyM;
import org.junit.Test;

import javax.lang.model.element.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

public class JavaModelAnalyzer_CopyMethod_Test extends AnalysisTestSupport {

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
        "net.karneim.pojobuilder.analysis.with.copymethod.PojoBuilder");
    assertThat(output.getBuilderModel().getProperties()).hasSize(2);
    PropertyM nameProperty = output.getBuilderModel().getProperties().get(new Key("name", "java.lang.String"));
    assertThat(nameProperty).isNotNull();
    assertThat(nameProperty.getFieldAccess()).isNotNull();
    assertThat(nameProperty.getFieldAccess().getModifier()).contains(Modifier.PUBLIC);
    PropertyM sizeProperty = output.getBuilderModel().getProperties().get(new Key("size", "int"));
    assertThat(sizeProperty).isNotNull();
    assertThat(sizeProperty.getFieldAccess()).isNull();
    assertThat(sizeProperty.getSetterMethod()).isNotNull();
    assertThat(sizeProperty.getSetterMethod().getModifiers()).contains(Modifier.PUBLIC);
    assertThat(sizeProperty.getGetterMethod()).isNotNull();
    assertThat(sizeProperty.getGetterMethod().getModifiers()).contains(Modifier.PUBLIC);
    assertThat(output.getBuilderModel().getCopyMethod()).isNotNull();
    assertThat(output.getBuilderModel().getCopyMethod().getName()).isEqualTo("copy");

  }


}
