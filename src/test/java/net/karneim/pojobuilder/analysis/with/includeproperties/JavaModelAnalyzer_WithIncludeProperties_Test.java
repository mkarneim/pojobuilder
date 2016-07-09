package net.karneim.pojobuilder.analysis.with.includeproperties;

import static org.assertj.core.api.Assertions.assertThat;
import net.karneim.pojobuilder.analysis.Input;
import net.karneim.pojobuilder.analysis.Output;
import net.karneim.pojobuilder.analysis.with.AnalysisTestSupport;
import net.karneim.pojobuilder.model.PropertyListM.Key;
import net.karneim.pojobuilder.model.PropertyM;

import org.junit.Test;

public class JavaModelAnalyzer_WithIncludeProperties_Test extends AnalysisTestSupport {

  @Test
  public void testAnalyzePojo1() {
    // Given:
    Input input = inputFor(Pojo1.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getPojoType().getName()).isEqualTo(Pojo1.class.getName());
    assertThat(output.getBuilderModel().getProperties()).hasSize(2);
    PropertyM firstnameProperty =
        output.getBuilderModel().getProperties().get(new Key("firstname", "java.lang.String"));
    assertThat(firstnameProperty).isNotNull();
    PropertyM surnameProperty = output.getBuilderModel().getProperties().get(new Key("surname", "java.lang.String"));
    assertThat(surnameProperty).isNotNull();
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
    PropertyM streetProperty = output.getBuilderModel().getProperties().get(new Key("street", "java.lang.String"));
    assertThat(streetProperty).isNotNull();
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
    assertThat(output.getBuilderModel().getProperties()).hasSize(1);
    PropertyM countryProperty = output.getBuilderModel().getProperties().get(new Key("country", "java.lang.String"));
    assertThat(countryProperty).isNotNull();
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
    assertThat(output.getBuilderModel().getProperties()).hasSize(1);
    PropertyM hobbiesProperty = output.getBuilderModel().getProperties().get(new Key("hobbies", "java.lang.String[]"));
    assertThat(hobbiesProperty).isNotNull();
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
    assertThat(output.getBuilderModel().getProperties()).hasSize(1);
    PropertyM skillsProperty = output.getBuilderModel().getProperties().get(new Key("skills", "java.util.List<java.lang.String>"));
    assertThat(skillsProperty).isNotNull();
  }

}
