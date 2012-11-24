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
		return delegate.processingOver();
	}

	@Override
	public boolean errorRaised() {
		return delegate.errorRaised();
	}

	@Override
	public Set<? extends Element> getRootElements() {
		return delegate.getRootElements();
	}

	@Override
	public Set<? extends Element> getElementsAnnotatedWith(TypeElement a) {
		return delegate.getElementsAnnotatedWith(a);
	}

	@Override
	public Set<? extends Element> getElementsAnnotatedWith(Class<? extends Annotation> a) {
		return delegate.getElementsAnnotatedWith(a);
	}

}
