package net.karneim.pojobuilder.util;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.util.Types;

import net.karneim.pojobuilder.model.TypeM;
import net.karneim.pojobuilder.model.TypeParameterM;

public class ExtendedTypeUtil {
    private Types types;

    public ExtendedTypeUtil(Types types) {
        super();
        this.types = types;
    }

    public static String getSimpleName(String qualifiedName) {
        if (qualifiedName == null) {
            return null;
        }
        int idx = qualifiedName.lastIndexOf('.');
        if (idx == -1) {
            return qualifiedName;
        } else {
            return qualifiedName.substring(idx + 1);
        }
    }

    public static String getPackage(String classname) {
        if (classname == null) {
            return null;
        }
        int idx = classname.lastIndexOf('.');
        if (idx == -1) {
            return null;
        } else {
            return classname.substring(0, idx);
        }
    }

    public TypeM getTypeM(TypeElement typeElem) {
        TypeM result = new TypeM(typeElem.getQualifiedName().toString());
        if (typeElem.getTypeParameters().isEmpty() == false) {
            for (TypeParameterElement param : typeElem.getTypeParameters()) {                
                //TypeParameterM tpm = new TypeParameterM(param.getSimpleName().toString());
                TypeM paramTypeM = getTypeM(param.asType()); // PATCHED 19.9.2012, mk
                TypeParameterM tpm = new TypeParameterM(paramTypeM);
                List<? extends TypeMirror> bounds = param.getBounds();
                for (TypeMirror bound : bounds) {
                    TypeM typeM = TypeM.get(bound.toString());
                    tpm.getBounds().add(typeM);
                }
                result.getTypeParameters().add(tpm);
            }
        }
        return result;
    }

    public TypeM getTypeM(DeclaredType type) {
        TypeElement typeElem = (TypeElement)type.asElement();
        TypeM result = new TypeM(typeElem.getQualifiedName().toString());
        if (type.getTypeArguments().isEmpty() == false) {
            for (TypeMirror typeArg : type.getTypeArguments()) {
                if (typeArg.getKind() == TypeKind.DECLARED) {
                    Element element = types.asElement(typeArg);
                    if (element instanceof TypeElement) {
                        TypeElement el = (TypeElement)element;
                        TypeM typeArgElemTypeM = getTypeM(typeArg);
                        TypeParameterM tpm = new TypeParameterM(typeArgElemTypeM); 
                        result.getTypeParameters().add(tpm);
                    } else {
                        throw new IllegalStateException("element=" + element);
                    }
                } else if (typeArg.getKind() == TypeKind.TYPEVAR) {
                    TypeVariable typeVar = (TypeVariable)typeArg;
                    TypeParameterElement typeParamElem = (TypeParameterElement)typeVar.asElement();
                    TypeM typeParamElemTypeM = getTypeM(typeParamElem.asType());
                    TypeParameterM tpm = new TypeParameterM(typeParamElemTypeM);
                    result.getTypeParameters().add(tpm);
                }
            }
        }
        return result;
    }

    public boolean isAccessibleFromPackage(Element e, String builderPackage) {
        String ownerPackage = getOwnerPackage(e);
        return (equals(ownerPackage, builderPackage));
    }

    public String getOwnerPackage(Element e) {
        String owner = e.getEnclosingElement().asType().toString();
        return getPackage(owner);
    }

    protected static boolean equals(Object a, Object b) {
        return (a == null && b == null) || (a != null && a.equals(b));
    }

    public TypeM getTypeM(VariableElement variableElement) {
        // TypeMirror typeMirror = env.getTypeUtils().capture(
        // variableElement.asType());

        TypeMirror typeMirror = variableElement.asType();
        // System.out.println(String.format("%s -> %s (%s) [%b] [%s]",
        // variableElement, typeMirror, typeMirror.getClass(),
        // typeMirror instanceof DeclaredType, typeMirror.getKind()));
        TypeM typeM = getTypeM(typeMirror);
        return typeM;
    }

    public TypeM getTypeM(TypeMirror typeMirror) {
        TypeKind kind = typeMirror.getKind();
        switch (kind) {
            case BOOLEAN:
                return TypeM.BOOLEAN_TYPE;
            case CHAR:
                return TypeM.CHAR_TYPE;
            case BYTE:
                return TypeM.BYTE_TYPE;
            case SHORT:
                return TypeM.SHORT_TYPE;
            case INT:
                return TypeM.INT_TYPE;
            case LONG:
                return TypeM.LONG_TYPE;
            case FLOAT:
                return TypeM.FLOAT_TYPE;
            case DOUBLE:
                return TypeM.DOUBLE_TYPE;
            case DECLARED:
                DeclaredType dt = (DeclaredType)typeMirror;
                Element element = types.asElement(typeMirror);
                if (element instanceof TypeElement) {
                    TypeElement el = (TypeElement)element;
                    //TypeM result = getTypeM(el); 
                    TypeM result = getTypeM(dt); // PATCHED 19.2.2012, mk
                    return result;
                } else {
                    throw new IllegalStateException("element=" + element);
                }
            case VOID:
                return TypeM.VOID_TYPE;
            case ARRAY:
                return TypeM.get(typeMirror.toString());
            case TYPEVAR:
                if (typeMirror instanceof TypeVariable) {
                    TypeVariable typeVar = (TypeVariable)typeMirror;
                    TypeM result = TypeM.get(typeMirror.toString()); // Ugly, since this can't see the actual type-var's value
                    // TODO: ??
                    TypeMirror upperBound = typeVar.getUpperBound();
                    TypeMirror lowerBound = typeVar.getUpperBound();
                    return result;
                } else {
                    throw new IllegalStateException(String.format("Expected TypeVariable for %s", typeMirror));
                }
            case ERROR:
            	Element errorElement = types.asElement(typeMirror);
            	if ( errorElement.getKind()==ElementKind.CLASS) {
            		TypeElement tel = (TypeElement)errorElement;
            		System.out.println("tel="+tel.getQualifiedName().toString());
            		return getTypeM((TypeElement)errorElement);
            	}
            	System.out.println("errorElement="+errorElement.getClass());
            	return TypeM.get(errorElement.getSimpleName().toString());
            default:
                throw new UnsupportedOperationException(String.format("Unexpected kind %s for typeMirror %s", kind, typeMirror.getClass().getName()));
        }

    }
}
