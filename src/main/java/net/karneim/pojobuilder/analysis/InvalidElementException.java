package net.karneim.pojobuilder.analysis;

import javax.lang.model.element.Element;

import net.karneim.pojobuilder.PojoBuilderException;

@SuppressWarnings("serial")
public class InvalidElementException extends PojoBuilderException {
  private final Element element;

  public InvalidElementException(String message, Element element) {
    super(message);
    this.element = element;
  }

  public Element getElement() {
    return element;
  }

}
