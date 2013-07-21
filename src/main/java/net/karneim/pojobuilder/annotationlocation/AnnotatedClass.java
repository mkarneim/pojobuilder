package net.karneim.pojobuilder.annotationlocation;

import net.karneim.pojobuilder.BuildException;
import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.TypeMUtils;
import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.model.TypeM;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;
import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.List;

public class AnnotatedClass implements AnnotationStrategy {

    private ProcessingEnvironment env;
    private final TypeElement classElement;
    private TypeMUtils typeMUtils;

    public AnnotatedClass(ProcessingEnvironment env, TypeElement classElement, TypeMUtils typeMUtils) {
        this.env = env;
        this.classElement = classElement;
        this.typeMUtils = typeMUtils;
    }

    @Override
    public TypeElement getPojoType() {
        return classElement;
    }

    @Override
    public GeneratePojoBuilder getAnnotation() {
        return classElement.getAnnotation(GeneratePojoBuilder.class);
    }

    @Override
    public ExecutableElement getFactoryMethod() {
        return null;
    }

    @Override
    public void addPropertyModelsForAnnotatedElement(BuilderM model) {
        addPropertyModelsForConstructor(model);
    }


    private void addPropertyModelsForConstructor(BuilderM builderModel) {
        List<ExecutableElement> constructors = ElementFilter.constructorsIn(env.getElementUtils().getAllMembers(
                classElement));
        ExecutableElement constr = findFirstAnnotatedConstructor(constructors, ConstructorProperties.class);
        if (constr != null) {
            ConstructorProperties constrProps = constr.getAnnotation(ConstructorProperties.class);
            String[] propertyNames = constrProps.value();
            List<? extends VariableElement> parameters = constr.getParameters();
            if (propertyNames.length != parameters.size()) {
                throw new BuildException(Diagnostic.Kind.ERROR,
                        String.format("Incorrect number of values in annotation %s on constructor %s! "
                                + "Expected %d, but was %d.", ConstructorProperties.class.getCanonicalName(), constr,
                                parameters.size(), propertyNames.length), constr);
            }

            // loop over all constructor parameters
            for (int i = 0; i < propertyNames.length; ++i) {
                String propertyName = propertyNames[i];
                TypeMirror propertyType = parameters.get(i).asType();
                TypeM propertyTypeM = typeMUtils.getTypeM(propertyType);

                PropertyM propM = builderModel.getOrCreateProperty(propertyName, propertyTypeM);
                propM.setParameterPos(i);
            }
        } else {
            constr = findDefaultConstructor(constructors);
        }

        if (constr != null) {
            // find all exceptions that can be thrown by this constructor
            List<? extends TypeMirror> throwTypes = constr.getThrownTypes();
            List<TypeM> exceptionTypes = new ArrayList<TypeM>();
            for (TypeMirror throwType : throwTypes) {
                TypeM exeptionType = typeMUtils.getTypeM(throwType);
                exceptionTypes.add(exeptionType);
            }
            builderModel.getBuildExceptions().addAll(exceptionTypes);
        } else {
            throw new BuildException(Diagnostic.Kind.ERROR, String.format(
                    "Missing default constructor OR constructor annotated with %s in class %s!",
                    ConstructorProperties.class.getCanonicalName(), classElement.getQualifiedName()),
                    classElement);
        }
    }

    private ExecutableElement findFirstAnnotatedConstructor(List<ExecutableElement> constructors,
                                                            Class<ConstructorProperties> annoType) {
        for (ExecutableElement constr : constructors) {
            if (constr.getAnnotation(annoType) != null) {
                return constr;
            }
        }
        return null;
    }

    private ExecutableElement findDefaultConstructor(List<ExecutableElement> constructors) {
        for (ExecutableElement constr : constructors) {
            if (constr.getParameters().size() == 0) {
                return constr;
            }
        }
        return null;
    }

}
