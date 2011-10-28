package net.karneim.pojobuilder;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.model.TypeM;
import net.karneim.pojobuilder.util.BuilderMBuilder;
import net.karneim.pojobuilder.util.PropertyMBuilder;
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
		TypeM builderSuperclassName = getBuilderSuperclass(productClass);
		String builderPackageName = getBuilderPackage(productClass);

		String productBasename = StringUtil.getBasename(productClass
				.getQualifiedName().toString());

		String builderClassname = getBuilderClassname(builderPackageName,
				productBasename);
		System.out.println("builderClassname=" + builderClassname);

		try {

			BuilderM model = new BuilderMBuilder()
					.withType(new TypeM(builderClassname))
					.withSuperType(builderSuperclassName)
					.withProductType(
							new TypeM(productClass.getQualifiedName()
									.toString()))
					.withProperties(
							findProperties(productClass, builderPackageName))
					.build();
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

	private String getBuilderClassname(String packageName,
			String productBasename) {
		String result = productBasename + "Builder";
		if (packageName != null) {
			result = packageName + "." + result;
		}
		return result;
	}

	private String getBuilderPackage(TypeElement productClass) {
		GeneratePojoBuilder annotation = productClass
				.getAnnotation(GeneratePojoBuilder.class);
		String packageAttributeValue = annotation.intoPackage();
		if ("#default".equals(packageAttributeValue)) {
			PackageElement packageElement = findPackage(productClass);
			System.out.println(">>>>" + packageElement.getClass().getName());
			if (packageElement.isUnnamed()) {
				return null;
			} else {
				return packageElement.getQualifiedName().toString();
			}
		} else if ("".equals(packageAttributeValue)) {
			return null;
		} else {
			return packageAttributeValue;
		}
	}

	private PackageElement findPackage(TypeElement typeElement) {
		Element tmp = typeElement;
		// If tmp is a package, {@code null} is returned.
		while (tmp.getEnclosingElement() != null) {
			tmp = tmp.getEnclosingElement();
		}
		if (tmp instanceof PackageElement) {
			return (PackageElement) tmp;
		} else
			throw new IllegalStateException(
					"Can't find enclosing package of element: " + typeElement);
	}

	private TypeM getBuilderSuperclass(TypeElement productClass) {
		// GeneratePojoBuilder anno =
		// productClass.getAnnotation(GeneratePojoBuilder.class);
		// return anno.withSuperclass().getName();
		final String annotationName = GeneratePojoBuilder.class.getName();
		final String attributeName = "withSuperclass";

		String value = getAttributeValue(productClass, annotationName,
				attributeName);
		if (value == null) {
			return TypeM.OBJECT;
		} else {
			return TypeM.get(getClassname(value));
		}
	}

	private String getClassname(String value) {
		int idx = value.lastIndexOf(".class");
		if (idx == -1) {
			return value;
		} else {
			return value.substring(0, idx);
		}
	}

	private String getAttributeValue(TypeElement annotatedTypeElement,
			final String annotationName, final String attributeName) {
		for (AnnotationMirror am : annotatedTypeElement.getAnnotationMirrors()) {
			if (annotationName.equals(am.getAnnotationType().toString())) {
				for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : am
						.getElementValues().entrySet()) {
					if (attributeName.equals(entry.getKey().getSimpleName()
							.toString())) {
						AnnotationValue value = entry.getValue();
						return value.toString();
					}
				}
				return null;
			}
		}
		throw new IllegalArgumentException(String.format(
				"Can't find annotation '%s' on class '%s'", annotationName,
				annotatedTypeElement.toString()));
	}

	private Collection<PropertyM> findProperties(TypeElement productClass,
			String builderPackage) {
		Map<String, PropertyMBuilder> result = new HashMap<String, PropertyMBuilder>();

		TypeElement current = productClass;
		while (current != null) {
			System.out.println("current=" + current);
			PropertyFinder propertyFinder = new PropertyFinder(result, env,
					builderPackage, current);
			propertyFinder.setVisitConstructors(current == productClass);

			current.accept(propertyFinder, null);
			current = (TypeElement) env.getTypeUtils().asElement(
					current.getSuperclass());
		}

		return buildProperties(result.values());
	}

	public Collection<PropertyM> buildProperties(
			Collection<PropertyMBuilder> builders) {
		List<PropertyM> result = new ArrayList<PropertyM>();
		for (PropertyMBuilder builder : builders) {
			result.add(builder.build());
		}
		return result;
	}
}
