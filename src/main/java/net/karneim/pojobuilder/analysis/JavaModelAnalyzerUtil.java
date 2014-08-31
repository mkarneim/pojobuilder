package net.karneim.pojobuilder.analysis;

import static javax.lang.model.element.ElementKind.CLASS;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;
import static javax.lang.model.type.TypeKind.VOID;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import net.karneim.pojobuilder.model.TypeM;

public class JavaModelAnalyzerUtil {

  private static final String BUILD_METHOD_NAME = "build";
  private static final String IS = "is";
  private static final String GET = "get";
  private static final String SET = "set";

  private final Elements elements;
  private final Types types;

  public JavaModelAnalyzerUtil(Elements elements, Types types) {
    this.elements = elements;
    this.types = types;
  }

  /**
   * Returns the classname (without any package qualifier) of the given type element.
   * 
   * @param typeElem
   * @return the classname of the given type element
   */
  public String getClassname(TypeElement typeElem) {
    String qualifiedName = typeElem.getQualifiedName().toString();
    String packageName = getPackage(typeElem);
    if (packageName.isEmpty()) {
      return qualifiedName;
    }
    String result = qualifiedName.substring(packageName.length() + 1);
    return result;
  }

  /**
   * Returns the Java package the given type (or it's outer type) belongs to.
   * 
   * @param type
   * @return the Java package the given type belongs to
   */
  public String getPackage(DeclaredType type) {
    return getPackage((TypeElement) type.asElement());
  }

  /**
   * Returns the Java package the given type element (or it's outer type) belongs to.
   * 
   * @param typeElem
   * @return the Java package the given type element belongs to
   */
  public String getPackage(TypeElement typeElem) {
    Element outerElem = typeElem.getEnclosingElement();
    while (!(outerElem instanceof PackageElement)) {
      outerElem = outerElem.getEnclosingElement();
    }

    PackageElement packEl = (PackageElement) outerElem;

    return packEl.getQualifiedName().toString();
  }

  /**
   * Returns the top-level Java class that contains the given element.
   * 
   * @param elem
   * @return the top-level Java class that contains the given element
   */
  public TypeElement getCompilationUnit(Element elem) {
    if (elem instanceof TypeElement && elem.getEnclosingElement() instanceof PackageElement) {
      return (TypeElement) elem;
    }
    return getCompilationUnit(elem.getEnclosingElement());
  }

  // TODO remove that when we know we don't need it
  private boolean isAccessibleForBuilder(Element el, TypeM accessingType) {
    if (el.getModifiers().contains(PUBLIC)) {
      return true;
    }
    if (el.getModifiers().contains(PRIVATE)) {
      return false;
    }
    PackageElement fieldPackage = elements.getPackageOf(el);
    String builderPackge = accessingType.getPackageName();
    if (fieldPackage.isUnnamed()) {
      return builderPackge == null;
    } else {
      return fieldPackage.getQualifiedName().toString().equals(builderPackge);
    }
  }

  /**
   * Returns true if the given element is marked with a 'static' modifier.
   * 
   * @param el
   * @return true if the given element is marked with a 'static' modifier
   */
  public boolean isStatic(Element el) {
    return el.getModifiers().contains(STATIC);
  }

  /**
   * Returns true if the given element is a Setter-method.
   * 
   * @param el
   * @return true if the given element is a Setter-method
   */
  public boolean isSetterMethod(ExecutableElement el) {
    String methodName = el.getSimpleName().toString();
    TypeMirror retType = el.getReturnType();
    return methodName.startsWith(SET) && methodName.length() > SET.length() && retType.getKind() == VOID
        && el.getParameters().size() == 1;
  }

  /**
   * Returns true if the given element is a Getter-method.
   * 
   * @param el
   * @return true if the given element is a Getter-method
   */
  public boolean isGetterMethod(ExecutableElement el) {
    String methodName = el.getSimpleName().toString();
    TypeMirror retType = el.getReturnType();
    return ((methodName.startsWith(GET) && methodName.length() > GET.length()) || (methodName.startsWith(IS) && methodName
        .length() > IS.length())) && retType.getKind() != VOID && el.getParameters().size() == 0;
  }

  /**
   * Returns whether the given element is directly declared in {@link Object}.
   * 
   * @param el
   * @return true if the element is declared in {@link Object}
   */
  public boolean isDeclaredInObject(Element el) {
    Element ownerEl = el.getEnclosingElement();
    if (ownerEl.getKind() == CLASS) {
      TypeElement typeEl = (TypeElement) ownerEl;
      return typeEl.getQualifiedName().toString().equals(Object.class.getName());
    }
    return false;
  }

  /**
   * Returns the name of the property that is accessed by the given [G|S]etter method.
   * 
   * @param methodEl
   * @return the name of the property
   */
  public String getPropertyName(ExecutableElement methodEl) {
    String name = methodEl.getSimpleName().toString();
    int prefixLength = -1;
    if (name.startsWith(SET)) {
      prefixLength = SET.length();
    } else if (name.startsWith(GET)) {
      prefixLength = GET.length();
    } else if (name.startsWith(IS)) {
      prefixLength = IS.length();
    }

    if (prefixLength > 0) {
      name = name.substring(prefixLength);
      name = firstCharToLowerCase(name);
      return name;
    } else {
      throw new IllegalArgumentException(String.format("Not a setter or getter method name: %s!", name));
    }
  }

