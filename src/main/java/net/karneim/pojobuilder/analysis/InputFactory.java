package net.karneim.pojobuilder.analysis;


import java.util.HashSet;
import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;

public class InputFactory {

  private final DirectivesFactory directivesFactory;
  private final Types types;

  public InputFactory(Types types, DirectivesFactory directivesFactory) {
    this.types = types;
    this.directivesFactory = directivesFactory;
  }

  public Input getInput(Element annotatedElement) {
    if (annotatedElement.getKind() == ElementKind.CLASS) {
      TypeElement typeEl = (TypeElement) annotatedElement;
      if (typeEl.getModifiers().contains(Modifier.PRIVATE)) {
        throw new InvalidElementException(String.format("Pojo %s must not be private!", annotatedElement),
            annotatedElement);
      }
      if (typeEl.getEnclosingElement().getKind() == ElementKind.CLASS
          && !typeEl.getModifiers().contains(Modifier.STATIC)) {
        throw new InvalidElementException(String.format("Pojo %s must not be a non-static inner class!",
            annotatedElement), annotatedElement);
      }
      return getInputForAnnotatedPojo(typeEl);
    } else if (annotatedElement.getKind() == ElementKind.CONSTRUCTOR) {
      ExecutableElement constrEl = (ExecutableElement) annotatedElement;
      if (constrEl.getModifiers().contains(Modifier.PRIVATE)) {
        throw new InvalidElementException(String.format("Constructor  %s must not be private!", annotatedElement),
            annotatedElement);
      }
      TypeElement typeEl = (TypeElement) constrEl.getEnclosingElement();
      if (typeEl.getModifiers().contains(Modifier.PRIVATE)) {
        throw new InvalidElementException(String.format("Pojo %s must not be private!", typeEl), annotatedElement);
      }
      if (typeEl.getEnclosingElement().getKind() == ElementKind.CLASS
          && !typeEl.getModifiers().contains(Modifier.STATIC)) {
        throw new InvalidElementException(String.format("Pojo %s must not be a non-static inner class!", typeEl),
            annotatedElement);
      }
      return getInputForAnnotatedConstructor(constrEl);
    } else if (annotatedElement.getKind() == ElementKind.METHOD) {
      ExecutableElement methodEl = (ExecutableElement) annotatedElement;
      // TODO add support for non-static and non-public factory methods
      if (!methodEl.getModifiers().contains(Modifier.STATIC)) {
        throw new InvalidElementException(String.format("Factory method  %s must be static!", annotatedElement),
            annotatedElement);
      }
      if (!methodEl.getModifiers().contains(Modifier.PUBLIC)) {
        throw new InvalidElementException(String.format("Factory method  %s must be public!", annotatedElement),
            annotatedElement);
      }
      return getInputForAnnotatedFactoryMethod(methodEl);
    } else {
      throw new InvalidElementException(String.format("Unexpected type %s!", annotatedElement), annotatedElement);
    }
  }

  private Input getInputForAnnotatedFactoryMethod(ExecutableElement methodEl) {
    TypeMirror pojoTypeMirror = methodEl.getReturnType();
    TypeElement pojoTypeEl = (TypeElement) types.asElement(pojoTypeMirror);
    Set<Element> orginatingElements = new HashSet<Element>();
    Directives directives = directivesFactory.getDirectives(methodEl, orginatingElements);
    return new Input(methodEl, pojoTypeEl, (DeclaredType) pojoTypeMirror, directives, orginatingElements);
  }

  private Input getInputForAnnotatedConstructor(ExecutableElement constrEl) {
    TypeElement pojoTypeEl = (TypeElement) constrEl.getEnclosingElement();
    TypeMirror pojoTypeMirror = pojoTypeEl.asType();
    Set<Element> orginatingElements = new HashSet<Element>();
    Directives directives = directivesFactory.getDirectives(constrEl, orginatingElements);
    return new Input(constrEl, pojoTypeEl, (DeclaredType) pojoTypeMirror, directives, orginatingElements);
  }

  private Input getInputForAnnotatedPojo(TypeElement pojoTypeEl) {
    TypeMirror pojoTypeMirror = pojoTypeEl.asType();
    Set<Element> orginatingElements = new HashSet<Element>();
    Directives directives = directivesFactory.getDirectives(pojoTypeEl, orginatingElements);
    return new Input(pojoTypeEl, pojoTypeEl, (DeclaredType) pojoTypeMirror, directives, orginatingElements);
  }
}
