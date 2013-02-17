package testenv;

import java.util.Locale;
import java.util.Map;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

class ProcessingEnvironmentAdaptor implements ProcessingEnvironment {

	private ProcessingEnvironment delegate;

	public ProcessingEnvironmentAdaptor() {
		super();
	}

	public ProcessingEnvironment getDelegate() {
		return delegate;
	}

	public void setDelegate(ProcessingEnvironment delegate) {
		this.delegate = delegate;
	}

	@Override
	public Map<String, String> getOptions() {
		checkDelegateNotNull();
		return delegate.getOptions();
	}

	@Override
	public Messager getMessager() {
		checkDelegateNotNull();
		return delegate.getMessager();
	}

	@Override
	public Filer getFiler() {
		checkDelegateNotNull();
		return delegate.getFiler();
	}

	@Override
	public Elements getElementUtils() {
		checkDelegateNotNull();
		return delegate.getElementUtils();
	}

	@Override
	public Types getTypeUtils() {
		checkDelegateNotNull();
		return delegate.getTypeUtils();
	}

	@Override
	public SourceVersion getSourceVersion() {
		checkDelegateNotNull();
		return delegate.getSourceVersion();
	}

	@Override
	public Locale getLocale() {
		checkDelegateNotNull();
		return delegate.getLocale();
	}

	private void checkDelegateNotNull() {
		if ( delegate == null) {
			throw new IllegalStateException("Calling methods on ProcessingEnvironment is only supported inside test methods!");
		}
	}

}
