package net.karneim.pojobuilder.modelproducers;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.TypeMUtils;
import net.karneim.pojobuilder.annotationlocation.AnnotationStrategy;
import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.ManualBuilderM;
import net.karneim.pojobuilder.model.TypeM;
import net.karneim.pojobuilder.name.NameStrategy;
import net.karneim.pojobuilder.packages.PackageStrategy;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

import static net.karneim.pojobuilder.modelproducers.BuilderModelProducer.deriveTypeM;

public class GenerationGapModelProducer implements ModelProducer<ManualBuilderM> {

    private final ProcessingEnvironment env;
    private final TypeMUtils typeMUtils;
    private final NameStrategy nameStrategy;
    private final PackageStrategy packageStrategy;
    private final BuilderModelProducer producer;
    private final AnnotationStrategy annotationStrategy;

    public GenerationGapModelProducer(
            ProcessingEnvironment env,
            TypeMUtils typeMUtils,
            AnnotationStrategy annotationStrategy,
            NameStrategy nameStrategy,
            PackageStrategy packageStrategy,
            BuilderModelProducer producer)
    {
        this.env = env;
        this.typeMUtils = typeMUtils;
        this.nameStrategy = nameStrategy;
        this.packageStrategy = packageStrategy;
        this.annotationStrategy = annotationStrategy;
        this.producer = producer;
    }

    @Override
    public ManualBuilderM produce() {

        TypeElement pojoType = annotationStrategy.getPojoType();
        GeneratePojoBuilder annotation = annotationStrategy.getAnnotation();

        BuilderM builderModel = producer.getCachedResult();
        ManualBuilderM manualBuilderModel = new ManualBuilderM();

        manualBuilderModel.setSuperType(builderModel.getType());
        manualBuilderModel.setPojoType(builderModel.getPojoType());

        TypeM manualBuilderType = computeManualBuilderType(pojoType, annotation);
        manualBuilderModel.setType(manualBuilderType);

        return manualBuilderModel;

    }

    private TypeM computeManualBuilderType(TypeElement pojoTypeElement, GeneratePojoBuilder annotation) {
        String typeName = nameStrategy.getName(annotation, pojoTypeElement);
        String packageName = packageStrategy.getPackage(annotation, pojoTypeElement);
        TypeM result = deriveTypeM(packageName, typeName);
        result.getTypeParameters().addAll(typeMUtils.getTypeParameters(pojoTypeElement));
        return result;
    }

}
