package net.karneim.pojobuilder.analysis.with.setter;

import static javax.lang.model.element.Modifier.PUBLIC;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import net.karneim.pojobuilder.analysis.Input;
import net.karneim.pojobuilder.analysis.Output;
import net.karneim.pojobuilder.analysis.with.AnalysisTestSupport;
import net.karneim.pojobuilder.model.PropertyListM.Key;
import net.karneim.pojobuilder.model.PropertyM;

public class JavaModelAnalyzer_WithSetter_Test extends AnalysisTestSupport {

  @Test
  public void testAnalyze_public_void_setMyProperty_Object() {
    // Given:
    Class<?> pojoClass = PojoWithPublicVoidObjectSetter.class;
    Input input = inputFor(pojoClass);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    Key key = new Key("myProperty", "java.lang.Object");
    PropertyM property = output.getBuilderModel().getProperties().get(key);
    assertThat(property).isNotNull();
    assertThat(property.getSetterMethod()).isNotNull();
    assertThat(property.getSetterMethod().getDeclaringClass().getName())
        .isEqualTo(pojoClass.getName());
    assertThat(property.getSetterMethod().getModifiers()).containsOnly(PUBLIC);
    assertThat(property.getSetterMethod().getName()).isEqualTo("setMyProperty");
  }

  @Test
  public void testAnalyze_public_void_setMyProperty_primitive() {
    // Given:
    Class<?> pojoClass = PojoWithPublicVoidPrimitiveSetter.class;
    Input input = inputFor(pojoClass);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    Key key = new Key("myProperty", "int");
    PropertyM property = output.getBuilderModel().getProperties().get(key);
    assertThat(property).isNotNull();
    assertThat(property.getSetterMethod()).isNotNull();
    assertThat(property.getSetterMethod().getDeclaringClass().getName())
        .isEqualTo(pojoClass.getName());
    assertThat(property.getSetterMethod().getModifiers()).containsOnly(PUBLIC);
    assertThat(property.getSetterMethod().getName()).isEqualTo("setMyProperty");
  }

  @Test
  public void testAnalyze_public_Object_setMyProperty_Object() {
    // Given:
    Class<?> pojoClass = PojoWithPublicObjectObjectSetter.class;
    Input input = inputFor(pojoClass);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    Key key = new Key("myProperty", "java.lang.Object");
    PropertyM property = output.getBuilderModel().getProperties().get(key);
    assertThat(property).isNotNull();
    assertThat(property.getSetterMethod()).isNotNull();
    assertThat(property.getSetterMethod().getDeclaringClass().getName())
        .isEqualTo(pojoClass.getName());
    assertThat(property.getSetterMethod().getModifiers()).containsOnly(PUBLIC);
    assertThat(property.getSetterMethod().getName()).isEqualTo("setMyProperty");
  }

  @Test
  public void testAnalyze_public_primitive_setMyProperty_Object() {
    // Given:
    Class<?> pojoClass = PojoWithPublicPrimitiveObjectSetter.class;
    Input input = inputFor(pojoClass);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    Key key = new Key("myProperty", "java.lang.Object");
    PropertyM property = output.getBuilderModel().getProperties().get(key);
    assertThat(property).isNotNull();
    assertThat(property.getSetterMethod()).isNotNull();
    assertThat(property.getSetterMethod().getDeclaringClass().getName())
        .isEqualTo(pojoClass.getName());
    assertThat(property.getSetterMethod().getModifiers()).containsOnly(PUBLIC);
    assertThat(property.getSetterMethod().getName()).isEqualTo("setMyProperty");
  }

}
