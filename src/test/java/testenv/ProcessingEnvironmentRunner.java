package testenv;

import java.lang.reflect.UndeclaredThrowableException;

import javax.annotation.processing.ProcessingEnvironment;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;


public class ProcessingEnvironmentRunner extends BlockJUnit4ClassRunner {
	private static final ProcessingEnvironmentAdaptor processingEnvironment = new ProcessingEnvironmentAdaptor();

	public static ProcessingEnvironment getProcessingEnvironment() {
		return processingEnvironment;
	}

	private Class<?> klass;

	public ProcessingEnvironmentRunner(Class<?> klass) throws InitializationError {
		super(klass);
		this.klass = klass;
	}

	protected Statement methodBlock(FrameworkMethod method) {
		final Statement next = super.methodBlock(method);
		return new YYYStatement(next);
	}

	private class YYYStatement extends Statement {
		private Statement next;
		private Throwable exception;

		public YYYStatement(Statement next) {
			super();
			this.next = next;
		}

		@Override
		public void evaluate() throws Throwable {
			exception = null;
			Processor.setListener(new ProcessorListener() {

				@Override
				public void onInvoke(ProcessingEnvironment env) {
					processingEnvironment.setDelegate(env);
					try {
						next.evaluate();
					} catch (Throwable t) {
						exception = t;
					}
				}
			});

			try {
				JavaProject prj = new JavaProject(Util.createTempDir());
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

	@Override
	public void run(RunNotifier notifier) {
		super.run(notifier);
	}

}
