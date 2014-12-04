package net.karneim.pojobuilder.processor.with.generationgap;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.not;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_GenerationGap_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario Should generate {@link AbstractOrderBuilder} and {@link OrderBuilder}.
   */
  @Test
  public void testShouldGenerateAbstractPlayerBuilderAndPlayerBuilder() throws Exception {
    // Given:
    sourceFor(Order.class);
    String abstractBuilderClassname = "net.karneim.pojobuilder.processor.with.generationgap.AbstractOrderBuilder";
    String manualBuilderClassname = "net.karneim.pojobuilder.processor.with.generationgap.OrderBuilder";

    // When:
    boolean success = prj.compile();

    // Then:
    assertThat(success).isTrue();
    String actual1 = getContent(prj.findGeneratedSource(abstractBuilderClassname));
    logDebug(actual1);
    String actual2 = getContent(prj.findGeneratedSource(manualBuilderClassname));
    logDebug(actual2);

    String expected1 = loadResourceFromClasspath("AbstractOrderBuilder.expected.txt");
    assertThat(actual1).isEqualTo(expected1);
    assertThat(prj.findClass(abstractBuilderClassname)).isNotNull();

    String expected2 = loadResourceFromClasspath("OrderBuilder.expected.txt");
    assertThat(actual2).isEqualTo(expected2);
    assertThat(prj.findClass(manualBuilderClassname)).isNotNull();
  }

  /**
   * @throws Exception
   * @scenario Should generate {@link AbstractPlayerBuilder} but not {@link PlayerBuilder} since it already exists.
   */
  @Test
  public void testShouldGenerateOnlyAbstractPlayerBuilderButNotPlayerBuilder() throws Exception {
    // Given:
    sourceFor(Player.class);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .has(generatedSameSourceAs(AbstractPlayerBuilder.class))
        .has(compiled(AbstractPlayerBuilder.class))
        .has(not(generatedSameSourceAs(PlayerBuilder.class)))
        .has(compiled(PlayerBuilder.class));
    assertThat(success).isTrue();
  }

}
