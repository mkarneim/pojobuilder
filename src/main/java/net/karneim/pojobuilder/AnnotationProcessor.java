package net.karneim.pojobuilder;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

@SupportedSourceVersion(SourceVersion.RELEASE_6)
// @SupportedOptions({ GeneratePojoBuilderAnnotationProcessor.GEN })
public class AnnotationProcessor extends AbstractProcessor {

    public static final Class<GeneratePojoBuilder> GENERATE_POJO_BUILDER_CLASS = GeneratePojoBuilder.class;

    private static final Logger LOG = Logger.getLogger(AnnotationProcessor.class.getName());

    // public static final String GEN = "gen";
    private ProcessingEnvironment env;
    private NewGeneratePojoBuilderProcessor generatePojoBuilderProcessor;

    @Override
    public void init(ProcessingEnvironment env) {
        super.init(env);
        this.generatePojoBuilderProcessor = new NewGeneratePojoBuilderProcessor(env);
        this.env = env;
        // enableLogging();
        // String targetDir = env.getOptions().get(GEN);
        // System.out.println(GEN + "=" + targetDir);
    }
    
    

    @Override
	public Set<String> getSupportedAnnotationTypes() {
		HashSet<String> result = new HashSet<String>();
		result.add( GENERATE_POJO_BUILDER_CLASS.getName());
		return result;
	}



	public static void enableLogging() {
        try {
            LogManager.getLogManager().readConfiguration(AnnotationProcessor.class.getResourceAsStream("/logging.properties"));
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // System.out.println("supported options: "+
        // Arrays.toString(getSupportedOptions().toArray()));
        if (!roundEnv.processingOver()) {
            for (TypeElement currAnno : annotations) {
                // System.out.println("Found " + currAnno.getQualifiedName());
                if (currAnno.getQualifiedName().contentEquals(GENERATE_POJO_BUILDER_CLASS.getName())) {
                    Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(currAnno);
                    // System.out.println("[GenerateBuilder] annotatedElements="+
                    // Arrays.toString(annotatedElements.toArray()));
                    for (Element elem : annotatedElements) {
                        if (elem.getKind() == ElementKind.CLASS) {
                            TypeElement typeElem = (TypeElement)elem;
                            GeneratePojoBuilder annotation = typeElem.getAnnotation(GENERATE_POJO_BUILDER_CLASS);
                            generatePojoBuilderProcessor.process(typeElem, annotation);
                        } else if ( elem.getKind() == ElementKind.METHOD) {
                            ExecutableElement execElem = (ExecutableElement)elem;
                            GeneratePojoBuilder annotation = execElem.getAnnotation(GENERATE_POJO_BUILDER_CLASS);
                            generatePojoBuilderProcessor.process(execElem, annotation);
                        }
                    }
                }
                
            }
        }
        return false;
    }


}