  /**
   * Returns a copy of the given text where the first character is a lower case letter.
   * 
   * @param text
   * @return a copy of the text with first letter lower case
   */
  private String firstCharToLowerCase(String text) {
    char[] vals = text.toCharArray();
    vals[0] = Character.toLowerCase(vals[0]);
    return String.valueOf(vals);
  }

  /**
   * Returns the effective type of the given element when is is viewed as a member of the given owner type.
   * 
   * @param ownerType
   * @param element
   * @return the effective type of the given element
   */
  @SuppressWarnings("unchecked")
  public <T extends TypeMirror> T getType(DeclaredType ownerType, Element element) {
    T execType = (T) element.asType();
    try {
      execType = (T) types.asMemberOf(ownerType, element);
    } catch (IllegalArgumentException e) {
      // Ignore
    }
    return execType;
  }

  /**
   * Returns true, if the given type element has a method called "build" with no parameters and which has an actual
   * return type that is compatible with the given return type.
   * 
   * @param typeElement
   * @param requiredReturnType
   * @return true, if the type element has a build method
   */
  public boolean hasBuildMethod(TypeElement typeElement, TypeMirror requiredReturnType) {
    List<? extends Element> memberEls = elements.getAllMembers(typeElement);
    List<ExecutableElement> methodEls = ElementFilter.methodsIn(memberEls);
    for (ExecutableElement methodEl : methodEls) {
      TypeMirror actualReturnType = methodEl.getReturnType();
      if (actualReturnType.getKind() == TypeKind.TYPEVAR) {
        TypeVariable tv = (TypeVariable) actualReturnType;
        actualReturnType = tv.getUpperBound();
      }
      if (methodEl.getSimpleName().toString().equals(BUILD_METHOD_NAME) && methodEl.getParameters().size() == 0
          && types.isSubtype(requiredReturnType, actualReturnType)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns true if the given typeElement is a subtype of the given type parameter's upper bound.
   * 
   * @param typeElement
   * @param typeParamEl
   * @return true if the given typeElement is a subtype of the given type parameter's upper bound
   */
  public boolean matchesUpperBound(TypeElement typeElement, TypeParameterElement typeParamEl) {
    TypeMirror typeParam = typeParamEl.asType();
    if (typeParam.getKind() != TypeKind.TYPEVAR) {
      throw new RuntimeException(String.format("Unexpected kind of type parameter for %s: %s",
          typeParamEl.getSimpleName(), typeParam.getKind()));
    }
    TypeVariable tv = (TypeVariable) typeParam;
    return types.isSubtype(typeElement.asType(), tv.getUpperBound());
  }

  /**
   * Returns true if the given type parameter has an upper bound of type {@link Object}.
   * 
   * @param typeParamEl
   * @return true if the given type parameter has an upper bound of type {@link Object}
   */
  public boolean isUpperBoundToObject(TypeParameterElement typeParamEl) {
    TypeMirror typeParam = typeParamEl.asType();
    if (typeParam.getKind() != TypeKind.TYPEVAR) {
      throw new RuntimeException(String.format("Unexpected kind of type parameter for %s: %s",
          typeParamEl.getSimpleName(), typeParam.getKind()));
    }
    TypeVariable tv = (TypeVariable) typeParam;
    TypeElement objectElem = elements.getTypeElement(Object.class.getName());
    return types.isSameType(objectElem.asType(), tv.getUpperBound());
  }

  /**
   * Returns <code>true</code> if the given string is a valid Java identifier.
   * 
   * @param string
   * @return <code>true</code> if the given string is a valid Java identifier
   */
  public boolean isValidJavaIdentifier(String string) {
    char[] chars = string.toCharArray();
    if (chars.length > 0 && !Character.isJavaIdentifierStart(chars[0])) {
      return false;
    }
    for (int i = 1; i < chars.length; ++i) {
      if (!Character.isJavaIdentifierPart(chars[i])) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns <code>true</code> if the given string is a valid Java package name.
   * <p>
   * This does not check if the package exists.
   * 
   * @param string
   * @return <code>true</code> if the given string is a valid Java package name.
   */
  public boolean isValidJavaPackageName(String string) {
    String[] parts = string.split("\\.");
    for (String part : parts) {
      if (!isValidJavaIdentifier(part)) {
        return false;
      }
    }
    return true;
  }

  public Collection<? extends Element> findAnnotatedElements(Collection<TypeElement> typeElements,
      Class<?> annotationType) {
    List<Element> result = new ArrayList<Element>();
    for (Element unit : typeElements) {
      findAnnotatedElements(result, unit, annotationType);
    }
    return result;
  }

  private void findAnnotatedElements(List<Element> result, Element element, Class<?> annotationType) {
    switch (element.getKind()) {
      case CLASS:
        TypeElement typeEl = (TypeElement) element;
        for (AnnotationMirror anno : typeEl.getAnnotationMirrors()) {
          if (annotationType.getName().equals((getName(anno)))) {
            result.add(typeEl);
          }
        }
        List<? extends Element> enclosedElems = typeEl.getEnclosedElements();
        for (Element el : enclosedElems) {
          findAnnotatedElements(result, el, annotationType);
        }
        break;
      case CONSTRUCTOR:
      case METHOD:
        ExecutableElement exeEl = (ExecutableElement) element;
        for (AnnotationMirror anno : exeEl.getAnnotationMirrors()) {
          if (annotationType.getName().equals(getName(anno))) {
            result.add(exeEl);
          }
        }
        break;
      default:
        break;
    }
  }

  private String getName(AnnotationMirror anno) {
    TypeElement el = (TypeElement) anno.getAnnotationType().asElement();
    return el.getQualifiedName().toString();
  }

}
