package net.karneim.pojobuilder;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes("net.karneim.pojobuilder.GeneratePojoBuilder")
public class AnnotationProcessor extends AbstractProcessor {

    private GeneratePojoBuilderProcessor generatePojoBuilderProcessor;

    @Override
    public void init(ProcessingEnvironment env) {
        super.init(env);
        generatePojoBuilderProcessor = new GeneratePojoBuilderProcessor(env);
    }

    /**
     * Filter compilation rounds and annotations so that subsequent PojoBuilder code needs no safety checks.
     * <p/>
     * {@inheritDoc}
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            for (TypeElement currAnno : annotations) {
                if (annotations.iterator().next().getQualifiedName().contentEquals(GeneratePojoBuilder.class.getName())) {

                    assert annotations.size() == 1;  // We only declare support for one
                    Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(currAnno);
                    for (Element elem : annotatedElements) {
                        generatePojoBuilderProcessor.process(elem);
                    }
                    // return true; TODO Consume @GeneratePojoBuilder annotation?
                }
            }
        }
        return false;
    }

}

