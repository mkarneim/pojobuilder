package net.karneim.pojobuilder;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.FactoryM;
import net.karneim.pojobuilder.model.TypeM;
import net.karneim.pojobuilder.model.TypeParameterM;
import net.karneim.pojobuilder.util.BuilderMBuilder;
import net.karneim.pojobuilder.util.ExtendedTypeUtil;

public class GeneratePojoBuilderProcessor {

    private static final String BUILDER_CLASS_DEFAULT_POSTFIX = "Builder";
    private static final String PACKAGE_ATTRIBUTE_DEFAULT_VALUE = "#default";
    private static final String WITH_BASECLASS_ATTRIBUTE_NAME = "withBaseclass";

    private static final Logger LOG = Logger.getLogger(GeneratePojoBuilderProcessor.class.getName());

    private ProcessingEnvironment env;
    private ExtendedTypeUtil extTypeUtil;
    private BuilderSourceGenerator generator = new BuilderSourceGenerator();

    public GeneratePojoBuilderProcessor(ProcessingEnvironment env, BuilderSourceGenerator generator) {
        super();
        this.env = env;
        extTypeUtil = new ExtendedTypeUtil(env.getTypeUtils());
        this.generator = generator;
    }

    public void process(TypeElement productTypeElem, GeneratePojoBuilder annotation) {
        String productSimpleName = extTypeUtil.getSimpleName(productTypeElem.getQualifiedName().toString());

        String builderPackageName = getBuilderPackage(productTypeElem, annotation);
        String builderClassname = getBuilderClassname(builderPackageName, productSimpleName, annotation);

        String baseclassAttribute = getAttributeValue(productTypeElem, AnnotationProcessor.GENERATE_POJO_BUILDER_CLASS.getName(), WITH_BASECLASS_ATTRIBUTE_NAME);
        TypeM builderBaseclass = getBuilderBaseclass(baseclassAttribute);
        TypeM builderType = new TypeM(builderClassname);

        PropertyMap propertyMap = new PropertyMap();
        findProperties(productTypeElem, builderType.getPackage(), propertyMap, true);

        TypeM productType = extTypeUtil.getTypeM(productTypeElem);
        if (productType.isGeneric()) {
            for (TypeParameterM param : productType.getTypeParameters()) {
                builderType.getTypeParameters().add(param);
            }
        }
        BuilderM model = new BuilderMBuilder().withType(builderType).withSuperType(builderBaseclass).withProductType(productType).withProperties(propertyMap.build()).build();
        LOG.fine(String.format("Generated model:\n%s", model));
        createSourceCode(model);
    }

    public void process(ExecutableElement execElem, GeneratePojoBuilder annotation) {
        Set<Modifier> requiredModifiers = new HashSet<Modifier>();
        requiredModifiers.add(Modifier.PUBLIC);
        requiredModifiers.add(Modifier.STATIC);

        if (!execElem.getModifiers().containsAll(requiredModifiers)) {
            String message = String.format("Method must be public and static: %s", execElem);
            env.getMessager().printMessage(Diagnostic.Kind.ERROR, message);
            throw new RuntimeException(message);
        }

        TypeMirror productTypeMirror = execElem.getReturnType();
        TypeElement productTypeElem = (TypeElement)env.getTypeUtils().asElement(productTypeMirror);

        //
        String productSimpleName = extTypeUtil.getSimpleName(productTypeElem.getQualifiedName().toString());
        String builderPackageName = getBuilderPackage(productTypeElem, annotation);
        String builderClassname = getBuilderClassname(builderPackageName, productSimpleName, annotation);

        String baseclassAttribute = getAttributeValue(execElem, AnnotationProcessor.GENERATE_POJO_BUILDER_CLASS.getName(), WITH_BASECLASS_ATTRIBUTE_NAME);
        TypeM builderBaseclass = getBuilderBaseclass(baseclassAttribute);
        TypeM builderType = new TypeM(builderClassname);

        
        
        PropertyMap propertyMap = new PropertyMap();
        findProperties(productTypeElem, builderType.getPackage(), propertyMap, false);
        TypeM productType = extTypeUtil.getTypeM(productTypeElem);
        if (productType.isGeneric()) {
            for (TypeParameterM param : productType.getTypeParameters()) {
                builderType.getTypeParameters().add(param);
            }
        }
        FactoryM factoryM = createFactoryM(execElem, propertyMap);
        BuilderM model = new BuilderMBuilder().withFactory(factoryM).withType(builderType).withSuperType(builderBaseclass).withProductType(productType).withProperties(propertyMap.build()).build();
        LOG.fine(String.format("Generated model:\n%s", model));
        createSourceCode(model);
    }

