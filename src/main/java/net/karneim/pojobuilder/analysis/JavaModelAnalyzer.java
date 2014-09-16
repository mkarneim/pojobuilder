package net.karneim.pojobuilder.analysis;

import java.util.logging.Logger;

import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import net.karneim.pojobuilder.model.BuildMethodM;
import net.karneim.pojobuilder.model.CopyMethodM;
import net.karneim.pojobuilder.model.ManualBuilderM;
import net.karneim.pojobuilder.model.TypeListM;
import net.karneim.pojobuilder.model.TypeM;

public class JavaModelAnalyzer {

  private static final Logger LOG = Logger.getLogger(JavaModelAnalyzer.class.getName());

  private final Elements elements;
  private TypeMFactory typeMFactory;
  private JavaModelAnalyzerUtil javaModelAnalyzerUtil;
  private FactoryMethodScanner factoryMethodScanner;
  private PojoConstructorScanner pojoConstructorScanner;
  private PojoPropertiesScanner pojoPropertiesScanner;

  public JavaModelAnalyzer(Elements elements, Types types, JavaModelAnalyzerUtil javaModelAnalyzerUtil) {
    this.elements = elements;
    this.javaModelAnalyzerUtil = javaModelAnalyzerUtil;
    this.typeMFactory = new TypeMFactory(javaModelAnalyzerUtil);
    this.factoryMethodScanner = new FactoryMethodScanner(javaModelAnalyzerUtil, typeMFactory);
    this.pojoConstructorScanner = new PojoConstructorScanner(javaModelAnalyzerUtil, typeMFactory);
    this.pojoPropertiesScanner = new PojoPropertiesScanner(elements, javaModelAnalyzerUtil, typeMFactory);
  }

  public Output analyze(Input input) {
    LOG.fine(String.format("elem=%s", input));
    Output result = new Output(input);

    TypeM pojoType = typeMFactory.getTypeM(input.getPojoType());
    result.getBuilderModel().setPojoType(pojoType);
    result.getBuilderModel().setBuildMethod(new BuildMethodM());

    if (input.getDirectives().isGenerationGap()) {
      processBaseClass(result);
      processGenericBuilderInterface(result);

      result.getBuilderModel().setType(
          new TypeM(constructBuilderPackage(input), constructAbstractBuilderClassname(input))
              .withTypeParameter(pojoType.getTypeParameters().collectDistinctTypeVariablesRecursevly(new TypeListM())
                  .asArray()));
      result.getBuilderModel().setAbstract(true);

      result.setManualBuilderModel(new ManualBuilderM());
      result.getManualBuilderModel().setType(
          new TypeM(constructBuilderPackage(input), constructBuilderClassname(input)).withTypeParameter(pojoType
              .getTypeParameters().collectDistinctTypeVariablesRecursevly(new TypeListM()).asArray()));
      result.getManualBuilderModel().setBaseType(result.getBuilderModel().getType());
      result.getManualBuilderModel().setPojoType(pojoType);
      result.getBuilderModel().setSelfType(result.getManualBuilderModel().getType());
    } else {
      processBaseClass(result);
      processGenericBuilderInterface(result);

      result.getBuilderModel().setType(
          new TypeM(constructBuilderPackage(input), constructBuilderClassname(input)).withTypeParameter(pojoType
              .getTypeParameters().collectDistinctTypeVariablesRecursevly(new TypeListM()).asArray()));
      result.getBuilderModel().setSelfType(result.getBuilderModel().getType());
    }
    
    scanSourceCode(input, result);
    if (input.getDirectives().isGenerateCopyMethod()) {
      result.getBuilderModel().setCopyMethod(new CopyMethodM(input.getDirectives().getCopyMethodName()));
    }
    result.getBuilderModel().setHasBuilderProperties(input.getDirectives().isGenerateBuilderProperties());
    return result;
  }

  private void processGenericBuilderInterface(Output output) {
    if (output.getInput().getDirectives().getBuilderInterfaceName().equals(Void.class.getName())) {
      return;
    }
    String builderInterfaceName = output.getInput().getDirectives().getBuilderInterfaceName();
    TypeElement interfaceTypeElement = elements.getTypeElement(builderInterfaceName);
    if (interfaceTypeElement == null) {
      String message = String.format("Can not resolve interface %s!", builderInterfaceName);
      throw new InvalidElementException(message, output.getInput().getAnnotatedElement());
    }
    TypeM interfaceType = typeMFactory.getTypeM(interfaceTypeElement);
    if (interfaceTypeElement.getTypeParameters().size() != 1) {
      String message =
          String.format("Illegal interface %s! A builder's interface must declare exactly 1 generic type parameter!",
              interfaceTypeElement.getSimpleName());
      throw new InvalidElementException(message, output.getInput().getAnnotatedElement());
    }
    TypeParameterElement typeParamEl = interfaceTypeElement.getTypeParameters().get(0);
    if (!javaModelAnalyzerUtil.isUpperBoundToObject(typeParamEl)) {
      String message =
          String
              .format(
                  "Illegal interface %s! A builder's interface must declare exactly 1 generic type parameter with an upper bound of Object!",
                  interfaceTypeElement.getSimpleName());
      throw new InvalidElementException(message, output.getInput().getAnnotatedElement());
    }
    interfaceType.getTypeParameters().clear();
    interfaceType.getTypeParameters().add(output.getBuilderModel().getPojoType());

    boolean hasBuildMethod =
        javaModelAnalyzerUtil.hasBuildMethod(interfaceTypeElement, output.getInput().getPojoElement().asType());
    if (hasBuildMethod) {
      output.getBuilderModel().getBuildMethod().setOverrides(true);
    } else {
      String message =
          String.format("Illegal interface %s! A builder's interface must declare a generic method \"%s build()\"!",
              interfaceTypeElement.getSimpleName(), typeParamEl.getSimpleName());
      throw new InvalidElementException(message, output.getInput().getAnnotatedElement());
    }
    output.getBuilderModel().setInterfaceType(interfaceType);
    output.getInput().getOrginatingElements().add(javaModelAnalyzerUtil.getCompilationUnit(interfaceTypeElement));
  }

