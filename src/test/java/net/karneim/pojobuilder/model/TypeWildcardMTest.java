package net.karneim.pojobuilder.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import net.karneim.pojobuilder.testenv.TestDslBase;

/**
 * @author Adrodoc55
 */
public class TypeWildcardMTest extends TestDslBase {

  @Test
  public void test_getGenericTypeDefinition__without_type_parameter() {
    // given:
    TypeWildcardM underTest = new TypeWildcardM();

    // when:
    String act = underTest.getGenericTypeDefinition();

    // then:
    assertThat(act).isEqualTo("?");
  }

  @Test
  public void test_getGenericTypeDefinition__with_upper_bound() {
    // given:
    String name = some($String());
    TypeM underTest = new TypeWildcardM().whichExtends(new TypeVariableM(name));

    // when:
    String act = underTest.getGenericTypeDefinition();

    // then:
    assertThat(act).isEqualTo("? extends " + name);
  }

  @Test
  public void test_getGenericTypeDefinition__with_multiple_upper_bound() {
    // given:
    String name = some($String());
    String name2 = some($String());
    TypeM underTest =
        new TypeWildcardM().whichExtends(new TypeVariableM(name), new TypeVariableM(name2));

    // when:
    String act = underTest.getGenericTypeDefinition();

    // then:
    assertThat(act).isEqualTo("? extends " + name + " & " + name2);
  }

  @Test
  public void test_getGenericTypeDefinition__with_lower_bound() {
    // given:
    String name = some($String());
    TypeM underTest = new TypeWildcardM().whichIsASupertypeOf(new TypeVariableM(name));

    // when:
    String act = underTest.getGenericTypeDefinition();

    // then:
    assertThat(act).isEqualTo("? super " + name);
  }

  @Test
  public void test_getGenericTypeDefinition__with_multiple_lower_bound_last_bound_overrides() {
    // given:
    String name = some($String());
    String name2 = some($String());
    TypeM underTest = new TypeWildcardM()//
        .whichIsASupertypeOf(new TypeVariableM(name))//
        .whichIsASupertypeOf(new TypeVariableM(name2));

    // when:
    String act = underTest.getGenericTypeDefinition();

    // then:
    assertThat(act).isEqualTo("? super " + name2);
  }

  @Test
  public void test_getGenericTypeDefinition__with_multiple_upper_and_lower_bound_last_bound_overrides() {
    // given:
    String name = some($String());
    String name2 = some($String());
    String name3 = some($String());
    TypeM underTest = new TypeWildcardM()//
        .whichExtends(new TypeVariableM(name))//
        .whichExtends(new TypeVariableM(name2))//
        .whichIsASupertypeOf(new TypeVariableM(name3));

    // when:
    String act = underTest.getGenericTypeDefinition();

    // then:
    assertThat(act).isEqualTo("? super " + name3);
  }

  @Test
  public void test_getGenericTypeDefinition__with_multiple_lower_and_upper_bound_last_bound_overrides() {
    // given:
    String name = some($String());
    String name2 = some($String());
    String name3 = some($String());
    TypeM underTest = new TypeWildcardM()//
        .whichIsASupertypeOf(new TypeVariableM(name))//
        .whichExtends(new TypeVariableM(name2))//
        .whichExtends(new TypeVariableM(name3));

    // when:
    String act = underTest.getGenericTypeDefinition();

    // then:
    assertThat(act).isEqualTo("? extends " + name2 + " & " + name3);
  }

}
