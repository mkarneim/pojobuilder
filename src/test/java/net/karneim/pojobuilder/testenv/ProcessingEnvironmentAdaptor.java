package net.karneim.pojobuilder.testenv;

import java.util.Locale;
import java.util.Map;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

class ProcessingEnvironmentAdaptor implements ProcessingEnvironment {

  private final ThreadLocal<ProcessingEnvironment> delegate = new ThreadLocal<ProcessingEnvironment>();

  public ProcessingEnvironmentAdaptor() {
    super();
  }

  public ProcessingEnvironment getDelegate() {
    return delegate.get();
  }

  public void setDelegate(ProcessingEnvironment delegate) {
    this.delegate.set(delegate);
  }

  @Override
  public Map<String, String> getOptions() {
    checkDelegateNotNull();
    return getDelegate().getOptions();
  }

  @Override
  public Messager getMessager() {
    checkDelegateNotNull();
    return getDelegate().getMessager();
  }

  @Override
  public Filer getFiler() {
    checkDelegateNotNull();
    return getDelegate().getFiler();
  }

  @Override
  public Elements getElementUtils() {
    checkDelegateNotNull();
    return getDelegate().getElementUtils();
  }

  @Override
  public Types getTypeUtils() {
    checkDelegateNotNull();
    return getDelegate().getTypeUtils();
  }

  @Override
  public SourceVersion getSourceVersion() {
    checkDelegateNotNull();
    return getDelegate().getSourceVersion();
  }

  @Override
  public Locale getLocale() {
    checkDelegateNotNull();
    return getDelegate().getLocale();
  }

  private void checkDelegateNotNull() {
    if (delegate.get() == null) {
      throw new IllegalStateException("Calling methods on ProcessingEnvironment is only supported inside test methods!");
    }
  }

}
