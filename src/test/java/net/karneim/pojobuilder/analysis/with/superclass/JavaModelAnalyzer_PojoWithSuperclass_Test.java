package net.karneim.pojobuilder.analysis.with.superclass;

import net.karneim.pojobuilder.analysis.Input;
import net.karneim.pojobuilder.analysis.Output;
import net.karneim.pojobuilder.analysis.with.AnalysisTestSupport;
import net.karneim.pojobuilder.model.PropertyListM.Key;
import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.model.TypeM;
import org.junit.Test;

import javax.lang.model.element.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

public class JavaModelAnalyzer_PojoWithSuperclass_Test extends AnalysisTestSupport {

  @Test
  public void testAnalyzeWithPojoExtendsClassWithPublicField()
  {
    // Given:
    Input input = inputFor(SubclassPojo1.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getPojoType().getName()).isEqualTo(SubclassPojo1.class.getName());
    TypeM builderType = output.getBuilderModel().getType();
    assertThat(builderType).isNotNull();
    assertThat(builderType.getName())
        .isEqualTo("net.karneim.pojobuilder.analysis.with.superclass.SubclassPojo1Builder");
    assertThat(output.getBuilderModel().getProperties()).hasSize(3);
    PropertyM nameProperty = output.getBuilderModel().getProperties().get(new Key("name", "java.lang.String"));
    assertThat(nameProperty).isNotNull();
    assertThat(nameProperty.getFieldAccess()).isNotNull();
    assertThat(nameProperty.getFieldAccess().getModifier()).contains(Modifier.PUBLIC);

    PropertyM ageProperty = output.getBuilderModel().getProperties().get(new Key("age", "int"));
    assertThat(ageProperty).isNotNull();
    assertThat(ageProperty.getFieldAccess()).isNotNull();
    assertThat(ageProperty.getFieldAccess().getModifier()).contains(Modifier.PUBLIC);

    PropertyM hairColorProperty = output.getBuilderModel().getProperties().get(new Key("hairColor", "java.awt.Color"));
    assertThat(hairColorProperty).isNotNull();
    assertThat(hairColorProperty.getFieldAccess()).isNotNull();
    assertThat(hairColorProperty.getFieldAccess().getModifier()).contains(Modifier.PUBLIC);
  }

  @Test
  public void testAnalyzeWithPojoWhichExtendsClassInSamePackage()
  {
    // Given:
    Input input = inputFor(SubclassPojo2.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getPojoType().getName()).isEqualTo(SubclassPojo2.class.getName());
    TypeM builderType = output.getBuilderModel().getType();
    assertThat(builderType).isNotNull();
    assertThat(builderType.getName())
        .isEqualTo("net.karneim.pojobuilder.analysis.with.superclass.SubclassPojo2Builder");
    assertThat(output.getBuilderModel().getProperties()).hasSize(3);
    PropertyM visibleMemberProperty = output.getBuilderModel().getProperties().get(new Key("visibleMember", "int"));
    assertThat(visibleMemberProperty).isNotNull();
    assertThat(visibleMemberProperty.getFieldAccess()).isNotNull();
    assertThat(visibleMemberProperty.getFieldAccess().getModifier()).contains(Modifier.PUBLIC);

    PropertyM protectedMemberProperty = output.getBuilderModel().getProperties().get(new Key("protectedMember", "float"));
    assertThat(protectedMemberProperty).isNotNull();
    assertThat(protectedMemberProperty.getFieldAccess()).isNotNull();
    assertThat(protectedMemberProperty.getFieldAccess().getModifier()).contains(Modifier.PROTECTED);

    PropertyM nameMemberProperty = output.getBuilderModel().getProperties().get(new Key("name", "java.lang.String"));
    assertThat(nameMemberProperty).isNotNull();
    assertThat(nameMemberProperty.getFieldAccess()).isNull();
    assertThat(nameMemberProperty.getGetterMethod().getModifiers()).contains(Modifier.PROTECTED);
    assertThat(nameMemberProperty.getSetterMethod().getModifiers()).contains(Modifier.PROTECTED);

    assertThat(output.getBuilderModel().getProperties().get(new Key("hiddenMember", "float"))).isNull();
  }

  @Test
  public void testAnalyzeWithPojoWhichExtendsClassInOtherPackage()
  {
    // Given:
    Input input = inputFor(SubclassPojo3.class);
    // When:
    Output output = underTest.analyze(input);
    // Then:
    assertThat(output).isNotNull();
    assertThat(output.getBuilderModel().getPojoType().getName()).isEqualTo(SubclassPojo3.class.getName());
    TypeM builderType = output.getBuilderModel().getType();
    assertThat(builderType).isNotNull();
    assertThat(builderType.getName())
        .isEqualTo("net.karneim.pojobuilder.analysis.with.superclass.SubclassPojo3Builder");
    assertThat(output.getBuilderModel().getProperties()).hasSize(1);
    PropertyM visibleMemberProperty = output.getBuilderModel().getProperties().get(new Key("visibleMember", "int"));
    assertThat(visibleMemberProperty).isNotNull();
    assertThat(visibleMemberProperty.getFieldAccess()).isNotNull();
    assertThat(visibleMemberProperty.getFieldAccess().getModifier()).contains(Modifier.PUBLIC);
    assertThat(output.getBuilderModel().getProperties().get(new Key("hiddenMember", "float"))).isNull();
  }

}
