package net.karneim.pojobuilder.analysis.with.builderinheritance;

import net.karneim.pojobuilder.analysis.Input;
import net.karneim.pojobuilder.analysis.Output;
import net.karneim.pojobuilder.analysis.with.AnalysisTestSupport;
import net.karneim.pojobuilder.analysis.with.baseclass.Pojo;
import net.karneim.pojobuilder.processor.with.builderinheritance.AbstractPojo;
import org.junit.Test;

import javax.lang.model.element.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

public class JavaModelAnalyzer_WithAbstractPojo_Test extends AnalysisTestSupport {

  @Test
  public void testAbstractPojoGeneratesAbstractBuilder() {
    // Given:
    Input input = inputFor(AbstractPojo.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output.getBuilderModel().isAbstract()).isTrue();
    assertThat(output.getBuilderModel().getBuildMethod().getModifiers()).contains(Modifier.ABSTRACT);
  }

  @Test
  public void testAbstractPojoFromFactoryGeneratesConcreteBuilder() {
    // Given:
    Input input = inputFor(AbstractPojo.class, "instantiate");
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output.getBuilderModel().isAbstract()).isFalse();
    assertThat(output.getBuilderModel().getBuildMethod().getModifiers()).doesNotContain(Modifier.ABSTRACT);
  }

}
