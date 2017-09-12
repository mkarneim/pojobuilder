package net.karneim.pojobuilder.analysis;

import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

import net.karneim.pojobuilder.FactoryProperties;
import net.karneim.pojobuilder.model.FactoryMethodM;
import net.karneim.pojobuilder.model.FactoryMethodParameterM;
import net.karneim.pojobuilder.model.TypeM;

public class FactoryMethodScanner {
  private final JavaModelAnalyzerUtil javaModelAnalyzerUtil;
  private final TypeMFactory typeMFactory;

  public FactoryMethodScanner(JavaModelAnalyzerUtil javaModelAnalyzerUtil, TypeMFactory typeMFactory) {
    this.javaModelAnalyzerUtil = javaModelAnalyzerUtil;
    this.typeMFactory = typeMFactory;
  }

  public void scan(ExecutableElement factoryMethodEl, Output output) {
    TypeM ownerType = typeMFactory.getTypeM((TypeElement) factoryMethodEl.getEnclosingElement());

    output.getBuilderModel().setFactoryMethod(
        new FactoryMethodM(factoryMethodEl.getSimpleName().toString(), factoryMethodEl.getModifiers())
            .declaredIn(ownerType));

    if (factoryMethodEl.getParameters().isEmpty()) {
      return;
    }
    FactoryProperties factoryPropertiesAnno = factoryMethodEl.getAnnotation(FactoryProperties.class);
    List<? extends VariableElement> parameters = factoryMethodEl.getParameters();
    if (factoryPropertiesAnno == null) {
      // use declared parameter names to map parameters to property names
      int i = 0;
      for (VariableElement param : parameters) {
        String propertyName = param.getSimpleName().toString();
        TypeMirror propertyTypeMirror = param.asType();
        TypeM propertyType = typeMFactory.getTypeM(propertyTypeMirror);
        output
          .getBuilderModel()
          .getProperties()
          .getOrCreate(propertyName, propertyType)
          .writableVia(new FactoryMethodParameterM(i)
            .withVarArgs(factoryMethodEl.isVarArgs() && i == parameters.size() - 1));
        i++;
      }
    } else {
      // use @FactoryProperties to map parameters to property names
      String[] propertyNames = factoryPropertiesAnno.value();
      if (propertyNames.length != parameters.size()) {
        String message =
            String.format("Incorrect number of values in annotation @%s! Expected %d, but was %d.",
                FactoryProperties.class.getSimpleName(), factoryMethodEl.getParameters().size(), propertyNames.length);
        throw new InvalidElementException(message, factoryMethodEl);
      }
      for (int i = 0; i < propertyNames.length; ++i) {
        String propertyName = propertyNames[i];
        if (!javaModelAnalyzerUtil.isValidJavaIdentifier(propertyName)) {
          String message =
              String.format("Illegal value in annotation @%s! Value '%s' is not a valid identifier.",
                  FactoryProperties.class.getSimpleName(), propertyName);
          throw new InvalidElementException(message, factoryMethodEl);
        }
        TypeMirror propertyTypeMirror = factoryMethodEl.getParameters().get(i).asType();
        TypeM propertyType = typeMFactory.getTypeM(propertyTypeMirror);
        output
          .getBuilderModel()
          .getProperties()
          .getOrCreate(propertyName, propertyType)
          .writableVia(new FactoryMethodParameterM(i)
            .withVarArgs(factoryMethodEl.isVarArgs() && i == parameters.size() - 1));
      }
    }
    output.getInput().getOrginatingElements().add(javaModelAnalyzerUtil.getCompilationUnit(factoryMethodEl));
  }

}
