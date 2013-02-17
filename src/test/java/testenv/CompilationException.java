package testenv;

@SuppressWarnings("serial")
public class CompilationException extends Exception {

	public CompilationException() {
		super();
	}

	public CompilationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CompilationException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompilationException(String message) {
		super(message);
	}

	public CompilationException(Throwable cause) {
		super(cause);
	}

}
