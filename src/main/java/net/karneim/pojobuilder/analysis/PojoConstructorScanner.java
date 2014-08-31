package net.karneim.pojobuilder.analysis;

import java.beans.ConstructorProperties;
import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

import net.karneim.pojobuilder.model.ConstructorParameterM;
import net.karneim.pojobuilder.model.TypeM;

public class PojoConstructorScanner {
  private final JavaModelAnalyzerUtil javaModelAnalyzerUtil;
  private final TypeMFactory typeMFactory;

  public PojoConstructorScanner(JavaModelAnalyzerUtil javaModelAnalyzerUtil, TypeMFactory typeMFactory) {
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
        output
            .getBuilderModel()
            .getProperties()
            .getOrCreate(propertyName, propertyType)
            .writableVia(
                new ConstructorParameterM(i).withName(propertyName).withVarArgs(
                    constrEl.isVarArgs() && i == parameters.size() - 1));
        i++;
      }
    } else {
      // use @ConstructorProperties to map parameters to property names
      String[] propertyNames = constrPropsAnno.value();
      List<? extends VariableElement> parameters = constrEl.getParameters();
      if (propertyNames.length != parameters.size()) {
        String message =
            String.format("Incorrect number of values in annotation @%s! Expected %d, but was %d.",
                ConstructorProperties.class.getSimpleName(), parameters.size(), propertyNames.length);
        throw new InvalidElementException(message, constrEl);
      }

      for (int i = 0; i < propertyNames.length; ++i) {
        String propertyName = propertyNames[i];
        if (!javaModelAnalyzerUtil.isValidJavaIdentifier(propertyName)) {
          String message =
              String.format("Illegal value in annotation @%s! Value '%s' is not a valid identifier.",
                  ConstructorProperties.class.getSimpleName(), propertyName);
          throw new InvalidElementException(message, constrEl);
        }
        TypeMirror propertyTypeMirror = parameters.get(i).asType();
        TypeM propertyType = typeMFactory.getTypeM(propertyTypeMirror);
        output
            .getBuilderModel()
            .getProperties()
            .getOrCreate(propertyName, propertyType)
            .writableVia(
                new ConstructorParameterM(i).withName(propertyName).withVarArgs(
                    constrEl.isVarArgs() && i == parameters.size() - 1));
      }
    }
    output.getInput().getOrginatingElements().add(javaModelAnalyzerUtil.getCompilationUnit(constrEl));
  }

}