  private void processBaseClass(Output output) {
    TypeElement baseTypeElement = elements.getTypeElement(output.getInput().getDirectives().getBaseclassName());
    if (baseTypeElement.getModifiers().contains(Modifier.FINAL)) {
      String message =
          String.format("Illegal baseclass %s! A builder's baseclass must not be final.",
              baseTypeElement.getSimpleName());
      throw new InvalidElementException(message, output.getInput().getAnnotatedElement());
    }
    TypeM baseType = typeMFactory.getTypeM(baseTypeElement);
    if (!baseTypeElement.getTypeParameters().isEmpty()) {
      TypeElement pojoTypeElement = output.getInput().getPojoElement();
      if (baseTypeElement.getTypeParameters().size() > 1) {
        String message =
            String.format(
                "Illegal baseclass %s! A builder's baseclass must not have more than 1 generic type parameter!",
                baseTypeElement.getSimpleName());
        throw new InvalidElementException(message, output.getInput().getAnnotatedElement());
      }
      TypeParameterElement typeParamEl = baseTypeElement.getTypeParameters().get(0);
      if (!javaModelAnalyzerUtil.matchesUpperBound(pojoTypeElement, typeParamEl)) {
        String message =
            String.format("Illegal baseclass %s! %s can not be assigned to type parameter %s!",
                baseTypeElement.getSimpleName(), pojoTypeElement.getSimpleName(), typeParamEl.getSimpleName());
        throw new InvalidElementException(message, output.getInput().getAnnotatedElement());
      }
      baseType.getTypeParameters().clear();
      baseType.getTypeParameters().add(output.getBuilderModel().getPojoType());
    }
    boolean hasBuildMethod =
        javaModelAnalyzerUtil.hasBuildMethod(baseTypeElement, output.getInput().getPojoElement().asType());
    if (hasBuildMethod) {
      output.getBuilderModel().getBuildMethod().setOverrides(true);
    }
    output.getBuilderModel().setBaseType(baseType);
    output.getInput().getOrginatingElements().add(javaModelAnalyzerUtil.getCompilationUnit(baseTypeElement));
  }

  private void scanSourceCode(Input input, Output result) {
    // TODO remove input parameter since the value is already contained in Output
    if (input.getAnnotatedElement().getKind() == ElementKind.CONSTRUCTOR) {
      pojoConstructorScanner.scan((ExecutableElement) input.getAnnotatedElement(), result);
    } else if (input.getAnnotatedElement().getKind() == ElementKind.METHOD) {
      factoryMethodScanner.scan((ExecutableElement) input.getAnnotatedElement(), result);
    }
    pojoPropertiesScanner.scan(input, result);
  }

  private String constructAbstractBuilderClassname(Input input) {
    String result = "Abstract" + constructBuilderClassname(input);
    if (!javaModelAnalyzerUtil.isValidJavaIdentifier(result)) {
      String message =
          String.format(
              "Can't construct abstract builder's classname! The value \"%s\" is not a valid Java identifier.", result);
      throw new InvalidElementException(message, input.getAnnotatedElement());
    }
    return result;
  }

  private String constructBuilderClassname(Input input) {
    String result =
        input.getDirectives().getBuilderName().replaceAll("\\*", input.getPojoElement().getSimpleName().toString());
    if (!javaModelAnalyzerUtil.isValidJavaIdentifier(result)) {
      String message =
          String
              .format("Can't construct builder's classname! The value \"%s\" is not a valid Java identifier.", result);
      throw new InvalidElementException(message, input.getAnnotatedElement());
    }
    return result;
  }

  private String constructBuilderPackage(Input input) {
    String result =
        input.getDirectives().getIntoPackage().replaceAll("\\*", javaModelAnalyzerUtil.getPackage(input.getPojoType()));
    if (!javaModelAnalyzerUtil.isValidJavaPackageName(result)) {
      String message =
          String.format("Can't construct builder's package! The value \"%s\" is not a valid Java identifier.", result);
      throw new InvalidElementException(message, input.getAnnotatedElement());
    }
    return result;
  }

}