    private FactoryM createFactoryM(ExecutableElement execElem, PropertyMap propertyMap) {
        TypeElement ownerElement = (TypeElement)execElem.getEnclosingElement();
        TypeM ownerType = extTypeUtil.getTypeM(ownerElement);
        String methodName = execElem.getSimpleName().toString();

        PropertyNames anno = execElem.getAnnotation(PropertyNames.class);
        if (anno != null) {
            String[] paramNames = anno.value();
            List<? extends VariableElement> params = execElem.getParameters();
            int i = 0;
            for (VariableElement param : params) {
                TypeM type = extTypeUtil.getTypeM(param);
                String name = paramNames[i];
                propertyMap.getEntry(name, type.getQualifiedName()).withParameterPos(i).withType(type);
                ++i;
            }
        }
        FactoryM result = new FactoryM(ownerType, methodName);
        return result;
    }

    private void createSourceCode(BuilderM model) {
        try {
            String builderClassname = model.getType().getQualifiedName();
            JavaFileObject jobj = env.getFiler().createSourceFile(builderClassname);
            Writer writer = jobj.openWriter();
            generator.generate(model, writer);
            writer.close();

            env.getMessager().printMessage(Diagnostic.Kind.NOTE, String.format("Generated class %s", builderClassname));
            LOG.fine(String.format("Generated %s", jobj.toUri()));

        } catch (IOException e) {
            env.getMessager().printMessage(Diagnostic.Kind.ERROR, String.format("Error while processing: %s", e));
            throw new UndeclaredThrowableException(e);
        }
    }

    private String getBuilderClassname(String packageName, String productSimpleName, GeneratePojoBuilder annotation) {
    	String namePattern = annotation.withName();
    	String result = namePattern.replaceAll("\\*", productSimpleName);
        if (packageName != null) {
            result = packageName + "." + result;
        }
        return result;
    }

    private String getBuilderPackage(TypeElement productClass, GeneratePojoBuilder annotation) {
    	String packagePattern = annotation.intoPackage();
    	if ( packagePattern.contains("*")) {
    		PackageElement packageElement = findPackage(productClass);
            if (packageElement.isUnnamed()) {
                return null;
            } else {
                String pojoPackage = packageElement.getQualifiedName().toString();
                String result = packagePattern.replaceAll("\\*", pojoPackage);
                return result;
            }
    	} else if ("".equals(packagePattern)) {
            return null;
        } else {
            return packagePattern;
        }
    }

    private PackageElement findPackage(TypeElement typeElement) {
        Element tmp = typeElement;
        // If tmp is a package, {@code null} is returned.
        while (tmp.getEnclosingElement() != null) {
            tmp = tmp.getEnclosingElement();
        }
        if (tmp instanceof PackageElement) {
            return (PackageElement)tmp;
        } else
            throw new IllegalStateException(String.format("Can't find enclosing package of element %s", typeElement));
    }

    private TypeM getBuilderBaseclass(String baseclassAttribute) {
        if (baseclassAttribute == null) {
            return TypeM.OBJECT;
        } else {
            return TypeM.get(getClassname(baseclassAttribute));
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

    private String getAttributeValue(Element annotatedElement, final String annotationName, final String attributeName) {
        for (AnnotationMirror am : annotatedElement.getAnnotationMirrors()) {
            if (annotationName.equals(am.getAnnotationType().toString())) {
                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : am.getElementValues().entrySet()) {
                    if (attributeName.equals(entry.getKey().getSimpleName().toString())) {
                        AnnotationValue value = entry.getValue();
                        return value.toString();
                    }
                }
                return null;
            }
        }
        throw new IllegalArgumentException(String.format("Can't find annotation '%s' on class '%s'", annotationName, annotatedElement.toString()));
    }

    private void findProperties(TypeElement productTypeElem, String builderPackage, PropertyMap propertyMap, boolean visitConstructor) {
        TypeElement current = productTypeElem;
        while (current != null) {
            PropertyFinder propertyFinder = new PropertyFinder(propertyMap, env, builderPackage, current);
            propertyFinder.setVisitConstructors(visitConstructor);

            current.accept(propertyFinder, null);
            current = (TypeElement)env.getTypeUtils().asElement(current.getSuperclass());
            visitConstructor = false;
        }
    }

}
