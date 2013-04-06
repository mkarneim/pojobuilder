package net.karneim.pojobuilder;

import javax.lang.model.element.Element;
import javax.tools.Diagnostic;
import javax.tools.Diagnostic.Kind;

@SuppressWarnings("serial")
public class BuildException extends RuntimeException {
	private final Diagnostic.Kind kind;
	private final String message;
	private final Element element;

	public BuildException(Kind kind, String message, Element element) {
		super();
		this.kind = kind;
		this.message = message;
		this.element = element;
	}

	public BuildException(Throwable cause) {
		super(cause);
		this.kind = Kind.ERROR;
		this.message = cause.getMessage();
		this.element = null;
	}

	public Diagnostic.Kind getKind() {
		return kind;
	}

	public String getMessage() {
		return message;
	}

	public Element getElement() {
		return element;
	}

}
