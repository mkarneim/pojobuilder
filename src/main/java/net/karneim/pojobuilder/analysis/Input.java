package net.karneim.pojobuilder.analysis;

import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;

public class Input {
  private final Element annotatedElement;
  private final TypeElement pojoElement;
  private final DeclaredType pojoType;
  private final Directives directives;
  private final Set<Element> orginatingElements;

  public Input(Element annotatedElement, TypeElement pojoElement, DeclaredType pojoType, Directives directives,
      Set<Element> orginatingElements) {
    super();
    this.annotatedElement = annotatedElement;
    this.pojoElement = pojoElement;
    this.pojoType = pojoType;
    this.directives = directives;
    this.orginatingElements = orginatingElements;
  }

  public Element getAnnotatedElement() {
    return annotatedElement;
  }

  public TypeElement getPojoElement() {
    return pojoElement;
  }

  public DeclaredType getPojoType() {
    return pojoType;
  }

  public Directives getDirectives() {
    return directives;
  }

  public Set<Element> getOrginatingElements() {
    return orginatingElements;
  }

}
