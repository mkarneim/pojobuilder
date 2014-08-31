package net.karneim.pojobuilder.analysis.with.generics;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import net.karneim.pojobuilder.analysis.DirectivesFactory;
import net.karneim.pojobuilder.analysis.Input;
import net.karneim.pojobuilder.analysis.InputFactory;
import net.karneim.pojobuilder.analysis.JavaModelAnalyzer;
import net.karneim.pojobuilder.analysis.JavaModelAnalyzerUtil;
import net.karneim.pojobuilder.analysis.Output;
import net.karneim.pojobuilder.model.PropertyListM.Key;
import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.model.TypeM;
import net.karneim.pojobuilder.testenv.AddToSourceTree;
import net.karneim.pojobuilder.testenv.ProcessingEnvironmentRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({"src/testdata/java"})
public class JavaModelAnalyzer_Generics_Test {

  private ProcessingEnvironment env;
  private Elements elements;
  private InputFactory inputFactory;

  private JavaModelAnalyzer underTest;

  @Before
  public void init() {
    env = ProcessingEnvironmentRunner.getProcessingEnvironment();
    elements = env.getElementUtils();
    JavaModelAnalyzerUtil javaModelAnalyzerUtil = new JavaModelAnalyzerUtil(env.getElementUtils(), env.getTypeUtils());
    inputFactory =
        new InputFactory(env.getTypeUtils(), new DirectivesFactory(env.getElementUtils(), env.getTypeUtils(),
            javaModelAnalyzerUtil));
    underTest =
        new JavaModelAnalyzer(env.getElementUtils(), env.getTypeUtils(), new JavaModelAnalyzerUtil(
            env.getElementUtils(), env.getTypeUtils()));
  }

  @Test
  public void testAnalyze() throws Exception {
    // Given:
    String pojoClassname = ContainerPojo.class.getCanonicalName();
    TypeElement pojoType = elements.getTypeElement(pojoClassname);
    Input input = inputFactory.getInput(pojoType);

    // When:
    Output output = underTest.analyze(input);

    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getPojoType().getName()).isEqualTo(pojoClassname);
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
