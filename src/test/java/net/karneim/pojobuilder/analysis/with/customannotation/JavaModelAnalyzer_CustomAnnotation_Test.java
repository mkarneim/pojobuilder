package net.karneim.pojobuilder.analysis.with.customannotation;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.analysis.Input;
import net.karneim.pojobuilder.analysis.Output;
import net.karneim.pojobuilder.analysis.with.AnalysisTestSupport;
import net.karneim.pojobuilder.testenv.AddToSourceTree;
import org.junit.Test;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Annotation Hierarchy
 * <p/>
 * For activating the PojoBuilder processor the user can use any custom annotation which is annotated with
 * {@link GeneratePojoBuilder} itself. This can be done even in multiple hierarchies. The annotation attributes are read
 * in the order of appearance in the source code and override their values.
 */
@AddToSourceTree({"src/testdata/java"})
public class JavaModelAnalyzer_CustomAnnotation_Test extends AnalysisTestSupport {

  @Test
  public void testAnalyzePojoWithSingleCustomAnnotation() throws Exception {
    // Given:
    String pojoClassname = PojoA.class.getCanonicalName();
    TypeElement pojoType = elements.getTypeElement(pojoClassname);
    Input input = inputFactory.getInput(pojoType);

    // When:
    Output output = underTest.analyze(input);

    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getPojoType().getName()).isEqualTo(pojoClassname);
    assertThat(output.getBuilderModel().getType().getName()).isEqualTo("builder.FluentPojoABuilderA");
  }

  @Test
  public void testAnaylzePojoWithMultipleCustomAnnotations() throws Exception {
    // Given:
    String pojoClassname = PojoAB.class.getCanonicalName();
    TypeElement pojoType = elements.getTypeElement(pojoClassname);
    Input input = inputFactory.getInput(pojoType);

    // When:
    Output output = underTest.analyze(input);

    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getPojoType().getName()).isEqualTo(pojoClassname);
    assertThat(output.getBuilderModel().getType().getName()).isEqualTo("builder.FluentPojoABBuilderB");
    assertThat(output.getBuilderModel().getCopyMethod()).isNotNull();
  }

  @Test
  public void testAnalyzePojoWithMultipleCustomAnnotationsInAnnotationHierarchy() throws Exception {
    // Given:
    String pojoClassname = PojoC.class.getCanonicalName();
    TypeElement pojoType = elements.getTypeElement(pojoClassname);
    Input input = inputFactory.getInput(pojoType);

    // When:
    Output output = underTest.analyze(input);

    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getPojoType().getName()).isEqualTo(pojoClassname);
    assertThat(output.getBuilderModel().getType().getName()).isEqualTo("builder.FluentPojoCBuilderB");
  }

  @Test
  public void testAnalyzePojoFactoryWithCustomAnnotation() throws Exception {
    // Given:
    String pojoClassname = PojoF.class.getCanonicalName();
    String factoryClassname = PojoFFactory.class.getCanonicalName();
    TypeElement factoryType = elements.getTypeElement(factoryClassname);
    List<ExecutableElement> methods = ElementFilter.methodsIn(elements.getAllMembers(factoryType));
    ExecutableElement methodEl = getFirstMethodByName("createPojoF", methods);
    Input input = inputFactory.getInput(methodEl);

    // When:
    Output output = underTest.analyze(input);

    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getPojoType().getName()).isEqualTo(pojoClassname);
    assertThat(output.getBuilderModel().getType().getName()).isEqualTo(
        "net.karneim.pojobuilder.analysis.with.customannotation.FluentPojoFBuilderF");
  }

  private static ExecutableElement getFirstMethodByName(String name, List<ExecutableElement> methods) {
    for (ExecutableElement e : methods) {
      if (name.equals(e.getSimpleName().toString())) {
        return e;
      }
    }
    return null;
  }

}
