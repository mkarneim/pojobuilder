package net.karneim.pojobuilder;

import java.beans.ConstructorProperties;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.util.ElementScanner6;
import javax.tools.Diagnostic.Kind;

import net.karneim.pojobuilder.model.TypeM;
import net.karneim.pojobuilder.util.ExtendedTypeUtil;
import net.karneim.pojobuilder.util.PropertyMBuilder;

public class PropertyFinder extends ElementScanner6<Void, Void> {
    private static final Logger LOG = Logger.getLogger(PropertyFinder.class.getName());
    private PropertyMap propertyMap;

    private ProcessingEnvironment env;
    private ExtendedTypeUtil extTypeUtil;
    private String builderPackage;
    private TypeElement ownerTypeElement;
    private TypeElement staticTypeElem;
    private boolean visitConstructors = true;

    public PropertyFinder(PropertyMap propertyMap, ProcessingEnvironment env, String builderPackage, TypeElement ownerTypeElement, TypeElement staticTypeElem) {
    	this.propertyMap = propertyMap;
        this.env = env;
        extTypeUtil = new ExtendedTypeUtil(env.getTypeUtils());
        this.builderPackage = builderPackage;
        this.ownerTypeElement = ownerTypeElement;
        this.staticTypeElem = staticTypeElem;
        env.getMessager().printMessage(Kind.OTHER, String.format("Scanning properies of %s", ownerTypeElement.getSimpleName()));
    }

    public boolean isVisitConstructors() {
        return visitConstructors;
    }

    public void setVisitConstructors(boolean visitConstructors) {
        this.visitConstructors = visitConstructors;
    }

    public PropertyMap getPropertyMap() {
        return propertyMap;
    }

    @Override
    public Void visitType(TypeElement e, Void p) {
        LOG.entering(PropertyFinder.class.getName(), String.format("visitType: e=%s", e));

        if (e != ownerTypeElement) {
            return (Void)null;
        }
        return super.visitType(e, p);
    }

    @Override
    public Void visitExecutable(ExecutableElement e, Void p) {
        ElementKind k = e.getKind();
        switch (k) {
            case CONSTRUCTOR:
                if (visitConstructors) {
                    foundExecutableAsConstructor(e, p);
                }
                break;

            case METHOD:
                foundExecutableAsMethod(e, p);
                break;

            default:

        }

        return super.visitExecutable(e, p);
    }

    @Override
    public Void visitVariable(VariableElement e, Void p) {
        ElementKind k = e.getKind();
        switch (k) {
            case FIELD:
                foundVariableAsField(e, p);
            default:

        }

        return super.visitVariable(e, p);
    }

    private void foundExecutableAsMethod(ExecutableElement e, Void p) {
        LOG.entering(PropertyFinder.class.getName(), String.format("foundExecutableAsMethod: e=%s", e));
        String methodName = e.getSimpleName().toString();

        // We are note interested into getter methods here
        // if (isGetter(e)) {
        // String propName = getPropertyName(methodName);
        // addPropertyGetter(propName, e);
        //
        // }

        if (PropertyFinder.isSetter(e) && isAccessible(e)) {
            String propName = PropertyFinder.getPropertyName(methodName);

            addPropertySetter(propName, e);
        }

    }

    private void foundExecutableAsConstructor(ExecutableElement e, Void p) {
        LOG.entering(PropertyFinder.class.getName(), String.format("foundExecutableAsConstructor: e=%s", e));
        ConstructorProperties anno = e.getAnnotation(ConstructorProperties.class);
        if (anno == null) {
            return;
        }
        String[] paramNames = anno.value();
        List<? extends VariableElement> params = e.getParameters();
        int i = 0;
        for (VariableElement param : params) {

            String name = paramNames[i];
            addProperty(name, i, param);
            ++i;
        }
    }

    private void addProperty(String propName, int paramPos, VariableElement param) {
        TypeM type = extTypeUtil.getTypeM(param);
        PropertyMBuilder p = propertyMap.getEntry(propName, type.getQualifiedName());
        p.withType(type).withParameterPos(paramPos);
    }

    private void foundVariableAsField(VariableElement e, Void v) {
        LOG.entering(PropertyFinder.class.getName(), String.format("foundVariableAsField: e=%s", e));
        
        if (e.getModifiers().contains(Modifier.STATIC) || e.getModifiers().contains(Modifier.FINAL)) {
            return;
        }

        if (isAccessible(e)) {
        	TypeM type;
            if ( e.asType().getKind()==TypeKind.TYPEVAR) {
            	DeclaredType declType = (DeclaredType) staticTypeElem.asType();
            	TypeMirror typeMirror = env.getTypeUtils().asMemberOf(declType, e);
            	
            	type = extTypeUtil.getTypeM(typeMirror);
            } else {
            	type = extTypeUtil.getTypeM(e);	
            }
            String propName = e.getSimpleName().toString();
            PropertyMBuilder p = propertyMap.getEntry(propName, type.getQualifiedName());

            p.withType(type).withAccessible(true);
        }
    }

    private boolean isAccessible(Element e) {
        if (e.getModifiers().contains(Modifier.PUBLIC)) {
            return true;
        }
        if (!e.getModifiers().contains(Modifier.PRIVATE)) {
            return extTypeUtil.isAccessibleFromPackage(e, builderPackage);
        }
        return false;
    }

    private void addPropertySetter(String propName, ExecutableElement e) {
        String methodName = e.getSimpleName().toString();
        VariableElement param = e.getParameters().get(0);
        TypeM paramType;
        if ( param.asType().getKind()==TypeKind.TYPEVAR) {
        	DeclaredType declType = (DeclaredType) staticTypeElem.asType();
        	ExecutableType extype = (ExecutableType) env.getTypeUtils().asMemberOf(declType, e);
        	TypeMirror pType = extype.getParameterTypes().get(0);
        	paramType = extTypeUtil.getTypeM(pType);
        } else {
        	paramType = extTypeUtil.getTypeM(param);	
        }
        PropertyMBuilder p = propertyMap.getEntry(propName, paramType.getQualifiedName());
        p.withSetter(methodName).withType(paramType);
    }

    private static String convertToPropertyName(String name) {
        char[] vals = name.toCharArray();
        vals[0] = Character.toLowerCase(vals[0]);
        return String.valueOf(vals);
    }

    private static String getPropertyName(String setterMethodName) {
        if (setterMethodName == null) {
            return null;
        }
        return PropertyFinder.convertToPropertyName(setterMethodName.substring(3));
    }

    private static boolean isSetter(ExecutableElement ee) {
        String methodName = ee.getSimpleName().toString();
        TypeMirror retType = ee.getReturnType();

        if (ee.getModifiers().contains(Modifier.PRIVATE) || ee.getModifiers().contains(Modifier.STATIC)) {
            return false;
        }

        return methodName.startsWith("set") && methodName.length() > "set".length() && retType.getKind() == TypeKind.VOID && ee.getParameters().size() == 1;
    }

}