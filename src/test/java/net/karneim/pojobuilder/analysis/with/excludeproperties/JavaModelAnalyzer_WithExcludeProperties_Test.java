package net.karneim.pojobuilder.analysis.with.excludeproperties;

import static org.assertj.core.api.Assertions.assertThat;
import net.karneim.pojobuilder.analysis.Input;
import net.karneim.pojobuilder.analysis.Output;
import net.karneim.pojobuilder.analysis.with.AnalysisTestSupport;
import net.karneim.pojobuilder.model.PropertyListM.Key;
import net.karneim.pojobuilder.model.PropertyM;

import org.junit.Test;

public class JavaModelAnalyzer_WithExcludeProperties_Test extends AnalysisTestSupport {

  @Test
  public void testAnalyzePojo1() {
    // Given:
    Input input = inputFor(Pojo1.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getPojoType().getName()).isEqualTo(Pojo1.class.getName());
    assertThat(output.getBuilderModel().getProperties()).hasSize(1);
    PropertyM emailProperty = output.getBuilderModel().getProperties().get(new Key("email", "java.lang.String"));
    assertThat(emailProperty).isNotNull();
  }

  @Test
  public void testAnalyzePojo2() {
    // Given:
    Input input = inputFor(Pojo2.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getPojoType().getName()).isEqualTo(Pojo2.class.getName());
    assertThat(output.getBuilderModel().getProperties()).hasSize(1);
    PropertyM cityProperty = output.getBuilderModel().getProperties().get(new Key("city", "java.lang.String"));
    assertThat(cityProperty).isNotNull();
  }

  @Test
  public void testAnalyzePojo3() {
    // Given:
    Input input = inputFor(Pojo3.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getPojoType().getName()).isEqualTo(Pojo3.class.getName());
    assertThat(output.getBuilderModel().getProperties()).hasSize(3);
    PropertyM emailProperty = output.getBuilderModel().getProperties().get(new Key("street", "java.lang.String"));
    assertThat(emailProperty).isNotNull();
    PropertyM cityProperty = output.getBuilderModel().getProperties().get(new Key("city", "java.lang.String"));
    assertThat(cityProperty).isNotNull();
    PropertyM postCodeProperty = output.getBuilderModel().getProperties().get(new Key("postCode", "java.lang.String"));
    assertThat(postCodeProperty).isNotNull();
  }

  @Test
  public void testAnalyzePojo4() {
    // Given:
    Input input = inputFor(Pojo4.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getPojoType().getName()).isEqualTo(Pojo4.class.getName());
    assertThat(output.getBuilderModel().getProperties()).hasSize(2);
    PropertyM emailProperty = output.getBuilderModel().getProperties().get(new Key("firstname", "java.lang.String"));
    assertThat(emailProperty).isNotNull();
    PropertyM cityProperty = output.getBuilderModel().getProperties().get(new Key("surname", "java.lang.String"));
    assertThat(cityProperty).isNotNull();
  }

  @Test
  public void testAnalyzePojo5() {
    // Given:
    Input input = inputFor(Pojo5.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getPojoType().getName()).isEqualTo(Pojo5.class.getName());
    assertThat(output.getBuilderModel().getProperties()).hasSize(2);
    PropertyM emailProperty = output.getBuilderModel().getProperties().get(new Key("firstname", "java.lang.String"));
    assertThat(emailProperty).isNotNull();
    PropertyM cityProperty = output.getBuilderModel().getProperties().get(new Key("surname", "java.lang.String"));
    assertThat(cityProperty).isNotNull();
  }

}
