package net.karneim.pojobuilder;

import net.karneim.pojobuilder.model.BaseBuilderM;
import net.karneim.pojobuilder.model.TypeM;
import org.stringtemplate.v4.STGroupFile;

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

    private static final String JAVAX_ANNOTATION_GENERATED = "javax.annotation.Generated";
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
        TypeMUtils typeMUtils = new TypeMUtils();
        BuilderModelProducer producer = new BuilderModelProducer(env, typeMUtils);
        TypeElement pojoType = (TypeElement) env.getTypeUtils().asElement(methodElement.getReturnType());
        return producer.produce(new Input(pojoType, methodElement));
    }

    /**
     * Annotation was set on a class. This is deprecated behaviour from versions prior to 2.3
     */
    @Override
    public Output visitTypeAsClass(TypeElement classElement, Void context) {
        LOG.fine("Processing " + ANNOTATION + " on class " + classElement.asType().toString());
        TypeMUtils typeMUtils = new TypeMUtils();
        BuilderModelProducer producer = new BuilderModelProducer(env, typeMUtils);
        return producer.produce(new Input(classElement));
    }

    private void createAllSourceCode(Output output) {
        createSourceCode(builderGenerator, output.getBuilder(), true);
        if (output.getManualBuilder() != null) {
            createSourceCode(manualBuilderGenerator, output.getManualBuilder(), false);
        }
    }

    private void createSourceCode(BuilderSourceGenerator generator, BaseBuilderM model, boolean overwrite) {
        try {
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
