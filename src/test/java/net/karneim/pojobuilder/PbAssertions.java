package net.karneim.pojobuilder;

import net.karneim.pojobuilder.processor.with.JavaProjectAssert;
import net.karneim.pojobuilder.testenv.JavaProject;
import net.karneim.pojobuilder.testenv.TestBase;
import org.assertj.core.api.Assertions;

/**
 * As per docs but rather dodgy common class to provide single entry point for all assertion imports.
 */
public class PbAssertions extends Assertions {

  public static JavaProjectAssert assertThat(JavaProject actual) {
    return new JavaProjectAssert(actual, TestBase.TESTDATA_DIRECTORY);
  }

}
