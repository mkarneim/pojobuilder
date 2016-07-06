package net.karneim.pojobuilder.analysis.with.generics;

import net.karneim.pojobuilder.analysis.Input;
import net.karneim.pojobuilder.analysis.Output;
import net.karneim.pojobuilder.analysis.with.AnalysisTestSupport;
import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.PropertyListM;
import net.karneim.pojobuilder.model.PropertyListM.Key;
import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.model.TypeM;
import org.junit.Test;

import javax.lang.model.element.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

public class JavaModelAnalyzer_Generics_Test extends AnalysisTestSupport {

  @Test
  public void testAnalyze() {
    // Given:
    Input input = inputFor(ContainerPojo.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    BuilderM builderModel = output.getBuilderModel();
    assertThat(builderModel.getPojoType().getName()).isEqualTo(ContainerPojo.class.getName());
    TypeM builderType = builderModel.getType();
    assertThat(builderType).isNotNull();
    assertThat(builderType.getName()).isEqualTo("net.karneim.pojobuilder.analysis.with.generics.ContainerPojoBuilder");
    assertThat(builderType.getGenericTypeDefinition())
        .isEqualTo(
            "net.karneim.pojobuilder.analysis.with.generics.ContainerPojoBuilder<T extends net.karneim.pojobuilder.analysis.with.generics.ItemPojo & java.io.Serializable>");
    PropertyListM properties = builderModel.getProperties();
    assertThat(properties).hasSize(2);
    PropertyM elementProperty = properties.get(new Key("element", "T"));
    assertThat(elementProperty).isNotNull();
    assertThat(elementProperty.getFieldAccess()).isNotNull();
    assertThat(elementProperty.getFieldAccess().getModifier()).contains(Modifier.PUBLIC);
    PropertyM listProperty = properties.get(new Key("list", "java.util.List<? super T>"));
    assertThat(listProperty).isNotNull();
    assertThat(listProperty.getFieldAccess()).isNotNull();
    assertThat(listProperty.getFieldAccess().getModifier()).contains(Modifier.PUBLIC);
  }

}
