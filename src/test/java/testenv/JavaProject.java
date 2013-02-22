package testenv;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Processor;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

/**
 * The {@link JavaProject} is a driver for controlling a simple java project.
 * This includes adding source files, enabling annotation processors, compiling
 * and accessing the generated classes.
 * 
 * @author Michael Karneim
 */
public class JavaProject {
	private final File outputRoot;

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
	 * @param workingDirectory
	 *            will be used to store generated source and class files
	 */
	public JavaProject(File workingDirectory) {
		this.outputRoot = workingDirectory;
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
	}

	/**
	 * Deletes this java project by deleting the working directory.
	 */
	public void delete() {
		Util.deleteDir(outputRoot);
	}

	/**
	 * Returns the annotation processors that will be used during the
	 * compilation task.
	 * 
	 * @return the annotation processors that will be used during the
	 *         compilation task
	 */
	public List<Processor> getProcessors() {
		return processors;
	}

	/**
	 * Returns the annotation processor classes that will be used during the
	 * compilation task.
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
	 * Adds the file with the given relative filename to the source tree. If the
	 * file is a directory then all files inside that directory are added
	 * (recursively).
	 * 
	 * @param filepath
	 *            the filepath must be relative to the current directory (that
	 *            is the directory this JVM has been started from)
	 */
	public void addSourceFile(String filepath) {
		String curDir = System.getProperty("user.dir");
		File file = new File(curDir, filepath);
		addSourceFile(file);
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
	 * Adds the (compiled) class with the given full qualified name to the list
	 * of classes, that should be processed by the annotation processor(s)
	 * without being compiled first. Make sure that the class is available in
	 * the current class path.
	 * 
	 * @param name
	 *            the full qualified class name of the class
	 */
	public void addClassnameForProcessing(String name) {
		classnamesForProcessing.add(name);
	}

	/**
	 * Loads a class with the given class name from this project's output
	 * directory and returns it.
	 * 
	 * @param classname
	 * @return the class with the given class name, loaded from this project's
	 *         output directory
	 * @throws ClassNotFoundException
	 */
	public Class<?> findClass(String classname) throws ClassNotFoundException {
		ClassLoader cl = fileManager.getClassLoader(StandardLocation.CLASS_OUTPUT);
		Class<?> result = cl.loadClass(classname);
		return result;
	}

	/**
	 * Compiles this project's sources. All generated files will be placed into
	 * the output directory.
	 * 
	 * @return <code>true</code> if the compilation has been successful.
	 * @throws IOException
	 */
	public boolean compile() throws IOException {
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

		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, optionList,
				classnamesForProcessing, compilationUnits);
		if (!processors.isEmpty()) {
			task.setProcessors(processors);
		}
		boolean success = task.call();

		fileManager.close();

		for (Diagnostic<? extends JavaFileObject> d : diagnostics.getDiagnostics()) {
			System.out.println(d);
		}
		return success;
	}

}
