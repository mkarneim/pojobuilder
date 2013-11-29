package net.karneim.pojobuilder;

import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class BuilderSourceGenerator {
    private static final Logger LOG = Logger.getLogger(BuilderSourceGenerator.class.getName());
    private STGroup group;

    public BuilderSourceGenerator(STGroup group) {
        this.group = group;
    }

    public String generate(Object model) {
        ST template = group.getInstanceOf("builderClass");
        template.add("model", model);
        return template.render();
    }

    public void generate(Object model, Writer writer) throws IOException {
        String text = generate(model);
        LOG.finest(String.format("Generating source:%n%s", text));
        writer.append(text);
    }

}
