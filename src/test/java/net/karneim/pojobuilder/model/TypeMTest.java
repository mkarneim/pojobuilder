package net.karneim.pojobuilder.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import net.karneim.pojobuilder.testenv.TestDslBase;

/**
 * @author Adrodoc55
 */
public class TypeMTest extends TestDslBase {

  @Test
  public void test_getGenericTypeDefinition__without_type_parameter() {
    // given:
    String packageName = some($String());
    String simpleName = some($String());
    TypeM underTest = new TypeM(packageName, simpleName);

    // when:
    String act = underTest.getGenericTypeDefinition();

    // then:
    assertThat(act).isEqualTo(packageName + "." + simpleName);
  }

  @Test
  public void test_getGenericTypeDefinition__with_type_parameter() {
    // given:
    String packageName = some($String());
    String simpleName = some($String());
    String packageName2 = some($String());
    String simpleName2 = some($String());
    TypeM underTest =
        new TypeM(packageName, simpleName).withTypeParameter(new TypeM(packageName2, simpleName2));

    // when:
    String act = underTest.getGenericTypeDefinition();

    // then:
    assertThat(act)
        .isEqualTo(packageName + "." + simpleName + "<" + packageName2 + "." + simpleName2 + ">");
  }

  @Test
  public void test_getGenericType__without_type_parameter() {
    // given:
    String packageName = some($String());
    String simpleName = some($String());
    TypeM underTest = new TypeM(packageName, simpleName);

    // when:
    String act = underTest.getGenericType();

    // then:
    assertThat(act).isEqualTo(packageName + "." + simpleName);
  }

  @Test
  public void test_getGenericType__with_type_parameter() {
    // given:
    String packageName = some($String());
    String simpleName = some($String());
    String packageName2 = some($String());
    String simpleName2 = some($String());
    TypeM underTest =
        new TypeM(packageName, simpleName).withTypeParameter(new TypeM(packageName2, simpleName2));

    // when:
    String act = underTest.getGenericType();

    // then:
    assertThat(act)
        .isEqualTo(packageName + "." + simpleName + "<" + packageName2 + "." + simpleName2 + ">");
  }

}
