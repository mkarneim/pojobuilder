package net.karneim.pojobuilder.processor.with.optionals;

import static net.karneim.pojobuilder.PbAssertions.assertThat;

import org.junit.Test;

import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.testenv.JavaProject.Compilation;

/**
 * @feature The {@link net.karneim.pojobuilder.processor.AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_WithGuavaOptionals_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder contains withParam(Optional&lt;X&gt;) methods
   */
  @Test
  public void testShouldGenerateWithOptionalMethodsForBasicFieldAccess() {
    // Given:
    sourceFor(PojoWithGuavaOptionalBasicFieldAccess.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(PojoWithGuavaOptionalBasicFieldAccessBuilder.class)
        .compiled(PojoWithGuavaOptionalBasicFieldAccessBuilder.class).reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder contains withParam(Optional&lt;X&gt;) methods
   */
  @Test
  public void testShouldGenerateWithOptionalMethodsForBasicFieldAccessWithBuilderProperties() {
    // Given:
    sourceFor(PojoWithGuavaOptionalBasicFieldAccessWithBuilderProperties.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(PojoWithGuavaOptionalBasicFieldAccessWithBuilderPropertiesBuilder.class)
        .compiled(PojoWithGuavaOptionalBasicFieldAccessWithBuilderPropertiesBuilder.class)
        .reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder withParam(Optional&lt;X&gt;) methods should not generated if the member is already an
   *           Optional
   */
  @Test
  public void testShouldGenerateWithOptionalMethodsForOptionalFieldAccess() {
    // Given:
    sourceFor(PojoWithGuavaOptionalOptionalFieldAccess.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(PojoWithGuavaOptionalOptionalFieldAccessBuilder.class)
        .compiled(PojoWithGuavaOptionalOptionalFieldAccessBuilder.class).reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder withParam(Optional&lt;X&gt;) methods should not generated if the member is already an
   *           Optional
   */
  @Test
  public void testShouldGenerateWithOptionalMethodsForOptionalFieldAccessWithBuilderProperties() {
    // Given:
    sourceFor(PojoWithGuavaOptionalOptionalFieldAccessWithBuilderProperties.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(PojoWithGuavaOptionalOptionalFieldAccessWithBuilderPropertiesBuilder.class)
        .compiled(PojoWithGuavaOptionalOptionalFieldAccessWithBuilderPropertiesBuilder.class)
        .reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder contains withParam(Optional&lt;X&gt;) methods
   */
  @Test
  public void testShouldGenerateWithOptionalMethodsForBasicSetters() {
    // Given:
    sourceFor(PojoWithGuavaOptionalBasicSetters.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(PojoWithGuavaOptionalBasicSettersBuilder.class)
        .compiled(PojoWithGuavaOptionalBasicSettersBuilder.class).reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder contains withParam(Optional&lt;X&gt;) methods
   */
  @Test
  public void testShouldGenerateWithOptionalMethodsForBasicSettersWithBuilderProperties() {
    // Given:
    sourceFor(PojoWithGuavaOptionalBasicSettersWithBuilderProperties.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(PojoWithGuavaOptionalBasicSettersWithBuilderPropertiesBuilder.class)
        .compiled(PojoWithGuavaOptionalBasicSettersWithBuilderPropertiesBuilder.class).reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder contains withParam(Optional&lt;X&gt;) methods
   */
  @Test
  public void testShouldGenerateWithOptionalMethodsForOptionalSetters() {
    // Given:
    sourceFor(PojoWithGuavaOptionalOptionalSetters.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(PojoWithGuavaOptionalOptionalSettersBuilder.class)
        .compiled(PojoWithGuavaOptionalOptionalSettersBuilder.class).reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder contains withParam(Optional&lt;X&gt;) methods
   */
  @Test
  public void testShouldGenerateWithOptionalMethodsForOptionalSettersWithBuilderProperties() {
    // Given:
    sourceFor(PojoWithGuavaOptionalOptionalSettersWithBuilderProperties.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(PojoWithGuavaOptionalOptionalSettersWithBuilderPropertiesBuilder.class)
        .compiled(PojoWithGuavaOptionalOptionalSettersWithBuilderPropertiesBuilder.class).reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder contains withParam(Optional&lt;X&gt;) methods
   */
  @Test
  public void testShouldGenerateWithOptionalMethodsForBasicConstructor() {
    // Given:
    sourceFor(PojoWithGuavaOptionalBasicConstructor.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(PojoWithGuavaOptionalBasicConstructorBuilder.class)
        .compiled(PojoWithGuavaOptionalBasicConstructorBuilder.class).reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder contains withParam(Optional&lt;X&gt;) methods
   */
  @Test
  public void testShouldGenerateWithOptionalMethodsForBasicConstructorWithBuilderProperties() {
    // Given:
    sourceFor(PojoWithGuavaOptionalBasicConstructorWithBuilderProperties.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(PojoWithGuavaOptionalBasicConstructorWithBuilderPropertiesBuilder.class)
        .compiled(PojoWithGuavaOptionalBasicConstructorWithBuilderPropertiesBuilder.class)
        .reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder contains withParam(Optional&lt;X&gt;) methods
   */
  @Test
  public void testShouldGenerateWithOptionalMethodsForOptionalConstructor() {
    // Given:
    sourceFor(PojoWithGuavaOptionalOptionalConstructor.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(PojoWithGuavaOptionalOptionalConstructorBuilder.class)
        .compiled(PojoWithGuavaOptionalOptionalConstructorBuilder.class).reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder contains withParam(Optional&lt;X&gt;) methods
   */
  @Test
  public void testShouldGenerateWithOptionalMethodsForOptionalConstructorWithBuilderProperties() {
    // Given:
    sourceFor(PojoWithGuavaOptionalOptionalConstructorWithBuilderProperties.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(PojoWithGuavaOptionalOptionalConstructorWithBuilderPropertiesBuilder.class)
        .compiled(PojoWithGuavaOptionalOptionalConstructorWithBuilderPropertiesBuilder.class)
        .reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder contains withParam(Optional&lt;X&gt;) methods
   */
  @Test
  public void testShouldGenerateWithOptionalMethodsForBasicFactory() {
    // Given:
    sourceFor(PojoWithGuavaOptionalBasicFactory.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(PojoWithGuavaOptionalBasicFactoryBuilder.class)
        .compiled(PojoWithGuavaOptionalBasicFactoryBuilder.class).reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder contains withParam(Optional&lt;X&gt;) methods
   */
  @Test
  public void testShouldGenerateWithOptionalMethodsForBasicFactoryWithBuilderProperties() {
    // Given:
    sourceFor(PojoWithGuavaOptionalBasicFactoryWithBuilderProperties.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(PojoWithGuavaOptionalBasicFactoryWithBuilderPropertiesBuilder.class)
        .compiled(PojoWithGuavaOptionalBasicFactoryWithBuilderPropertiesBuilder.class).reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder contains withParam(Optional&lt;X&gt;) methods
   */
  @Test
  public void testShouldGenerateWithOptionalMethodsForOptionalFactory() {
    // Given:
    sourceFor(PojoWithGuavaOptionalOptionalFactory.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(PojoWithGuavaOptionalOptionalFactoryBuilder.class)
        .compiled(PojoWithGuavaOptionalOptionalFactoryBuilder.class).reported(Compilation.Success);
  }

  /**
   * @throws Exception
   * @scenario the builder contains withParam(Optional&lt;X&gt;) methods
   */
  @Test
  public void testShouldGenerateWithOptionalMethodsForOptionalFactoryWithBuilderProperties() {
    // Given:
    sourceFor(PojoWithGuavaOptionalOptionalFactoryWithBuilderProperties.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(PojoWithGuavaOptionalOptionalFactoryWithBuilderPropertiesBuilder.class)
        .compiled(PojoWithGuavaOptionalOptionalFactoryWithBuilderPropertiesBuilder.class).reported(Compilation.Success);
  }

}
