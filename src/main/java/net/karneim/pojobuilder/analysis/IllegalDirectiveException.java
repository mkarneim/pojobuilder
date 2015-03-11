package net.karneim.pojobuilder.analysis;

import net.karneim.pojobuilder.PojoBuilderException;

public class IllegalDirectiveException extends PojoBuilderException {

  private static final long serialVersionUID = 1L;

  public IllegalDirectiveException() {
    super();
  }

  public IllegalDirectiveException(String message) {
    super(message);
  }

}
