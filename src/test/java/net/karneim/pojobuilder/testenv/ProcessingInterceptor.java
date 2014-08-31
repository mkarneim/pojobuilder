package net.karneim.pojobuilder.testenv;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

import org.junit.runner.RunWith;

public class ProcessingInterceptor extends AbstractProcessor {

  private ProcessorListener listener;
  private ProcessingEnvironment processingEnv;

  public ProcessingInterceptor() {}

  public void setListener(ProcessorListener aListener) {
    this.listener = aListener;
  }

  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    this.processingEnv = processingEnv;
    super.init(processingEnv);
  }

  public ProcessingEnvironment getProcessingEnv() {
    return processingEnv;
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    if (roundEnv.processingOver()) {
      return false;
    }
    if (listener != null) {
      listener.onInvoke(getProcessingEnv(), roundEnv);
    }
    return true;
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return new HashSet<String>(Arrays.asList(RunWith.class.getCanonicalName()));
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latest();
  }

}
