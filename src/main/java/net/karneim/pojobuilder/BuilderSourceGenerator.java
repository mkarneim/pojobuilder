package net.karneim.pojobuilder;

import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

import net.karneim.pojobuilder.model.BuilderM;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class BuilderSourceGenerator {
    private static final Logger LOG = Logger.getLogger(BuilderSourceGenerator.class.getName());
    private STGroup group;

    public BuilderSourceGenerator() {
        group = new STGroupFile("Builder-template.stg");
    }

    public String generate(BuilderM model) {
        ST template = group.getInstanceOf("builderClass");
        template.add("model", model);
        return template.render();
    }

    public void generate(BuilderM model, Writer writer)
        throws IOException {
        String text = generate(model);
        LOG.finest( String.format("Generating source:\n%s", text));
        writer.append(text);
    }

}
