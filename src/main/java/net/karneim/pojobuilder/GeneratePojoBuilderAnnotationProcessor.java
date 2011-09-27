package net.karneim.pojobuilder;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Collection;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.model.TypeM;
import net.karneim.pojobuilder.util.BuilderMBuilder;
import net.karneim.pojobuilder.util.StringUtil;

@SupportedAnnotationTypes(value = { "net.karneim.pojobuilder.GeneratePojoBuilder" })
@SupportedSourceVersion(SourceVersion.RELEASE_6)
// @SupportedOptions({ GeneratePojoBuilderAnnotationProcessor.GEN })
public class GeneratePojoBuilderAnnotationProcessor extends AbstractProcessor {
	// public static final String GEN = "gen";
	private ProcessingEnvironment env;
	private BuilderSourceGenerator generator = new BuilderSourceGenerator();

	@Override
	public void init(ProcessingEnvironment env) {
		super.init(env);
		this.env = env;
		// String targetDir = env.getOptions().get(GEN);
		// System.out.println(GEN + "=" + targetDir);
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		// System.out.println("supported options: "+
		// Arrays.toString(getSupportedOptions().toArray()));
		if (!roundEnv.processingOver()) {
			for (TypeElement currAnno : annotations) {
				// System.out.println("Found " + currAnno.getQualifiedName());
				if (currAnno.getQualifiedName().contentEquals(
						GeneratePojoBuilder.class.getName())) {
					Set<? extends Element> annotatedElements = roundEnv
							.getElementsAnnotatedWith(currAnno);
					// System.out.println("[GenerateBuilder] annotatedElements="+
					// Arrays.toString(annotatedElements.toArray()));
					for (Element elem : annotatedElements) {
						if (elem.getKind() == ElementKind.CLASS) {
							TypeElement typeElem = (TypeElement) elem;
							process(typeElem);
						}
					}
				}
			}
		}
		return false;
	}

	public void process(TypeElement productClass) {
		Name name = productClass.getQualifiedName();
		String basename = StringUtil.getBasename(name.toString());
		String packname = StringUtil.getPackage(name.toString());

		String builderClassname = packname + "." + basename + "Builder";
		System.out.println("builderClassname=" + builderClassname);

		try {

			BuilderM model = new BuilderMBuilder()
					.withType(new TypeM(builderClassname))
					.withProductType(new TypeM(name.toString()))
					.withProperties(findProperties(productClass)).build();
			System.out.println("model=" + model);

			JavaFileObject jobj = env.getFiler().createSourceFile(
					builderClassname);
			Writer writer = jobj.openWriter();

			generator.generate(model, writer);

			writer.close();
			// processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
			// "Generated class "+builderClassname,
			// elem);

			// processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
			// "Blablabla",
			// elem);
			System.out.println("uri=" + jobj.toUri());
		} catch (IOException e) {
			e.printStackTrace();
			throw new UndeclaredThrowableException(e);
		}
	}

	private Collection<PropertyM> findProperties(TypeElement productClass) {
		System.out.println("###findProperties");
		PropertyFinder propertyFinder = new PropertyFinder(env);
		productClass.accept(propertyFinder, null);

		return propertyFinder.getProperties();
	}
}
