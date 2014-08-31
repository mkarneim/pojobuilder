package net.karneim.pojobuilder.testenv;

import java.lang.annotation.Annotation;
import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

public class RoundEnvironmentAdaptor implements RoundEnvironment {
  private final ThreadLocal<RoundEnvironment> delegate = new ThreadLocal<RoundEnvironment>();

  public RoundEnvironment getDelegate() {
    return delegate.get();
  }

  public void setDelegate(RoundEnvironment delegate) {
    this.delegate.set(delegate);
  }

  @Override
  public boolean processingOver() {
    checkDelegateNotNull();
    return getDelegate().processingOver();
  }

  @Override
  public boolean errorRaised() {
    checkDelegateNotNull();
    return getDelegate().errorRaised();
  }

  @Override
  public Set<? extends Element> getRootElements() {
    checkDelegateNotNull();
    return getDelegate().getRootElements();
  }

  @Override
  public Set<? extends Element> getElementsAnnotatedWith(TypeElement a) {
    checkDelegateNotNull();
    return getDelegate().getElementsAnnotatedWith(a);
  }

  @Override
  public Set<? extends Element> getElementsAnnotatedWith(Class<? extends Annotation> a) {
    checkDelegateNotNull();
    return getDelegate().getElementsAnnotatedWith(a);
  }

  private void checkDelegateNotNull() {
    if (delegate.get() == null) {
      throw new IllegalStateException("Calling methods on RoundEnvironment is only supported inside test methods!");
    }
  }
}
