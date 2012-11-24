package testenv;

import javax.annotation.processing.ProcessingEnvironment;

interface ProcessorListener {
	public void onInvoke( ProcessingEnvironment env);
}
