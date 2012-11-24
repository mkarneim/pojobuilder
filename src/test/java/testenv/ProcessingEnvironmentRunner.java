package testenv;

import java.lang.reflect.UndeclaredThrowableException;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

public class ProcessingEnvironmentRunner extends BlockJUnit4ClassRunner {
	private static final ProcessingEnvironmentAdaptor processingEnvironment = new ProcessingEnvironmentAdaptor();
	private static final RoundEnvironmentAdaptor roundEnvironment = new RoundEnvironmentAdaptor();

	public static ProcessingEnvironment getProcessingEnvironment() {
		return processingEnvironment;
	}

	public static RoundEnvironment getRoundEnvironment() {
		return roundEnvironment;
	}

	private Class<?> klass;

	public ProcessingEnvironmentRunner(Class<?> klass) throws InitializationError {
		super(klass);
		this.klass = klass;
	}

	protected Statement methodBlock(FrameworkMethod method) {
		final Statement next = super.methodBlock(method);
		return new YYYStatement(next, method);
	}

	private class YYYStatement extends Statement {
		private Statement next;
		private FrameworkMethod method;
		private Throwable exception;

		public YYYStatement(Statement next, FrameworkMethod method) {
			super();
			this.next = next;
			this.method = method;
		}

		@Override
		public void evaluate() throws Throwable {
			exception = null;
			
			
			
			Processor.setListener(new ProcessorListener() {

				@Override
				public void onInvoke(ProcessingEnvironment env, RoundEnvironment roundEnv) {
					processingEnvironment.setDelegate(env);
					roundEnvironment.setDelegate(roundEnv);
					try {
						next.evaluate();
					} catch (Throwable t) {
						exception = t;
					}
				}
			});

			try {
				JavaProject prj = new JavaProject(Util.createTempDir());
				
				addSourceFiles(prj, klass.getAnnotation(AddSourceFile.class));
				addSourceFiles(prj,  method.getMethod().getAnnotation(AddSourceFile.class));
				
				prj.getProcessorClasses().add(Processor.class);
				prj.addClassnameForProcessing(klass.getCanonicalName());
				prj.compile();
				prj.delete();
			} catch (Exception e) {
				throw new UndeclaredThrowableException(e);
			}
			processingEnvironment.setDelegate(null);
			if (exception != null) {
				throw exception;
			}
		}
	}

	private static void addSourceFiles(JavaProject prj, AddSourceFile anno) {
		if (anno != null) {
			String[] files = anno.value();
			if (files != null) {
				for (String file : files) {
					prj.addSourceFile(file);
				}
			}
		}
	}

	@Override
	public void run(RunNotifier notifier) {
		super.run(notifier);
	}

}
