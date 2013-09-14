package net.karneim.pojobuilder;

import net.karneim.pojobuilder.annotationlocation.AnnotatedClass;
import net.karneim.pojobuilder.annotationlocation.AnnotatedFactoryMethod;
import net.karneim.pojobuilder.annotationlocation.AnnotationStrategy;
import net.karneim.pojobuilder.baseclass.BaseClassStrategy;
import net.karneim.pojobuilder.baseclass.WithBaseClass;
import net.karneim.pojobuilder.baseclass.WithoutBaseClass;
import net.karneim.pojobuilder.generationgap.GenerationGapNameStrategy;
import net.karneim.pojobuilder.model.BaseBuilderM;
import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.ManualBuilderM;
import net.karneim.pojobuilder.model.TypeM;
import net.karneim.pojobuilder.modelproducers.BuilderModelProducer;
import net.karneim.pojobuilder.modelproducers.DummyModelProducer;
import net.karneim.pojobuilder.modelproducers.GenerationGapModelProducer;
import net.karneim.pojobuilder.modelproducers.ModelProducer;
import net.karneim.pojobuilder.name.NameStrategy;
import net.karneim.pojobuilder.name.ParameterisableNameStrategy;
import net.karneim.pojobuilder.packages.PackageStrategy;
import net.karneim.pojobuilder.packages.ParameterisablePackageStrategy;
import org.stringtemplate.v4.STGroupFile;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementKindVisitor6;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Map;
import java.util.logging.Logger;

public class GeneratePojoBuilderProcessor extends ElementKindVisitor6<Output, Void> {

    private static final String ANNOTATION = GeneratePojoBuilder.class.getSimpleName();

    private static final Logger LOG = Logger.getLogger(GeneratePojoBuilderProcessor.class.getName());

    private final ProcessingEnvironment env;
    private final BuilderSourceGenerator builderGenerator;
    private final BuilderSourceGenerator manualBuilderGenerator;

    public GeneratePojoBuilderProcessor(ProcessingEnvironment env) {
        this.env = env;
        this.builderGenerator = new BuilderSourceGenerator(new STGroupFile("Builder-template.stg"));
        this.manualBuilderGenerator = new BuilderSourceGenerator(new STGroupFile("ManualBuilder-template.stg"));
    }

    public void process(Element elem) {
        Output output = elem.accept(this, null);
        createAllSourceCode(output);
    }

    /**
     * Temporary shim for testing before we change it to assert on the generated classes
     * themselves instead of the intermediate model
     */
    public Output testProcess(Element elem) {
        return elem.accept(this, null);
    }

    /**
     * Annotation was set on a constructor
     */
    @Override
    public Output visitExecutableAsConstructor(ExecutableElement constructorElement, Void context) {
        LOG.fine("Processing " + ANNOTATION + " on constructor " + constructorElement.asType().toString());
        return null;
    }

    /**
     * Annotation was set on a factory method
     */
    @Override
    public Output visitExecutableAsMethod(ExecutableElement methodElement, Void context) {
        LOG.fine("Processing " + ANNOTATION + " on method " + methodElement.asType().toString());
        TypeMUtils typeMUtils = new TypeMUtils(); // TODO why is this not a static util class?
        AnnotationStrategy annotationStrategy = new AnnotatedFactoryMethod(env, methodElement, typeMUtils);
        return buildOutput(methodElement, annotationStrategy);
    }

    /**
     * Annotation was set on a class. This is deprecated behaviour from 2.2.x
     */
    @Override
    public Output visitTypeAsClass(TypeElement classElement, Void context) {
        LOG.fine("Processing " + ANNOTATION + " on class " + classElement.asType().toString());
        TypeMUtils typeMUtils = new TypeMUtils(); // TODO why is this not a static util class?
        AnnotationStrategy annotationStrategy = new AnnotatedClass(env, classElement, typeMUtils);
        return buildOutput(classElement, annotationStrategy);
    }

    private Output buildOutput(Element element, AnnotationStrategy annotationStrategy) {

        TypeMUtils typeMUtils = new TypeMUtils(); // TODO why is this not a static util class?
        Producers producers = constructProducers(typeMUtils, env, element, annotationStrategy);

        BuilderM builderModel = producers.builderModelProducer.produce();
        ManualBuilderM manualBuilderModel = producers.generationGapModelProducer.produce();

        if (manualBuilderModel!=null) {
            // rewriting main builder to get around g-gap circular dependency
            // In g-gap-problem, we could use a generic for selfType in the abstract class to avoid this.
            // class AbstractBuilder<B extends AbstractBuilder> { B withProp() }
            builderModel.setSelfType(manualBuilderModel.getType());
        }

        return new Output(builderModel, manualBuilderModel);
    }

