package net.karneim.pojobuilder.processor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.tools.Diagnostic;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileObject;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.analysis.AnnotationHierarchyUtil;
import net.karneim.pojobuilder.analysis.DirectivesFactory;
import net.karneim.pojobuilder.analysis.Input;
import net.karneim.pojobuilder.analysis.InputFactory;
import net.karneim.pojobuilder.analysis.InvalidElementException;
import net.karneim.pojobuilder.analysis.JavaModelAnalyzer;
import net.karneim.pojobuilder.analysis.JavaModelAnalyzerUtil;
import net.karneim.pojobuilder.analysis.Output;
import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.ManualBuilderM;
import net.karneim.pojobuilder.sourcegen.BuilderSourceGenerator;
import net.karneim.pojobuilder.sourcegen.ManualBuilderSourceGenerator;

import com.squareup.javawriter.JavaWriter;

public class AnnotationProcessor extends AbstractProcessor {
  private static final Logger LOG = Logger.getLogger(AnnotationProcessor.class.getName());
  private JavaModelAnalyzer javaModelAnalyzer;
  private InputFactory inputFactory;
  private JavaModelAnalyzerUtil javaModelAnalyzerUtil;
  private AnnotationHierarchyUtil annotationHierarchyUtil;

  private final Set<String> failedTypeNames = new HashSet<String>();
  private final Set<String> generatedTypeNames = new HashSet<String>();
  private final Map<Element, Exception> failedElementsMap = new HashMap<Element, Exception>();

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    HashSet<String> result = new HashSet<String>();
    result.add("*");
    return result;
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }

  private void initHelpers(ProcessingEnvironment env) {
    this.javaModelAnalyzerUtil =
        new JavaModelAnalyzerUtil(env.getElementUtils(), env.getTypeUtils());
    this.javaModelAnalyzer =
        new JavaModelAnalyzer(env.getElementUtils(), env.getTypeUtils(), javaModelAnalyzerUtil);
    this.inputFactory =
        new InputFactory(env.getTypeUtils(), new DirectivesFactory(env.getElementUtils(),
            env.getTypeUtils(), javaModelAnalyzerUtil));
    this.annotationHierarchyUtil = new AnnotationHierarchyUtil(env.getTypeUtils());
  }

  private void clearState() {
    javaModelAnalyzerUtil = null;
    javaModelAnalyzer = null;
    inputFactory = null;
    annotationHierarchyUtil = null;
    failedTypeNames.clear();
    generatedTypeNames.clear();
    failedElementsMap.clear();
  }

  /**
   * This processor claims NOT to process annotations exclusively.
   */
  private static final boolean ANNOTATIONS_NOT_CLAIMED_EXCLUSIVELY = false;

  @Override
  public boolean process(Set<? extends TypeElement> aAnnotations, RoundEnvironment aRoundEnv) {
    // per javadoc: A Processor must gracefully handle an empty set of annotations
    if (aAnnotations.isEmpty()) {
      return ANNOTATIONS_NOT_CLAIMED_EXCLUSIVELY;
    }
    try {
      initHelpers(processingEnv);
      if (!aRoundEnv.processingOver()) {

        Set<TypeElement> triggeringAnnotations =
            annotationHierarchyUtil.filterTriggeringAnnotations(aAnnotations,
                getTypeElement(GeneratePojoBuilder.class));
        List<Element> elementsToProcess = getAnnotatedElements(aRoundEnv, triggeringAnnotations);
        addElementsThatFailedInLastRound(elementsToProcess);
        resetFailedElements();

        List<Output> outputList = new ArrayList<Output>();
        for (Element elem : elementsToProcess) {
          try {
            // note(String.format("Processing %s", elem), elem);
            Input input = inputFactory.getInput(elem);
            Output output = javaModelAnalyzer.analyze(input);
            outputList.add(output);
          } catch (Exception ex) {
            addFailedElement(elem, ex);
          }
        }

        // Generate source files
        for (Output output : outputList) {
          try {
            generateSources(output);
          } catch (Exception ex) {
            error(ex, output.getInput().getAnnotatedElement());
          }
        }
      } else {
        // Last round
        showErrorsForFailedElements();
        clearState();
      }
    } catch (Throwable t) {
      processingEnv.getMessager().printMessage(Kind.ERROR, toString(t));
    }
    return ANNOTATIONS_NOT_CLAIMED_EXCLUSIVELY;
  }

  private void resetFailedElements() {
    failedElementsMap.clear();
    failedTypeNames.clear();
  }

  private void addElementsThatFailedInLastRound(List<Element> elementsToProcess) {
    elementsToProcess.addAll(javaModelAnalyzerUtil.findAnnotatedElements(
        getTypeElements(failedTypeNames), GeneratePojoBuilder.class));
  }

  private void addFailedElement(Element elem, Exception ex) {
    failedElementsMap.put(elem, ex);
    failedTypeNames.add(javaModelAnalyzerUtil.getCompilationUnit(elem).getQualifiedName()
        .toString());
  }

  private void showErrorsForFailedElements() {
    for (Map.Entry<Element, Exception> entry : failedElementsMap.entrySet()) {
      error(entry.getValue(), entry.getKey());
    }
  }

  private List<Element> getAnnotatedElements(RoundEnvironment aRoundEnv,
      Set<TypeElement> triggeringAnnotations) {
    List<Element> elementsToProcess = new ArrayList<Element>();
    for (TypeElement annoTypeEl : triggeringAnnotations) {
      elementsToProcess.addAll(aRoundEnv.getElementsAnnotatedWith(annoTypeEl));
    }
    return elementsToProcess;
  }

  private TypeElement getTypeElement(Class<?> cls) {
    return getTypeElement(cls.getName());
  }

  private TypeElement getTypeElement(String typeName) {
    return processingEnv.getElementUtils().getTypeElement(typeName);
  }

  private Collection<TypeElement> getTypeElements(Collection<String> typeNames) {
    List<TypeElement> result = new ArrayList<TypeElement>();
    for (String typeName : typeNames) {
      result.add(getTypeElement(typeName));
    }
    return result;
  }

  private void generateSources(Output output) throws IOException {
    if (!hasAlreadyBeenCreated(getTypeName(output.getBuilderModel()))) {
      generateBuilderImpl(output.getBuilderModel(), output.getInput().getOrginatingElements());
    }
    if (output.getManualBuilderModel() != null
        && !hasAlreadyBeenCreated(getTypeName(output.getManualBuilderModel()))
        && !typeExists(getTypeName(output.getManualBuilderModel()))) {
      generateManualBuilder(output.getManualBuilderModel(), output.getInput()
          .getOrginatingElements());
    }
  }

  private boolean hasAlreadyBeenCreated(String typename) {
    return this.generatedTypeNames.contains(typename);
  }

  private void generateBuilderImpl(BuilderM builderModel, Collection<Element> orginatingElements)
      throws IOException {
    String qualifiedName = getTypeName(builderModel);
    JavaFileObject jobj =
        processingEnv.getFiler().createSourceFile(qualifiedName, asArray(orginatingElements));
    Writer writer = jobj.openWriter();
    JavaWriter javaWriter = new JavaWriter(writer);
    BuilderSourceGenerator generator = new BuilderSourceGenerator(javaWriter);
    generator.generateSource(builderModel);
    writer.close();

    generatedTypeNames.add(qualifiedName);
    note(String.format("PojoBuilder: Generated class %s", qualifiedName), null);
    LOG.fine(String.format("Generated %s", jobj.toUri()));
  }

  private Element[] asArray(Collection<Element> elements) {
    Element[] result = new Element[elements.size()];
    elements.toArray(result);
    return result;
  }

  private void generateManualBuilder(ManualBuilderM manualBuilderModel,
      Collection<Element> orginatingElements) throws IOException {
    String qualifiedName = getTypeName(manualBuilderModel);
    JavaFileObject jobj =
        processingEnv.getFiler().createSourceFile(qualifiedName, asArray(orginatingElements));
    Writer writer = jobj.openWriter();
    JavaWriter javaWriter = new JavaWriter(writer);
    ManualBuilderSourceGenerator generator = new ManualBuilderSourceGenerator(javaWriter);
    generator.generateSource(manualBuilderModel);
    writer.close();

    generatedTypeNames.add(qualifiedName);
    note(String.format("PojoBuilder: Generated class %s", qualifiedName), null);
    LOG.fine(String.format("Generated %s", jobj.toUri()));
  }

  private boolean typeExists(String qualifiedName) {
    return processingEnv.getElementUtils().getTypeElement(qualifiedName) != null;
  }

  private String getTypeName(ManualBuilderM manualBuilderModel) {
    String qualifiedName = manualBuilderModel.getType().getName();
    return qualifiedName;
  }

  private String getTypeName(BuilderM builderModel) {
    String qualifiedName = builderModel.getType().getName();
    return qualifiedName;
  }

  private void error(Exception ex, Element processedElement) {
    if (ex instanceof InvalidElementException) {
      InvalidElementException invElemEx = (InvalidElementException) ex;
      Element elem = invElemEx.getElement();
      error(invElemEx.getMessage(), elem);
    } else {
      String message =
          String.format("PojoBuilder caught unexpected exception on element %s!%s",
              processedElement, toString(ex));
      error(message, processedElement);
    }
  }

  private void error(String msg, Element element) {
    if (element.asType().getKind() != TypeKind.ERROR) {
      processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, msg, element);
    } else {
      processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, msg, null);
    }
    LOG.severe(msg);
  }

  private void note(String msg, Element element) {
    processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, msg, element);
  }

  private String toString(Throwable ex) {
    if (ex == null) {
      return "";
    }
    StringWriter writer = new StringWriter();
    writer.append("\n");
    ex.printStackTrace(new PrintWriter(writer));
    return writer.toString();
  }

}
