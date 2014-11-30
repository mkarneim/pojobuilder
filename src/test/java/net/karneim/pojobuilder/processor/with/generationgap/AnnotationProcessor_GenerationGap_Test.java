package net.karneim.pojobuilder.processor.with.generationgap;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
    String pojoClassname = Order.class.getName();
    String abstractBuilderClassname = "net.karneim.pojobuilder.processor.with.generationgap.AbstractOrderBuilder";
    String manualBuilderClassname = "net.karneim.pojobuilder.processor.with.generationgap.OrderBuilder";

    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojoClassname));

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
    String pojoClassname = Player.class.getName();
    String abstractBuilderClassname = AbstractPlayerBuilder.class.getName();
    String manualBuilderClassname = PlayerBuilder.class.getName();

    prj.addSourceFile(getSourceFilename(TESTDATA_DIRECTORY, pojoClassname));

    // When:
    boolean success = prj.compile();

    // Then:
    assertThat(success).isTrue();
    String codeOfAbstractBuilder = getContent(prj.findGeneratedSource(abstractBuilderClassname));
    logDebug(codeOfAbstractBuilder);
    String codeOfManualBuilder = getContent(prj.findGeneratedSource(manualBuilderClassname));
    logDebug(codeOfManualBuilder);

    String expected1 = loadResourceFromFilesystem(TESTDATA_DIRECTORY, getSourceFilename(abstractBuilderClassname));
    assertThat(codeOfAbstractBuilder).isEqualTo(expected1);
    assertThat(prj.findClass(abstractBuilderClassname)).isNotNull();

    assertThat(codeOfManualBuilder).isNull(); // <- not overwritten since it already exists
    assertThat(prj.findClass(manualBuilderClassname)).isNotNull();
  }

}
