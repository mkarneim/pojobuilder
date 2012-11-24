package study.buildermodelproducer;

import java.lang.annotation.Annotation;
import java.util.Map;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.TypeM;

public class BuilderModelProducer {

	private final ProcessingEnvironment env;

	public BuilderModelProducer(ProcessingEnvironment env) {
		super();
		this.env = env;
	}

	public BuilderM produceModel(TypeElement pojoTypeElement) {
		checkNotNull(pojoTypeElement, "pojoTypeElement==null");
		checkAnnotated(pojoTypeElement, GeneratePojoBuilder.class);
		GeneratePojoBuilder annotation = pojoTypeElement.getAnnotation(GeneratePojoBuilder.class);

		BuilderM result = new BuilderM();

		setProductType(pojoTypeElement, result);
		setBuilderType(pojoTypeElement, annotation, result);
		setSuperType(pojoTypeElement, result);

		return result;
	}

	private void setProductType(TypeElement pojoTypeElement, BuilderM result) {
		String productTypeName = pojoTypeElement.getQualifiedName().toString();
		TypeM productType = TypeM.get(productTypeName);
		result.setProductType(productType);
	}

	private void setBuilderType(TypeElement pojoTypeElement, GeneratePojoBuilder annotation, BuilderM result) {
		String builderTypeNamePattern = annotation.withName();
		// Simple

		String builderTypeName = builderTypeNamePattern.replace("*", pojoTypeElement.getSimpleName());
		PackageElement packageElement = env.getElementUtils().getPackageOf(pojoTypeElement);
		if (!packageElement.isUnnamed()) {
			builderTypeName = packageElement.getQualifiedName() + "." + builderTypeName;
		}
		TypeM builderType = TypeM.get(builderTypeName);
		result.setType(builderType);
	}

	private void setSuperType(TypeElement pojoTypeElement, BuilderM result) {
		String annotationClassname = GeneratePojoBuilder.class.getCanonicalName();
		TypeElement superTypeElement = getAnnotationClassAttributeValue(pojoTypeElement, annotationClassname,
				"withBaseclass");
		TypeM superType = TypeM.get(superTypeElement.getQualifiedName().toString());
		result.setSuperType(superType);
	}

	//
	// HELPER METHODS: there are candidates for separate components

	private void checkNotNull(Object obj, String message) {
		if (obj == null) {
			throw new IllegalArgumentException(message);
		}
	}

	private void checkAnnotated(TypeElement typeElement, Class<? extends Annotation> annoClass) {
		if (typeElement.getAnnotation(annoClass) == null) {
			throw new IllegalArgumentException(String.format("Missing required annotation %s on class %s!",
					annoClass.getCanonicalName(), typeElement.getQualifiedName()));
		}
	}

	private TypeElement getAnnotationClassAttributeValue(Element annotatedElement, final String annotationName,
			final String attributeName) {
		for (AnnotationMirror am : annotatedElement.getAnnotationMirrors()) {
			if (annotationName.equals(am.getAnnotationType().toString())) {
				Map<? extends ExecutableElement, ? extends AnnotationValue> valueMap = env.getElementUtils()
						.getElementValuesWithDefaults(am);
				for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : valueMap.entrySet()) {
					if (attributeName.equals(entry.getKey().getSimpleName().toString())) {
						AnnotationValue value = entry.getValue();
						if (value == null) {
							return null;
						} else {
							String valueStr = String.valueOf(value.getValue());
							// String classname = valueStr.substring(0,
							// valueStr.length()-".class".length());
							TypeElement result = env.getElementUtils().getTypeElement(valueStr);
							return result;
						}
					}
				}
				return null;
			}
		}
		throw new IllegalArgumentException(String.format("Missing annotation %s on class %s!", annotationName,
				annotatedElement.toString()));
	}

}
