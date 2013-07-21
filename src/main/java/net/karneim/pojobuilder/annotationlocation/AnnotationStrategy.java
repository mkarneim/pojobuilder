package net.karneim.pojobuilder.annotationlocation;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.model.BuilderM;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * Pluggable strategy to handle {@link net.karneim.pojobuilder.GeneratePojoBuilder} declared on different types
 */
public interface AnnotationStrategy {

    /**
     * The final type of the constructed pojo
     *
     * @return
     */
    TypeElement getPojoType();

    /**
     * @deprecated Direct access to annotation should not be required when strategy-refactoring is complete
     */
    @Deprecated
    GeneratePojoBuilder getAnnotation();

    @Deprecated
    ExecutableElement getFactoryMethod();

    /**
     * Compute all available annoation-location-specific properties for the pojo and add into builder
     *
     * @param model the builder to update.
     */
    // TODO Return Collection<PropertyM> then merge into BuilderM in the caller
    void addPropertyModelsForAnnotatedElement(BuilderM model);
}
