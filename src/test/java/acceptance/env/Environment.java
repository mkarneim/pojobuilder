package acceptance.env;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

import net.karneim.pojobuilder.GeneratePojoBuilderAnnotationProcessor;

public class Environment {
	private File sourceRoot;
	private List<Class> processorClasses = new ArrayList<Class>();
	private JavaCompiler compiler;
	private DiagnosticCollector<JavaFileObject> diagnostics;
	private StandardJavaFileManager fileManager;
	
	public Environment(File directory) {
		this.sourceRoot = directory;
		this.compiler = ToolProvider.getSystemJavaCompiler();
		this.diagnostics = new DiagnosticCollector<JavaFileObject>();
		this.fileManager = compiler.getStandardFileManager(diagnostics, null, null);
		
		try {
			// Define the output locations
			fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(sourceRoot));
			fileManager.setLocation(StandardLocation.SOURCE_OUTPUT, Arrays.asList(sourceRoot));
		} catch (IOException e) {
			throw new UndeclaredThrowableException(e);
		}
	}
	
	public void destroy() {
		Util.deleteDir( sourceRoot);
	}

	public List<Class> getProcessorClasses() {
		return processorClasses;
	}

	public File getSourceRoot() {
		return sourceRoot;
	}

	public void addSourceFile(String filename) {
		try {
			String curDir = System.getProperty("user.dir");
			File fromFile = new File(curDir, filename);
			File targetDir = getSourceRoot();
			copy(fromFile, targetDir);
		} catch (IOException e) {
			throw new UndeclaredThrowableException(e);
		}
	}

	private void copy(File fromFile, File toDir) throws IOException {
		if (!fromFile.exists()) {
			throw new IllegalArgumentException(String.format(
					"File '%s' not found!", fromFile));
		}
		if (!toDir.exists()) {
			toDir.mkdirs();
			if (!toDir.exists()) {
				throw new IllegalArgumentException(String.format(
						"Directory '%s' not found!", toDir));
			}
		}
		File toFile = new File(toDir, fromFile.getName());
		InputStream in = new FileInputStream(fromFile);
		OutputStream out = new FileOutputStream(toFile);
		// Transfer bytes from in to out
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}

	public Class<?> findClass(String classname) throws ClassNotFoundException {
		ClassLoader cl = fileManager.getClassLoader(StandardLocation.CLASS_OUTPUT);
		Class<?> result = cl.loadClass(classname);
		return result;
	}

	public boolean compile() throws IOException {
		Iterable<? extends JavaFileObject> compilationUnits = fileManager
				.getJavaFileObjectsFromStrings(getSources());

		List<String> optionList = new ArrayList<String>();
		// set compiler's classpath to be same as the runtime's
		optionList.addAll(Arrays.asList("-classpath", System.getProperty("java.class.path")));
		// enable the annotation processor
		if ( !processorClasses.isEmpty()) {
			//optionList.addAll(Arrays.asList("-processor", GeneratePojoBuilderAnnotationProcessor.class.getCanonicalName()));#
			StringBuilder buf = new StringBuilder();
			for( Class cls: processorClasses) {
				if ( buf.length()>0) {
					buf.append(",");
				}
				buf.append( cls.getCanonicalName());
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

	private Collection<String> getSources() {
		ArrayList<String> result = new ArrayList<String>();
		findJavaFiles(getSourceRoot(), result);
		return result;
	}

	private void findJavaFiles(File fromDir, List<String> result) {
		String[] javaFiles = fromDir.list(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".java");
			}
		});
		for (String javafile : javaFiles) {
			result.add(new File(fromDir, javafile).getAbsolutePath());
		}

		File[] subdirs = fromDir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}
		});
		for (File dir : subdirs) {
			findJavaFiles(dir, result);
		}
	}

}
