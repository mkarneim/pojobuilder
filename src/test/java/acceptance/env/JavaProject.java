package acceptance.env;

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
	private final List<Class<? extends Processor>> processorClasses = new ArrayList<Class<? extends Processor>>();
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
		this.fileManager = compiler.getStandardFileManager(diagnostics, null,
				null);

		try {
			// Define the output locations
			fileManager.setLocation(StandardLocation.CLASS_OUTPUT,
					Arrays.asList(outputRoot));
			fileManager.setLocation(StandardLocation.SOURCE_OUTPUT,
					Arrays.asList(outputRoot));
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
	 * @return the annotation processors that will be used
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

	/**
	 * Adds the file with the given relative filename to the source tree.
	 * 
	 * @param filename
	 *            the filename mus be relative to the current directory (that is
	 *            the directory this JVM has been started from)
	 */
	public void addSourceFile(String filename) {
		String curDir = System.getProperty("user.dir");
		File fromFile = new File(curDir, filename);
		sourceFiles.add(fromFile);
	}

	/**
	 * Loads a class with the given classname from this project's output
	 * directory and returns it.
	 * 
	 * @param classname
	 * @return the class with the given classname, loaded from this project's
	 *         output directory
	 * @throws ClassNotFoundException
	 */
	public Class<?> findClass(String classname) throws ClassNotFoundException {
		ClassLoader cl = fileManager
				.getClassLoader(StandardLocation.CLASS_OUTPUT);
		Class<?> result = cl.loadClass(classname);
		return result;
	}

	/**
	 * Compiles this project's sources. All generated files will be placed into
	 * the output directory.
	 * 
	 * @return <code>true</code> if the compilation has been successfull.
	 * @throws IOException
	 */
	public boolean compile() throws IOException {
		Iterable<? extends JavaFileObject> compilationUnits = fileManager
				.getJavaFileObjectsFromFiles(this.sourceFiles);

		List<String> optionList = new ArrayList<String>();
		// set compiler's classpath to be same as the runtime's
		optionList.addAll(Arrays.asList("-classpath",
				System.getProperty("java.class.path")));
		// enable the annotation processor
		if (!processorClasses.isEmpty()) {
			// optionList.addAll(Arrays.asList("-processor",
			// GeneratePojoBuilderAnnotationProcessor.class.getCanonicalName()));#
			StringBuilder buf = new StringBuilder();
			for (Class<? extends Processor> cls : processorClasses) {
				if (buf.length() > 0) {
					buf.append(",");
				}
				buf.append(cls.getCanonicalName());
			}
			optionList.addAll(Arrays.asList("-processor", buf.toString()));
		}

		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager,
				diagnostics, optionList, null, compilationUnits);
		boolean success = task.call();

		fileManager.close();

		for (Diagnostic<? extends JavaFileObject> d : diagnostics
				.getDiagnostics()) {
			System.out.println(d);
		}
		return success;
	}

}
