package net.karneim.pojobuilder.analysis.with.generics;

import static org.assertj.core.api.Assertions.assertThat;

import javax.lang.model.element.Modifier;

import org.junit.Test;

import net.karneim.pojobuilder.analysis.Input;
import net.karneim.pojobuilder.analysis.Output;
import net.karneim.pojobuilder.analysis.with.AnalysisTestSupport;
import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.PropertyListM;
import net.karneim.pojobuilder.model.PropertyListM.Key;
import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.model.TypeM;

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
    assertThat(properties).hasSize(7);

    PropertyM elementProperty = properties.get(new Key("element", "T"));
    assertThat(elementProperty).isNotNull();
    assertThat(elementProperty.getFieldAccess()).isNotNull();
    assertThat(elementProperty.getFieldAccess().getModifier()).contains(Modifier.PUBLIC);
    assertThat(elementProperty.getPropertyType().getGenericType()).isEqualTo("T");

    PropertyM listProperty = properties.get(new Key("list", "java.util.List<T>"));
    assertThat(listProperty).isNotNull();
    assertThat(listProperty.getFieldAccess()).isNotNull();
    assertThat(listProperty.getFieldAccess().getModifier()).contains(Modifier.PUBLIC);
    assertThat(listProperty.getPropertyType().getGenericType()).isEqualTo("java.util.List<T>");

    PropertyM wildcardListProperty = properties.get(new Key("wildcardList", "java.util.List<? super T>"));
    assertThat(wildcardListProperty).isNotNull();
    assertThat(wildcardListProperty.getFieldAccess()).isNotNull();
    assertThat(wildcardListProperty.getFieldAccess().getModifier()).contains(Modifier.PUBLIC);
    assertThat(wildcardListProperty.getPropertyType().getGenericType()).isEqualTo("java.util.List<? super T>");

    PropertyM arrayProperty = properties.get(new Key("array", "T[]"));
    assertThat(arrayProperty).isNotNull();
    assertThat(arrayProperty.getFieldAccess()).isNotNull();
    assertThat(arrayProperty.getFieldAccess().getModifier()).contains(Modifier.PUBLIC);
    assertThat(arrayProperty.getPropertyType().getGenericType()).isEqualTo("T[]");

    PropertyM arrayListProperty = properties.get(new Key("arrayList", "java.util.List<T[]>"));
    assertThat(arrayListProperty).isNotNull();
    assertThat(arrayListProperty.getFieldAccess()).isNotNull();
    assertThat(arrayListProperty.getFieldAccess().getModifier()).contains(Modifier.PUBLIC);
    assertThat(arrayListProperty.getPropertyType().getGenericType()).isEqualTo("java.util.List<T[]>");

    PropertyM primitiveArrayProperty = properties.get(new Key("primitiveArray", "int[]"));
    assertThat(primitiveArrayProperty).isNotNull();
    assertThat(primitiveArrayProperty.getFieldAccess()).isNotNull();
    assertThat(primitiveArrayProperty.getFieldAccess().getModifier()).contains(Modifier.PUBLIC);
    assertThat(primitiveArrayProperty.getPropertyType().getGenericType()).isEqualTo("int[]");

    PropertyM primitiveArrayListProperty = properties.get(new Key("primitiveArrayList", "java.util.List<int[]>"));
    assertThat(primitiveArrayListProperty).isNotNull();
    assertThat(primitiveArrayListProperty.getFieldAccess()).isNotNull();
    assertThat(primitiveArrayListProperty.getFieldAccess().getModifier()).contains(Modifier.PUBLIC);
    assertThat(primitiveArrayListProperty.getPropertyType().getGenericType()).isEqualTo("java.util.List<int[]>");
  }

}
