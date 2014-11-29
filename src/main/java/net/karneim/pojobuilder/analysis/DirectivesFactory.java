package net.karneim.pojobuilder.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class DirectivesFactory {
  private final Elements elements;
  private final Types types;
  private final JavaModelAnalyzerUtil javaModelAnalyzerUtil;

  public DirectivesFactory(Elements elements, Types types,
      JavaModelAnalyzerUtil javaModelAnalyzerUtil) {
    this.elements = elements;
    this.types = types;
    this.javaModelAnalyzerUtil = javaModelAnalyzerUtil;
  }

  /**
   * Scans the annotation hierarchy of all annotations on the given (annotated) element for the
   * presence of a {@link GeneratePojoBuilder} annotation and returns a {@link Directives} object
   * populated with the aggregation of the specified {@link GeneratePojoBuilder} element values.
   * <p>
   * Please note that element values can be overridden top-down. This means, that element values
   * that appear further up in the source code will be overridden by element values further down.
   * 
   * @param annotatedEl the annotated element
   * @param orginatingElements this out parameter will be filled with all elements that have (or
   *        could have) contributed to the {@link Directives} throughout the scan process.
   * @return a {@link Directives} object populated with the effective annotation element values
   */
  public Directives getDirectives(Element annotatedEl, Set<Element> orginatingElements) {
    List<HierarchyElement> hierarchy = findAnnotationHierarchy(annotatedEl);
    List<HierarchyElement> relevantHierarchy = filterByType(hierarchy, GeneratePojoBuilder.class);
    Map<String, Object> valueMap = getValueMap(relevantHierarchy);
    fillOrginatingElements(orginatingElements, hierarchy);
    Directives result = new Directives(valueMap);
    validate(annotatedEl, result);
    return result;
  }

  private List<HierarchyElement> filterByType(List<HierarchyElement> hierarchy,
      Class<GeneratePojoBuilder> class1) {
    TypeMirror generatePojoBuilderAnno =
        elements.getTypeElement(class1.getName()).asType();
    List<HierarchyElement> result = new ArrayList<HierarchyElement>();
    for (HierarchyElement el : hierarchy) {
      if (types.isSameType(el.annotation.getAnnotationType(), generatePojoBuilderAnno)) {
        result.add(el);
      }
    }
    return result;
  }

  private void fillOrginatingElements(Set<Element> result, List<HierarchyElement> hierarchy) {
    for (HierarchyElement hierarchyElement : hierarchy) {
      result.add(javaModelAnalyzerUtil.getCompilationUnit(hierarchyElement.annotatedEl));
    }
  }

  private void validate(Element annotatedEl, Directives directives) {
    if (directives.isGenerateBuilderProperties()
        && Void.class.getName().equals(directives.getBuilderInterfaceName())) {
      String message =
          String
              .format("Value for \"withBuilderInterface\" not specified. When setting \"withBuilderProperties\" to true, you must also specifiy a valid builder interface.");
      throw new InvalidElementException(message, annotatedEl);
    }
  }

  private static class HierarchyElement {
    Element annotatedEl;
    AnnotationMirror annotation;

    public HierarchyElement(Element annotatedEl, AnnotationMirror annotation) {
      super();
      this.annotatedEl = annotatedEl;
      this.annotation = annotation;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((annotatedEl == null) ? 0 : annotatedEl.hashCode());
      result = prime * result + ((annotation == null) ? 0 : annotation.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      HierarchyElement other = (HierarchyElement) obj;
      if (annotatedEl == null) {
        if (other.annotatedEl != null)
          return false;
      } else if (!annotatedEl.equals(other.annotatedEl))
        return false;
      if (annotation == null) {
        if (other.annotation != null)
          return false;
      } else if (!annotation.equals(other.annotation))
        return false;
      return true;
    }

    @Override
    public String toString() {
      return "HierarchyElement [annotatedEl=" + annotatedEl + ", annotation=" + annotation + "]";
    }
  }

  private List<HierarchyElement> findAnnotationHierarchy(Element annotatedEl) {
    Set<Element> visitedElements = new HashSet<Element>();
    List<HierarchyElement> result = new LinkedList<HierarchyElement>();
    findAnnotationHierarchy(annotatedEl, result, visitedElements);
    return result;
  }

  private void findAnnotationHierarchy(Element annotatedEl, List<HierarchyElement> result,
      Set<Element> visitedElements) {
    if (!visitedElements.add(annotatedEl)) {
      return;
    }
    List<? extends AnnotationMirror> annos = annotatedEl.getAnnotationMirrors();
    for (AnnotationMirror anno : annos) {
      Element el = anno.getAnnotationType().asElement();
      findAnnotationHierarchy(el, result, visitedElements);
      HierarchyElement hElem = new HierarchyElement(annotatedEl, anno);
      result.add(hElem);
    }
  }

  private boolean isGeneratePojoBuilderAnnotation(AnnotationMirror anno) {
    TypeElement generatePojoBuilderTypeEl =
        elements.getTypeElement(GeneratePojoBuilder.class.getName());
    return types.isSameType(anno.getAnnotationType(), generatePojoBuilderTypeEl.asType());
  }

  private Map<String, Object> getValueMap(List<HierarchyElement> hierarchy) {
    Map<String, Object> result = null;
    for (HierarchyElement el : hierarchy) {
      if (isGeneratePojoBuilderAnnotation(el.annotation)) {
        if (result == null) {
          result = getValueMap(elements.getElementValuesWithDefaults(el.annotation));
        } else {
          result.putAll(getValueMap(el.annotation.getElementValues()));
        }
      }
    }
    return result;
  }

  private Map<String, Object> getValueMap(
      Map<? extends ExecutableElement, ? extends AnnotationValue> values) {
    Map<String, Object> result = new HashMap<String, Object>();
    for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : values
        .entrySet()) {
      String name = entry.getKey().getSimpleName().toString();
      Object value = entry.getValue().getValue();
      if (value instanceof TypeMirror) {
        TypeMirror tm = (TypeMirror) value;
        if (tm.getKind() == TypeKind.DECLARED) {
          DeclaredType declType = (DeclaredType) tm;
          TypeElement elem = (TypeElement) declType.asElement();
          result.put(name, elem.getQualifiedName().toString());
        } else {
          // TODO could this really happen?
          result.put(name, String.valueOf(value));
        }
      } else {
        result.put(name, value);
      }
    }
    return result;
  }

}
