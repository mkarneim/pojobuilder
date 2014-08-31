package net.karneim.pojobuilder.testenv;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;

interface ProcessorListener {

  void onInvoke(ProcessingEnvironment env, RoundEnvironment roundEnv);
}
