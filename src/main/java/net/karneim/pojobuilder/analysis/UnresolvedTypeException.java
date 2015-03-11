package net.karneim.pojobuilder.analysis;

import net.karneim.pojobuilder.PojoBuilderException;

@SuppressWarnings("serial")
public class UnresolvedTypeException extends PojoBuilderException {

  public UnresolvedTypeException(String message) {
    super(message);
  }

}
