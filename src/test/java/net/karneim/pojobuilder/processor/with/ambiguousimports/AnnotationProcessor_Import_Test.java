package net.karneim.pojobuilder.processor.with.ambiguousimports;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.processor.with.ambiguousimports.innerclasses.PojoWithAmbiguousInnerClassImports;
import net.karneim.pojobuilder.processor.with.ambiguousimports.innerclasses.PojoWithAmbiguousInnerClassImportsBuilder;
import net.karneim.pojobuilder.processor.with.ambiguousimports.optional.PojoWithAmbiguousOptionalImports;
import net.karneim.pojobuilder.processor.with.ambiguousimports.optional.PojoWithAmbiguousOptionalImportsBuilder;
import net.karneim.pojobuilder.testenv.JavaProject.Compilation;
import org.junit.Test;

import static net.karneim.pojobuilder.PbAssertions.assertThat;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_Import_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder is created with appropriate import statements.
   */
  @Test
  public void testShouldGenerateBuilderWithAppropriateImportProperties() {
    // Given:
    sourceFor(Pojo.class);
    // When:
    prj.compile();
    // Then:
    assertThat(prj).generatedSameSourceAs(PojoBuilder.class).compiled(PojoBuilder.class)
        .reported(Compilation.Success);
  }

  /**
   * Test for issue <a href="https://github.com/mkarneim/pojobuilder/issues/154">#154</a>.
   */
  @Test
  public void test_ambiguous_inner_class_imports() {
    // given:
    sourceFor(PojoWithAmbiguousInnerClassImports.class);

    // when:
    prj.compile();

    // then:
    assertThat(prj).generatedSameSourceAs(PojoWithAmbiguousInnerClassImportsBuilder.class)
        .compiled(PojoWithAmbiguousInnerClassImportsBuilder.class).reported(Compilation.Success);
  }

  /**
   * * Test for issue <a href="https://github.com/mkarneim/pojobuilder/issues/156">#156</a>.
   */
  @Test
  public void test_ambiguous_optional_import() {
    // given:
    sourceFor(PojoWithAmbiguousOptionalImports.class);

    // when:
    prj.compile();

    // then:
    assertThat(prj).generatedSameSourceAs(PojoWithAmbiguousOptionalImportsBuilder.class)
        .compiled(PojoWithAmbiguousOptionalImportsBuilder.class).reported(Compilation.Success);
  }

}
