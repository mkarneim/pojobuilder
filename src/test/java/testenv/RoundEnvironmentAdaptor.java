package testenv;

import java.lang.annotation.Annotation;
import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

public class RoundEnvironmentAdaptor implements RoundEnvironment {
	private RoundEnvironment delegate;

	public RoundEnvironment getDelegate() {
		return delegate;
	}

	public void setDelegate(RoundEnvironment delegate) {
		this.delegate = delegate;
	}

	@Override
	public boolean processingOver() {
		checkDelegateNotNull();
		return delegate.processingOver();
	}

	@Override
	public boolean errorRaised() {
		checkDelegateNotNull();
		return delegate.errorRaised();
	}

	@Override
	public Set<? extends Element> getRootElements() {
		checkDelegateNotNull();
		return delegate.getRootElements();
	}

	@Override
	public Set<? extends Element> getElementsAnnotatedWith(TypeElement a) {
		checkDelegateNotNull();
		return delegate.getElementsAnnotatedWith(a);
	}

	@Override
	public Set<? extends Element> getElementsAnnotatedWith(Class<? extends Annotation> a) {
		checkDelegateNotNull();
		return delegate.getElementsAnnotatedWith(a);
	}

	private void checkDelegateNotNull() {
		if (delegate == null) {
			throw new IllegalStateException(
					"Calling methods on RoundEnvironment is only supported inside test methods!");
		}
	}
}
