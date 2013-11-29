package net.karneim.pojobuilder;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class AnnotationProcessor extends AbstractProcessor {

    private GeneratePojoBuilderProcessor generatePojoBuilderProcessor;

    @Override
    public void init(ProcessingEnvironment env) {
        super.init(env);
        this.generatePojoBuilderProcessor = new GeneratePojoBuilderProcessor(env);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet<String> result = new HashSet<String>();
        result.add(GeneratePojoBuilder.class.getName());
        return result;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            for (TypeElement currAnno : annotations) {
                if (currAnno.getQualifiedName().contentEquals(GeneratePojoBuilder.class.getName())) {
                    Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(currAnno);
                    for (Element elem : annotatedElements) {
                        if (elem.getKind() == ElementKind.CLASS) {
                            TypeElement typeElem = (TypeElement) elem;
                            generatePojoBuilderProcessor.process(typeElem);
                        } else if (elem.getKind() == ElementKind.METHOD) {
                            ExecutableElement execElem = (ExecutableElement) elem;
                            generatePojoBuilderProcessor.process(execElem);
                        }
                    }
                }

            }
        }
        return false;
    }

}
