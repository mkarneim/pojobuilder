package net.karneim.pojobuilder.analysis.with.generics;

import net.karneim.pojobuilder.analysis.Input;
import net.karneim.pojobuilder.analysis.Output;
import net.karneim.pojobuilder.analysis.with.AnalysisTestSupport;
import net.karneim.pojobuilder.model.PropertyListM.Key;
import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.model.TypeM;
import org.junit.Test;

import javax.lang.model.element.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

public class JavaModelAnalyzer_Generics_Test extends AnalysisTestSupport {

  @Test
  public void testAnalyze() throws Exception {
    // Given:
    Input input = inputFor(ContainerPojo.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getPojoType().getName()).isEqualTo(ContainerPojo.class.getName());
    TypeM builderType = output.getBuilderModel().getType();
    assertThat(builderType).isNotNull();
    assertThat(builderType.getName()).isEqualTo("net.karneim.pojobuilder.analysis.with.generics.ContainerPojoBuilder");
    assertThat(builderType.getGenericType())
        .isEqualTo(
            "net.karneim.pojobuilder.analysis.with.generics.ContainerPojoBuilder<T extends net.karneim.pojobuilder.analysis.with.generics.ItemPojo & java.io.Serializable>");
    assertThat(output.getBuilderModel().getProperties()).hasSize(1);
    PropertyM nameProperty = output.getBuilderModel().getProperties().get(new Key("element", "T"));
    assertThat(nameProperty).isNotNull();
    assertThat(nameProperty.getFieldAccess()).isNotNull();
    assertThat(nameProperty.getFieldAccess().getModifier()).contains(Modifier.PUBLIC);
  }

}
