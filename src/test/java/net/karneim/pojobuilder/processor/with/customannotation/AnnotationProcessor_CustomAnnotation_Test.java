package net.karneim.pojobuilder.processor.with.customannotation;

import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.processor.with.customannotation.builder.FluentPojoABBuilderB;
import net.karneim.pojobuilder.processor.with.customannotation.builder.FluentPojoABuilderA;
import net.karneim.pojobuilder.processor.with.customannotation.builder.FluentPojoCBuilderB;
import net.karneim.pojobuilder.testenv.JavaProject.Compilation;
import org.junit.Test;

import static net.karneim.pojobuilder.PbAssertions.assertThat;

public class AnnotationProcessor_CustomAnnotation_Test extends ProcessorTestSupport {

  @Test
  public void testShouldGenerateBuilderForPojoWithSingleCustomAnnotation() throws Exception {
    // Given:
    sourceFor(PojoA.class);
    sourceFor(MyCustomAnnotationA.class);
    String builderClassname = FluentPojoABuilderA.class.getName();
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(FluentPojoABuilderA.class)
        .compiled(FluentPojoABuilderA.class)
        .reported(Compilation.Success);
  }

  @Test
  public void testShouldGenerateBuilderForPojoWithMultipleCustomAnnotations() throws Exception {
    // Given:
    sourceFor(PojoAB.class);
    sourceFor(MyCustomAnnotationA.class);
    sourceFor(MyCustomAnnotationB.class);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(FluentPojoABBuilderB.class)
        .compiled(FluentPojoABBuilderB.class)
        .reported(Compilation.Success);
  }

  @Test
  public void testShouldGenerateBuilderForPojoWithMultipleCustomAnnotationsInAnnotationHierarchy() throws Exception {
    // Given:
    sourceFor(PojoC.class);
    sourceFor(MyCustomAnnotationA.class);
    sourceFor(MyCustomAnnotationB.class);
    sourceFor(MyCustomAnnotationC.class);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(FluentPojoCBuilderB.class)
        .compiled(FluentPojoCBuilderB.class)
        .reported(Compilation.Success);
  }

}
