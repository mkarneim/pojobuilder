package net.karneim.pojobuilder;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.logging.Logger;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

import net.karneim.pojobuilder.model.BaseBuilderM;
import net.karneim.pojobuilder.model.TypeM;

import org.stringtemplate.v4.STGroupFile;

public class GeneratePojoBuilderProcessor {

    private static final String JAVAX_ANNOTATION_GENERATED = "javax.annotation.Generated";

    private static final Logger LOG = Logger.getLogger(GeneratePojoBuilderProcessor.class.getName());

	private final ProcessingEnvironment env;
	private final BuilderSourceGenerator builderGenerator;
	private final BuilderSourceGenerator manualBuilderGenerator;

    public GeneratePojoBuilderProcessor(ProcessingEnvironment env) {
        this.env = env;
        this.builderGenerator = new BuilderSourceGenerator(new STGroupFile("Builder-template.stg"));
        this.manualBuilderGenerator = new BuilderSourceGenerator(new STGroupFile("ManualBuilder-template.stg"));
    }

    public void process(TypeElement productTypeElem) {
        TypeMUtils typeMUtils = new TypeMUtils();
        BuilderModelProducer producer = new BuilderModelProducer(env, typeMUtils);
        Output output = producer.produce(new Input(productTypeElem));

        createAllSourceCode(output);
    }

    public void process(ExecutableElement execElem) {
        TypeMUtils typeMUtils = new TypeMUtils();
        BuilderModelProducer producer = new BuilderModelProducer(env, typeMUtils);
        TypeElement productTypeElem = (TypeElement) env.getTypeUtils().asElement(execElem.getReturnType());
        Output output = producer.produce(new Input(productTypeElem, execElem));

        createAllSourceCode(output);
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
