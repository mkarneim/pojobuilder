package net.karneim.pojobuilder;

import java.beans.ConstructorProperties;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementScanner6;

import net.karneim.pojobuilder.model.TypeM;
import net.karneim.pojobuilder.util.PropertyMBuilder;
import net.karneim.pojobuilder.util.StringUtil;

public class PropertyFinder extends ElementScanner6<Void, Void> {
    private static final Logger LOG = Logger.getLogger(PropertyFinder.class.getName());
    
    private Map<String, PropertyMBuilder> resultBuilders;
    private ProcessingEnvironment env;

    private String builderPackage;
    private TypeElement ownerTypeElement;
    private boolean visitConstructors = true;

    public PropertyFinder(Map<String, PropertyMBuilder> resultBuilders, ProcessingEnvironment env, String builderPackage, TypeElement ownerTypeElement) {
        this.resultBuilders = resultBuilders;
        this.env = env;
        this.builderPackage = builderPackage;
        this.ownerTypeElement = ownerTypeElement;
    }

    public boolean isVisitConstructors() {
        return visitConstructors;
    }

    public void setVisitConstructors(boolean visitConstructors) {
        this.visitConstructors = visitConstructors;
    }

    public Map<String, PropertyMBuilder> getResultBuilders() {
        return resultBuilders;
    }

    @Override
    public Void visitType(TypeElement e, Void p) {
        LOG.entering(PropertyFinder.class.getName(), String.format("visitType: e=%s",e));
        
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
        LOG.entering(PropertyFinder.class.getName(), String.format("foundExecutableAsMethod: e=%s",e));
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
        LOG.entering(PropertyFinder.class.getName(), String.format("foundExecutableAsConstructor: e=%s",e));
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
        String type = getType(param);
        PropertyMBuilder p = getPropertyMBuilder(propName, type);
        p.withType(TypeM.get(type)).withParameterPos(paramPos);
    }

    private void foundVariableAsField(VariableElement e, Void v) {
        LOG.entering(PropertyFinder.class.getName(), String.format("foundVariableAsField: e=%s",e));
        
        if (e.getModifiers().contains(Modifier.STATIC) || e.getModifiers().contains(Modifier.FINAL)) {
            return;
        }

        if (isAccessible(e)) {
            String type = getType(e);
            String propName = e.getSimpleName().toString();
            PropertyMBuilder p = getPropertyMBuilder(propName, type);

            p.withType(TypeM.get(type)).withAccessible(true);
        }
    }

    private boolean isAccessible(Element e) {
        if (e.getModifiers().contains(Modifier.PUBLIC)) {
            return true;
        }
        if (e.getModifiers().contains(Modifier.PROTECTED)) {
            return isAccessibleFromPackage(e, builderPackage);
        }
        return false;
    }

    private static boolean isAccessibleFromPackage(Element e, String builderPackage) {
        String ownerPackage = getOwnerPackage(e);
        return (equals(ownerPackage, builderPackage));
    }

    private static String getOwnerPackage(Element e) {
        String owner = e.getEnclosingElement().asType().toString();
        return StringUtil.getPackage(owner);
    }

    protected static boolean equals(Object a, Object b) {
        return (a == null && b == null) || (a != null && a.equals(b));
    }

    private void addPropertySetter(String propName, ExecutableElement e) {
        String methodName = e.getSimpleName().toString();
        String paramType = getType(e.getParameters().get(0));
        PropertyMBuilder p = getPropertyMBuilder(propName, paramType);
        p.withSetter(methodName).withType(TypeM.get(paramType));
    }

    private String getType(VariableElement variableElement) {
        // TypeMirror typeMirror = env.getTypeUtils().capture(
        // variableElement.asType());
        TypeMirror typeMirror = variableElement.asType();
        String name = getTypeName(typeMirror);
        return name;
    }

    private String getTypeName(TypeMirror typeMirror) {
        TypeKind kind = typeMirror.getKind();
        switch (kind) {
            case BOOLEAN:
                return Boolean.TYPE.getName();
            case CHAR:
                return Character.TYPE.getName();
            case BYTE:
                return Byte.TYPE.getName();
            case SHORT:
                return Short.TYPE.getName();
            case INT:
                return Integer.TYPE.getName();
            case LONG:
                return Long.TYPE.getName();
            case FLOAT:
                return Float.TYPE.getName();
            case DOUBLE:
                return Double.TYPE.getName();
            case DECLARED:
                if (typeMirror instanceof DeclaredType) {
                    DeclaredType dt = (DeclaredType)typeMirror;
                    List<? extends TypeMirror> typeArgs = dt.getTypeArguments();
                    if (!typeArgs.isEmpty()) {
                        // HERE! We have the type args!!! 
                        //System.out.println("typeArgs=" + Arrays.toString(typeArgs.toArray()));
                    }
                }
                Element element = env.getTypeUtils().asElement(typeMirror);
                //System.out.println("getTypeName: element=" + element);
                if (element instanceof TypeElement) {
                    TypeElement el = (TypeElement)element;
                    return el.getQualifiedName().toString();
                } else {
                    throw new IllegalStateException("element=" + element);
                }
            case VOID:
                return Void.TYPE.getName();
            case ARRAY:
                return typeMirror.toString();
            default:
                throw new UnsupportedOperationException("typeMirror=" + typeMirror.getClass().getName());
        }

    }

    private PropertyMBuilder getPropertyMBuilder(String propName, String type) {
        String fieldname = PropertyFinder.toFieldname(propName, type);
        PropertyMBuilder result = resultBuilders.get(fieldname);
        if (result == null) {
            result = new PropertyMBuilder().withName(propName).withFieldname(fieldname);
            resultBuilders.put(fieldname, result);
        }
        return result;
    }

    private static String toFieldname(String propName, String type) {
        String typeString = type.replaceAll("\\.", "\\$");
        typeString = typeString.replaceAll("\\[\\]", "\\$");
        return propName + "$" + typeString;
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