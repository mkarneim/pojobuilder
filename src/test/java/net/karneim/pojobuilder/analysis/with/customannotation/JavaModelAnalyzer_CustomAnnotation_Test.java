package net.karneim.pojobuilder.analysis.with.customannotation;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.analysis.Input;
import net.karneim.pojobuilder.analysis.Output;
import net.karneim.pojobuilder.analysis.with.AnalysisTestSupport;
import net.karneim.pojobuilder.testenv.AddToSourceTree;
import net.karneim.pojobuilder.testenv.TestBase;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Annotation Hierarchy
 * <p/>
 * For activating the PojoBuilder processor the user can use any custom annotation which is annotated with
 * {@link GeneratePojoBuilder} itself. This can be done even in multiple hierarchies. The annotation attributes are read
 * in the order of appearance in the source code and override their values.
 */
@AddToSourceTree({TestBase.TESTDATA_DIRECTORY})
public class JavaModelAnalyzer_CustomAnnotation_Test extends AnalysisTestSupport {

  @Test
  public void testAnalyzePojoWithSingleCustomAnnotation() {
    // Given:
    Input input = inputFor(PojoA.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getPojoType().getName()).isEqualTo(PojoA.class.getName());
    assertThat(output.getBuilderModel().getType().getName()).isEqualTo("builder.FluentPojoABuilderA");
  }

  @Test
  public void testAnaylzePojoWithMultipleCustomAnnotations() {
    // Given:
    Input input = inputFor(PojoAB.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getPojoType().getName()).isEqualTo(PojoAB.class.getName());
    assertThat(output.getBuilderModel().getType().getName()).isEqualTo("builder.FluentPojoABBuilderB");
    assertThat(output.getBuilderModel().getCopyMethod()).isNotNull();
  }

  @Test
  public void testAnalyzePojoWithMultipleCustomAnnotationsInAnnotationHierarchy() {
    // Given:
    Input input = inputFor(PojoC.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getPojoType().getName()).isEqualTo(PojoC.class.getName());
    assertThat(output.getBuilderModel().getType().getName()).isEqualTo("builder.FluentPojoCBuilderB");
  }

  @Test
  public void testAnalyzePojoFactoryWithCustomAnnotation() {
    // Given:
    Input input = inputFor(PojoFFactory.class, "createPojoF");
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getPojoType().getName()).isEqualTo(PojoF.class.getName());
    assertThat(output.getBuilderModel().getType().getName()).isEqualTo(
        "net.karneim.pojobuilder.analysis.with.customannotation.FluentPojoFBuilderF");
  }

}
