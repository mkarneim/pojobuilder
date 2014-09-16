package net.karneim.pojobuilder.analysis;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.util.Types;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class AnnotationHierarchyUtil {

  private final Types types;

  public AnnotationHierarchyUtil(Types types) {
    this.types = types;
  }

  /**
   * Filters the given set of annotation elements and returns only those that are 'triggering' the generation of a pojo
   * builder, including, of course, the {@link GeneratePojoBuilder} annotation and all custom made meta annotations that
   * are annotated with {@link GeneratePojoBuilder} or with another meta annotation.
   * 
   * @param aAnnotations the set of annotation elements that will be filtered
   * @param generatePojoBuilderAnnotation the type element representing the {@literal @}{@link GeneratePojoBuilder}
   *        annotation
   * @return those annotations that are triggering the generation of a pojo builder
   */
  public Set<TypeElement> filterTriggeringAnnotations(Set<? extends TypeElement> aAnnotations,
      TypeElement generatePojoBuilderAnnotation) {
    Set<TypeElement> result = new HashSet<TypeElement>();
    for (TypeElement annoEl : aAnnotations) {
      Set<TypeElement> hierarchy = getAnnotationHierarchy(annoEl);
      if (containsAnnotation(hierarchy, generatePojoBuilderAnnotation)) {
        result.add(annoEl);
      }
    }
    return result;
  }

  private Set<TypeElement> getAnnotationHierarchy(TypeElement annoTypeEl) {
    Set<TypeElement> result = new HashSet<TypeElement>();
    getAnnotationHierarchy(annoTypeEl, result);
    return result;
  }

  private void getAnnotationHierarchy(TypeElement annoTypeEl, Set<TypeElement> result) {
    if (result.add(annoTypeEl)) {
      List<? extends AnnotationMirror> annos = annoTypeEl.getAnnotationMirrors();
      for (AnnotationMirror anno : annos) {
        DeclaredType annoDeclType = anno.getAnnotationType();
        Element annoEl = annoDeclType.asElement();
        if (annoEl.getKind() == ElementKind.ANNOTATION_TYPE) {
          getAnnotationHierarchy((TypeElement) annoEl, result);
        }
      }
    }
  }

  private boolean containsAnnotation(Set<TypeElement> elems, TypeElement annoEl) {
    for (TypeElement el : elems) {
      if (types.isSameType(annoEl.asType(), el.asType())) {
        return true;
      }
    }
    return false;
  }

}
