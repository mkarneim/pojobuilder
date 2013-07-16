package net.karneim.pojobuilder;

import net.karneim.pojobuilder.model.BaseBuilderM;
import net.karneim.pojobuilder.model.TypeM;
import net.karneim.pojobuilder.name.NameStrategy;
import net.karneim.pojobuilder.name.ParameterisableNameStrategy;
import net.karneim.pojobuilder.packages.PackageStrategy;
import net.karneim.pojobuilder.packages.ParameterisablePackageStrategy;
import org.stringtemplate.v4.STGroupFile;

import javax.annotation.Generated;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementKindVisitor6;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.logging.Logger;

public class GeneratePojoBuilderProcessor extends ElementKindVisitor6<Output, Void> {

    private static final String JAVAX_ANNOTATION_GENERATED = Generated.class.getName();
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
        BuilderModelProducer producer = constructProducer(env);
        TypeElement pojoType = (TypeElement) env.getTypeUtils().asElement(methodElement.getReturnType());
        return producer.produce(new Input(pojoType, methodElement));
    }

    /**
     * Annotation was set on a class. This is deprecated behaviour from 2.2.x
     */
    @Override
    public Output visitTypeAsClass(TypeElement classElement, Void context) {
        LOG.fine("Processing " + ANNOTATION + " on class " + classElement.asType().toString());
        BuilderModelProducer producer = constructProducer(env);
        return producer.produce(new Input(classElement));
    }

    private BuilderModelProducer constructProducer(ProcessingEnvironment env) {
        TypeMUtils typeMUtils = new TypeMUtils(); // TODO why is this not a static util class?
        NameStrategy nameStrategy = new ParameterisableNameStrategy(env);
        PackageStrategy packageStrategy = new ParameterisablePackageStrategy(env);
        BuilderModelProducer producer = new BuilderModelProducer(
                env,
                typeMUtils,
                nameStrategy,
                packageStrategy
        );
        return producer;
    }

    private void createAllSourceCode(Output output) {
        createSourceCode(builderGenerator, output.getBuilder(), true);
        if (output.getManualBuilder() != null) {
            createSourceCode(manualBuilderGenerator, output.getManualBuilder(), false);
        }
    }

    private void createSourceCode(BuilderSourceGenerator generator, BaseBuilderM model, boolean overwrite) {
        try {
            // TODO we should not still be "producing" the model here
            model.getAdditionalImports().add(TypeM.get(JAVAX_ANNOTATION_GENERATED));

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
