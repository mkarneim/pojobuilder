package net.karneim.pojobuilder.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import net.karneim.pojobuilder.testenv.TestDslBase;

/**
 * @author Adrodoc55
 */
public class TypeVariableMTest extends TestDslBase {

  @Test
  public void test_getGenericTypeDefinition__without_type_parameter() {
    // given:
    String name = some($String());
    TypeM underTest = new TypeVariableM(name);

    // when:
    String act = underTest.getGenericTypeDefinition();

    // then:
    assertThat(act).isEqualTo(name);
  }

  @Test
  public void test_getGenericTypeDefinition__with_upper_bound() {
    // given:
    String name = some($String());
    String name2 = some($String());
    TypeM underTest = new TypeVariableM(name).whichExtends(new TypeVariableM(name2));

    // when:
    String act = underTest.getGenericTypeDefinition();

    // then:
    assertThat(act).isEqualTo(name + " extends " + name2);
  }

  @Test
  public void test_getGenericTypeDefinition__with_multiple_upper_bound() {
    // given:
    String name = some($String());
    String name2 = some($String());
    String name3 = some($String());
    TypeM underTest =
        new TypeVariableM(name).whichExtends(new TypeVariableM(name2), new TypeVariableM(name3));

    // when:
    String act = underTest.getGenericTypeDefinition();

    // then:
    assertThat(act).isEqualTo(name + " extends " + name2 + " & " + name3);
  }

  @Test
  public void test_getGenericType__without_type_parameter() {
    // given:
    String name = some($String());
    TypeM underTest = new TypeVariableM(name);

    // when:
    String act = underTest.getGenericType();

    // then:
    assertThat(act).isEqualTo(name);
  }

  @Test
  public void test_getGenericType__with_upper_bound() {
    // given:
    String name = some($String());
    String name2 = some($String());
    TypeM underTest = new TypeVariableM(name).whichExtends(new TypeVariableM(name2));

    // when:
    String act = underTest.getGenericType();

    // then:
    assertThat(act).isEqualTo(name);
  }

  @Test
  public void test_getGenericType__with_multiple_upper_bound() {
    // given:
    String name = some($String());
    String name2 = some($String());
    String name3 = some($String());
    TypeM underTest =
        new TypeVariableM(name).whichExtends(new TypeVariableM(name2), new TypeVariableM(name3));

    // when:
    String act = underTest.getGenericType();

    // then:
    assertThat(act).isEqualTo(name);
  }

}
