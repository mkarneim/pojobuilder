package net.karneim.pojobuilder.testenv;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.Processor;
import javax.lang.model.SourceVersion;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

import com.google.common.base.Throwables;

/**
 * The {@link JavaProject} is a driver for controlling a simple java project. This includes adding source files,
 * enabling annotation processors, compiling and accessing the generated classes.
 *
 * @author Michael Karneim
 */
public class JavaProject {

  public enum Compilation {
    NotStarted, Success, Failure
  }

  private Compilation status = Compilation.NotStarted;

  private final File workingDirectory;
  private final File outputRoot;
  private final File localTempDir;

  private final List<File> sourceFiles = new ArrayList<File>();
  private final List<String> classnamesForProcessing = new ArrayList<String>();
  private final List<Class<? extends Processor>> processorClasses = new ArrayList<Class<? extends Processor>>();
  private final List<Processor> processors = new ArrayList<Processor>();
  private final JavaCompiler compiler;
  private final DiagnosticCollector<JavaFileObject> diagnostics;
  private final StandardJavaFileManager fileManager;


  /**
   * Creates a new java project with the specified working directory.
   *
   * @param workingDirectory will be used to store generated source and class files
   */
  public JavaProject(File workingDirectory) {
    this.workingDirectory = workingDirectory;
    this.outputRoot = new File(workingDirectory, "output");
    this.outputRoot.mkdir();
    this.localTempDir = new File(workingDirectory, "temp");
    this.localTempDir.mkdir();
    this.compiler = ToolProvider.getSystemJavaCompiler();
    this.diagnostics = new DiagnosticCollector<JavaFileObject>();
    this.fileManager = compiler.getStandardFileManager(diagnostics, null, null);

    try {
      // Define the output locations
      fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(outputRoot));
      fileManager.setLocation(StandardLocation.SOURCE_OUTPUT, Arrays.asList(outputRoot));
    } catch (IOException e) {
      throw new UndeclaredThrowableException(e);
    }

