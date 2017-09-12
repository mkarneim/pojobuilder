package net.karneim.pojobuilder.analysis;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.ConstructorParameterM;
import net.karneim.pojobuilder.model.TypeM;

public class PojoConstructorScanner {
  private final Elements elements;
  private final JavaModelAnalyzerUtil javaModelAnalyzerUtil;
  private final TypeMFactory typeMFactory;

  public PojoConstructorScanner(Elements elements, JavaModelAnalyzerUtil javaModelAnalyzerUtil,
      TypeMFactory typeMFactory) {
    this.elements = elements;
    this.javaModelAnalyzerUtil = javaModelAnalyzerUtil;
    this.typeMFactory = typeMFactory;
  }

  public void scan(ExecutableElement constrEl, Output output) {
    ConstructorProperties constrPropsAnno = constrEl.getAnnotation(ConstructorProperties.class);
    if (constrPropsAnno == null) {
      // use declared parameter names to map parameters to property names
      List<? extends VariableElement> parameters = constrEl.getParameters();
      int i = 0;
      for (VariableElement paramEl : parameters) {
        String propertyName = paramEl.getSimpleName().toString();
        TypeMirror propertyTypeMirror = paramEl.asType();
        TypeM propertyType = typeMFactory.getTypeM(propertyTypeMirror);
        output.getBuilderModel().getProperties().getOrCreate(propertyName, propertyType)
            .writableVia(new ConstructorParameterM(i).withVarArgs(constrEl.isVarArgs() && i == parameters.size() - 1));
        i++;
      }
    } else {
      // use @ConstructorProperties to map parameters to property names
      String[] propertyNames = constrPropsAnno.value();
      List<? extends VariableElement> parameters = constrEl.getParameters();
      if (propertyNames.length != parameters.size()) {
        String message = String.format("Incorrect number of values in annotation @%s! Expected %d, but was %d.",
            ConstructorProperties.class.getSimpleName(), parameters.size(), propertyNames.length);
        throw new InvalidElementException(message, constrEl);
      }

      for (int i = 0; i < propertyNames.length; ++i) {
        String propertyName = propertyNames[i];
        if (!javaModelAnalyzerUtil.isValidJavaIdentifier(propertyName)) {
          String message = String.format("Illegal value in annotation @%s! Value '%s' is not a valid identifier.",
              ConstructorProperties.class.getSimpleName(), propertyName);
          throw new InvalidElementException(message, constrEl);
        }
        TypeMirror propertyTypeMirror = parameters.get(i).asType();
        TypeM propertyType = typeMFactory.getTypeM(propertyTypeMirror);
        output.getBuilderModel().getProperties().getOrCreate(propertyName, propertyType)
            .writableVia(new ConstructorParameterM(i).withVarArgs(constrEl.isVarArgs() && i == parameters.size() - 1));
      }
    }
    output.getInput().getOrginatingElements().add(javaModelAnalyzerUtil.getCompilationUnit(constrEl));
  }

  public void scan(TypeElement pojoTypeEl, Output output) {
    List<ExecutableElement> constructorEls = findAccessibleConstructors(pojoTypeEl, output.getBuilderModel());
    if (constructorEls.size() == 0) {
      return;
    }
    ExecutableElement constructor = null;
    for (ExecutableElement constrEl : constructorEls) {
      if (constrEl.getParameters().isEmpty()) {
        constructor = constrEl;
      } else if (constructor == null) {
        constructor = constrEl;
      } else if (constructor != null && !constructor.getParameters().isEmpty()) {
        throw new InvalidElementException(String.format(
            "Can't automatically choose which constructor to use for class %s. It has too many constructors!",
            pojoTypeEl), pojoTypeEl);
      }
    }
    if (constructor != null) {
      scan(constructor, output);
    }
  }

  private List<ExecutableElement> findAccessibleConstructors(TypeElement pojoTypeEl, BuilderM builderM) {
    List<? extends Element> memberEls = elements.getAllMembers(pojoTypeEl);
    List<ExecutableElement> result = new ArrayList<ExecutableElement>();
    for (Element memberEl : memberEls) {
      if (memberEl.getKind() == ElementKind.CONSTRUCTOR) {
        if (javaModelAnalyzerUtil.isAccessibleForBuilder(memberEl, builderM)) {
          result.add((ExecutableElement) memberEl);
        }
      }
    }
    return result;
  }
}