    // Exists only for g-gap problem. Ignore unclean code.
    private static final class Producers {
        ModelProducer<BuilderM> builderModelProducer;
        ModelProducer<ManualBuilderM> generationGapModelProducer;
        public Producers(ModelProducer<BuilderM> builderModelProducer, ModelProducer<ManualBuilderM> generationGapModelProducer) {
            this.builderModelProducer = builderModelProducer;
            this.generationGapModelProducer = generationGapModelProducer;
        }
    }

    /*
     * Compose a builderModelProducer from strategies, removing as many conditionals as possible from
     * any given implementation - especially the builderModelProducer itself
     */
    private Producers constructProducers(TypeMUtils typeMUtils, ProcessingEnvironment env, Element element, AnnotationStrategy annotationStrategy) {

        // Convoluted code here is due to g-gap circular issue
        boolean hasGenerationGap = element.getAnnotation(GeneratePojoBuilder.class).withGenerationGap();

        NameStrategy nameStrategy = new ParameterisableNameStrategy(env);
        PackageStrategy packageStrategy = new ParameterisablePackageStrategy(env);
        BaseClassStrategy baseClassStrategy = selectBaseClassStrategy(typeMUtils, element);
        NameStrategy builderNameStrategy = hasGenerationGap ? new GenerationGapNameStrategy(nameStrategy) : nameStrategy;

        BuilderModelProducer builderModelProducer = new BuilderModelProducer(
                env,
                typeMUtils,
                annotationStrategy,
                builderNameStrategy,
                packageStrategy,
                baseClassStrategy);

        ModelProducer<ManualBuilderM> generationGapModelProducer;
        if ( hasGenerationGap ) {
            generationGapModelProducer = new GenerationGapModelProducer(
                    env,
                    typeMUtils,
                    annotationStrategy,
                    nameStrategy,
                    packageStrategy,
                    builderModelProducer);
        } else {
            generationGapModelProducer = DummyModelProducer.dummyModelProducer();
        }

        return new Producers(builderModelProducer, generationGapModelProducer);
    }

    private BaseClassStrategy selectBaseClassStrategy(TypeMUtils typeMUtils, Element element) {
        AnnotationMirror am = getAnnotationMirror( element, GeneratePojoBuilder.class );
        TypeMirror baseClassType = getAnnotationClassAttributeValue( am, env, "withBaseclass" ); // Yuck
        if ( baseClassType!=null ) {
            TypeM baseClassTypeM = typeMUtils.getTypeM(baseClassType);
            if ( ! "java.lang.Object".equals(baseClassTypeM.getQualifiedName()) ) { // Yuck
                return new WithBaseClass( baseClassTypeM );
            }
        }
        return new WithoutBaseClass();
    }

    // Candidate for moving out
    private static AnnotationMirror getAnnotationMirror( Element element, Class<? extends Annotation> annotation ) {
        String expected = annotation.getName();
        for (AnnotationMirror am : element.getAnnotationMirrors()) {
            if (expected.equals(am.getAnnotationType().toString())) {
                return am;
            }
        }
        throw new IllegalStateException(String.format("Missing annotation %s on class %s!", annotation,
                element.toString()));
    }

    /**
     * Special code needed to read an attribute of type Class. TODO is this all really required
     * @param am
     * @param attributeName
     * @return
     */
    private static TypeMirror getAnnotationClassAttributeValue(AnnotationMirror am, ProcessingEnvironment env, final String attributeName) {
        Map<? extends ExecutableElement, ? extends AnnotationValue> valueMap = env.getElementUtils()
                .getElementValuesWithDefaults(am);
        for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : valueMap.entrySet()) {
            if (attributeName.equals(entry.getKey().getSimpleName().toString())) {
                return (TypeMirror)entry.getValue().getValue();
            }
        }
        return null;
    }

    private void createAllSourceCode(Output output) {
        createSourceCode(builderGenerator, output.getBuilder(), true);
        if (output.getManualBuilder() != null) {
            createSourceCode(manualBuilderGenerator, output.getManualBuilder(), false);
        }
    }

    private void createSourceCode(BuilderSourceGenerator generator, BaseBuilderM model, boolean overwrite) {
        try {
            String builderClassname = model.getType().getQualifiedName();

            boolean missing = env.getElementUtils().getTypeElement(builderClassname) == null;
            if (overwrite || missing) {
                JavaFileObject jobj = env.getFiler().createSourceFile(builderClassname);
                Writer writer = jobj.openWriter();
                generator.generate(model, writer);
                writer.close();

                env.getMessager().printMessage(Diagnostic.Kind.NOTE,
                        String.format("Generated class %s", builderClassname));
                LOG.fine(String.format("Generated %s", jobj.toUri()));
            }
        } catch (IOException e) {
            env.getMessager().printMessage(Diagnostic.Kind.ERROR, String.format("Error while processing: %s", e));
            throw new UndeclaredThrowableException(e);
        }
    }

}
