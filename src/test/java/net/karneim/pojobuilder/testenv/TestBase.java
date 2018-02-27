package net.karneim.pojobuilder.testenv;

import java.io.IOException;

import net.karneim.pojobuilder.testenv.FileHelper.FakeAnnotationReplacement;

public abstract class TestBase {
  public static final String TESTDATA_DIRECTORY = "src/testdata/java";

  private static final boolean DEBUG_LOG_ENABLED = false;

  private final FileHelper fileHelper;

  public TestBase() {
    fileHelper =
        new FileHelper(hasJava9Annotation() ? FakeAnnotationReplacement.JAVA_9 : FakeAnnotationReplacement.CLASSIC);
  }

  private static boolean hasJava9Annotation() {
    try {
      Class.forName(FakeAnnotationReplacement.JAVA_9.getText());
    } catch (ClassNotFoundException e) {
      return false;
    }
    return true;
  }

  protected void logDebug(String message) {
    if (DEBUG_LOG_ENABLED) {
      System.out.println(message);
    }
  }

  protected String loadResourceFromClasspath(String name) throws IOException {
    return fileHelper.getContent(this.getClass().getResourceAsStream(name));
  }

  protected String loadJavaSourceFromFilesystem(String directory, String javaClassName) throws IOException {
    return fileHelper.loadJavaSourceFromFilesystem(directory, javaClassName, Extension.JAVA);
  }

  protected String loadJavaSourceFromFilesystem(String directory, String javaClassName, Extension extension)
      throws IOException {
    return fileHelper.loadJavaSourceFromFilesystem(directory, javaClassName, extension);
  }

  protected String getSourceFilename(String srcDir, String fullQualifiedClassname) {
    return fileHelper.getSourceFilename(srcDir, fullQualifiedClassname, Extension.JAVA);
  }

  protected String getContent(java.io.InputStream is) {
    return fileHelper.getContent(is);
  }
}