    Set<SourceVersion> v = this.compiler.getSourceVersions();
    System.out.println(v);
  }

  public boolean isSourceVersionJava9OrGreater() {
    try {
      SourceVersion version = SourceVersion.valueOf("RELEASE_9");
      return this.compiler.getSourceVersions().contains(version);
    } catch (IllegalArgumentException e) {
      return false;
    }
  }

  /**
   * Deletes this java project by deleting the working directory.
   */
  public void delete() {
    status = Compilation.NotStarted;
    Util.deleteDir(workingDirectory);
  }

  /**
   * Returns the annotation processors that will be used during the compilation task.
   *
   * @return the annotation processors that will be used during the compilation task
   */
  public List<Processor> getProcessors() {
    return processors;
  }

  /**
   * Returns the annotation processor classes that will be used during the compilation task.
   *
   * @return the annotation processor classes that will be used
   */
  public List<Class<? extends Processor>> getProcessorClasses() {
    return processorClasses;
  }

  /**
   * Returns the directory that contains the generated source and class files.
   *
   * @return the directory that contains the generated source and class files
   */
  public File getOutputRoot() {
    return outputRoot;
  }

  public List<Diagnostic<? extends JavaFileObject>> getDiagnostics() {
    return diagnostics.getDiagnostics();
  }

  /**
   * Adds the file with the given relative filename to the source tree. If the file is a directory then all files inside
   * that directory are added (recursively).
   *
   * @param filepath the filepath must be absolute or relative to the current directory (that is the directory this JVM
   *        has been started from as stored in System.getProperty("user.dir"))
   */
  public void addSourceFile(String filepath) {
    File file = new File(filepath);
    if (file.isAbsolute()) {
      addSourceFile(file);
    } else {
      String curDir = System.getProperty("user.dir");
      File absfile = new File(curDir, filepath);
      addSourceFile(absfile);
    }
  }

  /**
   * Adds a source file for the given qualified class name and the given content to the source tree.
   *
   * @param qualifiedClassname the qualified name of the Java class
   * @param content the source code of the Java class
   * @throws IOException
   */
  public void addSourceFile(String qualifiedClassname, String content) throws IOException {
    File file = new File(localTempDir, getSourceFilename(qualifiedClassname));
    file.getParentFile().mkdirs();
    PrintWriter out = new PrintWriter(file);
    try {
      out.print(content);
    } finally {
      out.close();
    }
    addSourceFile(file);
  }

  protected static String getSourceFilename(String fullQualifiedClassname) {
    String result = fullQualifiedClassname.replace('.', '/').concat(".java");
    return result;
  }

  private void addSourceFile(File aFile) {
    if (aFile.exists() == false) {
      return;
    }
    if (aFile.isDirectory()) {
      File[] files = aFile.listFiles();
      for (File file : files) {
        addSourceFile(file);
      }
    } else if (aFile.getName().endsWith(".java")) {
      sourceFiles.add(aFile);
    }
  }

  /**
   * Adds the (compiled) class with the given full qualified name to the list of classes, that should be processed by
   * the annotation processor(s) without being compiled first. Make sure that the class is available in the current
   * class path.
   *
   * @param name the full qualified class name of the class
   */
  public void addClassnameForProcessing(String name) {
    classnamesForProcessing.add(name);
  }

  /**
   * Loads a class with the given class name from this project's output directory and returns it.
   *
   * @param classname
   * @return the class with the given class name, loaded from this project's output directory
   * @throws ClassNotFoundException
   */
  public Class<?> findClass(String classname) throws ClassNotFoundException {
    ClassLoader cl = fileManager.getClassLoader(StandardLocation.CLASS_OUTPUT);
    Class<?> result = cl.loadClass(classname);
    return result;
  }

  /**
   * Returns an {@link InputStream} for reading the source code of the specified java class with the given name from
   * this project's output directory.
   *
   * @param classname
   * @return the input stream for reading the specified java class
   * @throws IOException
   */
  public InputStream findGeneratedSource(String classname) throws IOException {
    JavaFileObject fileObject = fileManager.getJavaFileForInput(StandardLocation.CLASS_OUTPUT, classname, Kind.SOURCE);
    if (fileObject == null) {
      return null;
    }
    return fileObject.openInputStream();
  }

  /**
   * Compiles this project's sources. All generated files will be placed into the output directory.
   *
   * @return <code>true</code> if the compilation has been successful.
   * @throws IOException
   */
  public boolean compile() {
    try {
      return _compile();
    } catch (Exception e) {
      throw Throwables.propagate(e);
    }
  }

  private boolean _compile() throws Exception {
    Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(this.sourceFiles);

    List<String> optionList = new ArrayList<String>();
    // set compiler's class path to be same as the runtime's
    // optionList.addAll(Arrays.asList("-classpath",System.getProperty("java.class.path")));

    // enable the annotation processor
    if (!processorClasses.isEmpty()) {
      StringBuilder buf = new StringBuilder();
      for (Class<? extends Processor> cls : processorClasses) {
        if (buf.length() > 0) {
          buf.append(",");
        }
        buf.append(cls.getCanonicalName());
      }
      optionList.addAll(Arrays.asList("-processor", buf.toString()));
    }
    optionList.addAll(Arrays.asList("-encoding", "utf8"));

    JavaCompiler.CompilationTask task =
        compiler.getTask(null, fileManager, diagnostics, optionList, classnamesForProcessing, compilationUnits);
    if (!processors.isEmpty()) {
      task.setProcessors(processors);
    }
    status = task.call() ? Compilation.Success : Compilation.Failure;

    fileManager.close();

    for (Diagnostic<? extends JavaFileObject> d : diagnostics.getDiagnostics()) {
      System.out.println(d);
    }
    return status == Compilation.Success;
  }

  public Compilation getStatus() {
    return status;
  }
}
