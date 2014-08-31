package net.karneim.pojobuilder.analysis;

import javax.lang.model.element.Element;

@SuppressWarnings("serial")
public class InvalidElementException extends RuntimeException {
  private final Element element;

  public InvalidElementException(String message, Element element) {
    super(message);
    this.element = element;
  }

  public Element getElement() {
    return element;
  }

}
