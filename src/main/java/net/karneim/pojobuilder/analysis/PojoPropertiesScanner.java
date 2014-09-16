package net.karneim.pojobuilder.analysis;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;

import net.karneim.pojobuilder.model.FieldAccessM;
import net.karneim.pojobuilder.model.MethodM;
import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.model.SetterMethodM;
import net.karneim.pojobuilder.model.TypeM;

public class PojoPropertiesScanner {
  private final Elements elements;
  private final JavaModelAnalyzerUtil javaModelAnalyzerUtil;
  private final TypeMFactory typeMFactory;

  public PojoPropertiesScanner(Elements elements, JavaModelAnalyzerUtil javaModelAnalyzerUtil, TypeMFactory typeMFactory) {
    this.elements = elements;
    this.javaModelAnalyzerUtil = javaModelAnalyzerUtil;
    this.typeMFactory = typeMFactory;
  }

  public void scan(Input input, Output output) {
    scanFields(input, output);
    scanSetterMethods(input, output);
    scanGetterMethods(input, output);
  }

  private void scanSetterMethods(Input input, Output output) {
    TypeElement pojoTypeEl = input.getPojoElement();
    DeclaredType pojoClassType = (DeclaredType) pojoTypeEl.asType();
    List<? extends Element> memberEls = elements.getAllMembers(pojoTypeEl);
    // loop over all setter methods
    List<ExecutableElement> methodEls = ElementFilter.methodsIn(memberEls);
    for (ExecutableElement methodEl : methodEls) {
      if (!javaModelAnalyzerUtil.isStatic(methodEl) && javaModelAnalyzerUtil.isSetterMethod(methodEl)
          && !javaModelAnalyzerUtil.isDeclaredInObject(methodEl)
          && javaModelAnalyzerUtil.isAccessibleForBuilder(methodEl, output.getBuilderModel())) {
        String propertyName = javaModelAnalyzerUtil.getPropertyName(methodEl);
        ExecutableType execType = (ExecutableType) javaModelAnalyzerUtil.getType(pojoClassType, methodEl);
        TypeMirror propertyTypeMirror = execType.getParameterTypes().get(0);
        TypeM propertyType = typeMFactory.getTypeM(propertyTypeMirror);
        output
            .getBuilderModel()
            .getProperties()
            .getOrCreate(propertyName, propertyType)
            .writableVia(
                new SetterMethodM(methodEl.getSimpleName().toString(), methodEl.getModifiers()).withVarArgs(
                    methodEl.isVarArgs()).declaredIn(typeMFactory.getTypeM(input.getPojoType())));
        output.getInput().getOrginatingElements().add(javaModelAnalyzerUtil.getCompilationUnit(methodEl));
      }
    }
  }

  private void scanFields(Input input, Output output) {
    TypeElement pojoTypeEl = input.getPojoElement();
    List<? extends Element> memberEls = elements.getAllMembers(pojoTypeEl);
    // loop over all fields
    List<VariableElement> fieldEls = ElementFilter.fieldsIn(memberEls);
    for (VariableElement fieldEl : fieldEls) {
      if (!javaModelAnalyzerUtil.isStatic(fieldEl) && !javaModelAnalyzerUtil.isDeclaredInObject(fieldEl)
          && javaModelAnalyzerUtil.isAccessibleForBuilder(fieldEl, output.getBuilderModel())) {
        DeclaredType declType = (DeclaredType) pojoTypeEl.asType();
        TypeM propertyType = typeMFactory.getTypeM(javaModelAnalyzerUtil.getType(declType, fieldEl));
        output
            .getBuilderModel()
            .getProperties()
            .getOrCreate(fieldEl.getSimpleName().toString(), propertyType)
            .accessibleVia(
                new FieldAccessM(fieldEl.getModifiers()).declaredIn(typeMFactory.getTypeM(input.getPojoType())));
        output.getInput().getOrginatingElements().add(javaModelAnalyzerUtil.getCompilationUnit(fieldEl));
      }
    }
  }

  private void scanGetterMethods(Input input, Output output) {
    TypeElement pojoTypeEl = input.getPojoElement();
    DeclaredType pojoClassType = (DeclaredType) pojoTypeEl.asType();
    List<? extends Element> memberEls = elements.getAllMembers(pojoTypeEl);
    // loop over all setter methods
    List<ExecutableElement> methodsEls = ElementFilter.methodsIn(memberEls);
    for (ExecutableElement methodEl : methodsEls) {
      if (!javaModelAnalyzerUtil.isStatic(methodEl) && javaModelAnalyzerUtil.isGetterMethod(methodEl)
          && !javaModelAnalyzerUtil.isDeclaredInObject(methodEl)
          && javaModelAnalyzerUtil.isAccessibleForBuilder(methodEl, output.getBuilderModel())) {
        String propertyName = javaModelAnalyzerUtil.getPropertyName(methodEl);
        ExecutableType execType = (ExecutableType) javaModelAnalyzerUtil.getType(pojoClassType, methodEl);
        TypeMirror propertyTypeMirror = execType.getReturnType();
        TypeM propertyType = typeMFactory.getTypeM(propertyTypeMirror);

        PropertyM prop = output.getBuilderModel().getProperties().get(propertyName, propertyType);
        if (prop != null) {
          prop.readableVia(new MethodM(methodEl.getSimpleName().toString(), methodEl.getModifiers())
              .declaredIn(typeMFactory.getTypeM(input.getPojoType())));
        }
        output.getInput().getOrginatingElements().add(javaModelAnalyzerUtil.getCompilationUnit(methodEl));
      }
    }
  }


}
