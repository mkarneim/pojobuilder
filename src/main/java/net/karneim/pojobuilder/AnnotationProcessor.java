package net.karneim.pojobuilder;

import static net.karneim.pojobuilder.filter.FilterFactory.*;

import java.io.IOException;
import java.util.ArrayList;
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

@SupportedAnnotationTypes(value = { "net.karneim.pojobuilder.GeneratePojoBuilder"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
// @SupportedOptions({ GeneratePojoBuilderAnnotationProcessor.GEN })
public class AnnotationProcessor extends AbstractProcessor {

	public static final Class<GeneratePojoBuilder> GENERATE_POJO_BUILDER_CLASS = GeneratePojoBuilder.class;

	private static final Logger LOG = Logger.getLogger(AnnotationProcessor.class.getName());

	// public static final String GEN = "gen";
	private ProcessingEnvironment env;
	private GeneratePojoBuilderProcessor generatePojoBuilderProcessor;

	@Override
	public void init(ProcessingEnvironment env) {
		super.init(env);
		this.generatePojoBuilderProcessor = new GeneratePojoBuilderProcessor(env, new BuilderSourceGenerator());
		this.env = env;
		// enableLogging();
		// String targetDir = env.getOptions().get(GEN);
		// System.out.println(GEN + "=" + targetDir);
	}

	public static void enableLogging() {
		try {
			LogManager.getLogManager().readConfiguration(
					AnnotationProcessor.class.getResourceAsStream("/logging.properties"));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		if (!roundEnv.processingOver()) {
			// @formatter:off
			for (TypeElement currAnno : filter(annotations).by(TypeElementFilter()
											.withQualifiedName(GENERATE_POJO_BUILDER_CLASS.getName()))
										.into(new ArrayList<TypeElement>())) {
				// @formatter:on
				Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(currAnno);
				// @formatter:off
				for( TypeElement annotatedClass: filter(annotatedElements).by(ElementFilter()
													.withAnnotation(GENERATE_POJO_BUILDER_CLASS)
													.withKind(ElementKind.CLASS)
												).asListOfType(TypeElement.class)) {
					// @formatter:on
					GeneratePojoBuilder annotation = annotatedClass.getAnnotation(GENERATE_POJO_BUILDER_CLASS);
					generatePojoBuilderProcessor.process(annotatedClass, annotation);
				}
				// @formatter:off
				for( ExecutableElement annotatedMethod: filter(annotatedElements).by(ElementFilter()
															.withAnnotation(GENERATE_POJO_BUILDER_CLASS)
															.withKind(ElementKind.METHOD)
														).asListOfType(ExecutableElement.class)) {
					// @formatter:on
					GeneratePojoBuilder annotation = annotatedMethod.getAnnotation(GENERATE_POJO_BUILDER_CLASS);
					generatePojoBuilderProcessor.process(annotatedMethod, annotation);
				}
			}
		}
		return false;
	}

}
