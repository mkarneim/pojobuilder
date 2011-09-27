package net.karneim.pojobuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;

import net.karneim.pojobuilder.model.BuilderM;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

public class BuilderSourceGenerator {
	private StringTemplateGroup group;

	public BuilderSourceGenerator() {
		group = new StringTemplateGroup( //
				new InputStreamReader(BuilderSourceGenerator.class
						.getClassLoader().getResourceAsStream("builder.st")) //
		);
	}

	public String generate(BuilderM model) {
		StringTemplate template = group.getInstanceOf("builderClass");
		template.setAttribute("model", model);
		return template.toString();
	}

	public void generate(BuilderM model, Writer writer) throws IOException {
		String text = generate(model);

		System.out.println("SourceGenerator:\n" + text);
		writer.append(text);
	}

}
