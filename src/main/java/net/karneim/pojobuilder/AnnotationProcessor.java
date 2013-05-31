package net.karneim.pojobuilder;

import java.util.Set;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementKindVisitor6;

@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes("net.karneim.pojobuilder.GeneratePojoBuilder")
public class AnnotationProcessor extends AbstractProcessor {

    private static final ElementVisitor VISITOR = new ElementVisitor();
	private GeneratePojoBuilderProcessor generatePojoBuilderProcessor;

    @Override
	public void init(ProcessingEnvironment env) {
		super.init(env);
		generatePojoBuilderProcessor = new GeneratePojoBuilderProcessor(env);
	}

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            for (TypeElement currAnno : annotations) {
                if (annotations.iterator().next().getQualifiedName().contentEquals(GeneratePojoBuilder.class.getName())) {

                    assert annotations.size()==1;  // We only declare support for one
                    Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(currAnno);
                    for (Element elem : annotatedElements) {
                        elem.accept(VISITOR, generatePojoBuilderProcessor);
                    }
                    // return true; TODO Consume @GeneratePojoBuilder annotation?
                }
            }
        }
        return false;
    }

    private static class ElementVisitor extends ElementKindVisitor6<Void,GeneratePojoBuilderProcessor> {

        @Override
        public Void visitExecutableAsConstructor(ExecutableElement e, GeneratePojoBuilderProcessor processor) {
            processor.process(e);
            return null;
        }

        @Override
        public Void visitExecutableAsMethod(ExecutableElement e, GeneratePojoBuilderProcessor processor) {
            processor.process(e);
            return null;
        }

        @Override
        public Void visitTypeAsClass(TypeElement e, GeneratePojoBuilderProcessor processor) {
            processor.process(e);
            return null;
        }
    }

}

