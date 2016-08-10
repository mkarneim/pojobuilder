package net.karneim.pojobuilder;

public class PojoBuilderException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public PojoBuilderException() {
    super();
  }

  public PojoBuilderException(String message, Throwable cause) {
    super(message, cause);
  }

  public PojoBuilderException(String message) {
    super(message);
  }

  public PojoBuilderException(Throwable cause) {
    super(cause);
  }

}
