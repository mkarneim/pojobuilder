package net.karneim.pojobuilder.testenv;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileHelper {

  public enum FakeAnnotationReplacement {
    CLASSIC("javax.annotation.Generated"), JAVA_9("javax.annotation.processing.Generated");

    private final String text;

    private FakeAnnotationReplacement(String text) {
      this.text = text;
    }

    public String getText() {
      return text;
    }

  };

  private FakeAnnotationReplacement fakeAnnotationReplacement;

  public FileHelper(FakeAnnotationReplacement fakeAnnotationReplacement) {
    this.fakeAnnotationReplacement = fakeAnnotationReplacement;
  }

  public String loadJavaSourceFromFilesystem(String directory, String javaClassName) throws IOException {
    return loadJavaSourceFromFilesystem(directory, javaClassName, Extension.JAVA);
  }

  public String loadJavaSourceFromFilesystem(String directory, String javaClassName, Extension extension)
      throws IOException {
    String sourceFilename = getSourceFilename(javaClassName, extension);
    String result = loadResourceFromFilesystem(directory, sourceFilename);
    result = replaceJavaxFakeAnnotationGenerated(result);
    return result;
  }

  public String getSourceFilename(String srcDir, String fullQualifiedClassname, Extension extension) {
    return new File(srcDir, getSourceFilename(fullQualifiedClassname, extension)).getPath();
  }

  public String getContent(java.io.InputStream is) {
    if (is == null) {
      return null;
    }
    @SuppressWarnings("resource")
    java.util.Scanner scanner = new java.util.Scanner(is, "UTF-8").useDelimiter("\\A");
    try {
      String result = scanner.hasNext() ? scanner.next() : "";
      result = result.replaceAll("\r\n", "\n").replaceAll(String.valueOf((char) 65279), "");
      return result;
    } finally {
      scanner.close();
    }
  }

  private String getSourceFilename(String fullQualifiedClassname, Extension extension) {
    String result = fullQualifiedClassname.replace('.', '/').concat(extension.asString());
    return result;
  }

  private String replaceJavaxFakeAnnotationGenerated(String javaSource) {
    return javaSource.replace("import javaxfake.annotation.Generated;",
        "import " + fakeAnnotationReplacement.getText() + ";");
  }

  private String loadResourceFromFilesystem(String directory, String name) throws IOException {
    File file = new File(directory, name);
    return loadResource(file);
  }

  private String loadResource(File file) throws FileNotFoundException {
    return getContent(new FileInputStream(file));
  }

}
