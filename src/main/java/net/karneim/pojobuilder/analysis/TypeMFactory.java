package net.karneim.pojobuilder.analysis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.type.WildcardType;

import net.karneim.pojobuilder.model.ArrayTypeM;
import net.karneim.pojobuilder.model.PrimitiveTypeM;
import net.karneim.pojobuilder.model.TypeM;
import net.karneim.pojobuilder.model.TypeVariableM;
import net.karneim.pojobuilder.model.TypeWildcardM;

public class TypeMFactory {

  private final JavaModelAnalyzerUtil javaModelAnalyzerUtil;
  /*
   * This cache prevents an infinite loop when generic type parameters are defined recursivly e.g. A in Pair<A extends
   * Comparable<A>, B>
   */
  private final Map<TypeParameterElement, TypeVariableM> typeVarCache =
      new HashMap<TypeParameterElement, TypeVariableM>();

  public TypeMFactory(JavaModelAnalyzerUtil javaModelAnalyzerUtil) {
    this.javaModelAnalyzerUtil = javaModelAnalyzerUtil;
  }

  public TypeM getTypeM(TypeElement typeElem) {
    String packageName = javaModelAnalyzerUtil.getPackage(typeElem);
    String classname = javaModelAnalyzerUtil.getClassname(typeElem);
    TypeM result = new TypeM(packageName, classname).withTypeParameter(getTypeMArray(typeElem.getTypeParameters()));
    return result;
  }

  public TypeM getTypeM(TypeMirror typeMirror) {
    TypeKind kind = typeMirror.getKind();
    switch (kind) {
      case BOOLEAN:
        return PrimitiveTypeM.BOOLEAN;
      case CHAR:
        return PrimitiveTypeM.CHAR;
      case BYTE:
        return PrimitiveTypeM.BYTE;
      case SHORT:
        return PrimitiveTypeM.SHORT;
      case INT:
        return PrimitiveTypeM.INT;
      case LONG:
        return PrimitiveTypeM.LONG;
      case FLOAT:
        return PrimitiveTypeM.FLOAT;
      case DOUBLE:
        return PrimitiveTypeM.DOUBLE;
      case DECLARED:
        DeclaredType dt = (DeclaredType) typeMirror;
        return getTypeM(dt);
      case VOID:
        return PrimitiveTypeM.VOID;
      case ARRAY:
        ArrayType arrayType = (ArrayType) typeMirror;
        return new ArrayTypeM(getTypeM(arrayType.getComponentType()));
      case TYPEVAR:
        if (typeMirror instanceof TypeVariable) {
          TypeVariable typeVar = (TypeVariable) typeMirror;
          return getTypeVariableM((TypeParameterElement) typeVar.asElement());
        } else {
          throw new UnresolvedTypeException(
              String.format("Expected TypeVariable for %s (%s)", typeMirror, typeMirror.getClass().getName()));
        }
      case ERROR:
        String message = String.format(
            "%s could not be resolved! Possibly this class is not compiled, not imported, or not part of the classpath.",
            typeMirror);
        throw new IllegalArgumentException(message);
      default:
        throw new UnsupportedOperationException(
            String.format("Unexpected kind %s for typeMirror %s", kind, typeMirror.getClass().getName()));
    }
  }

  public TypeM getTypeM(DeclaredType type) {
    TypeElement typeElem = (TypeElement) type.asElement();
    String packageName = javaModelAnalyzerUtil.getPackage(typeElem);
    String classname = javaModelAnalyzerUtil.getClassname(typeElem);
    TypeM result = new TypeM(packageName, classname);

    for (TypeMirror typeArg : type.getTypeArguments()) {
      TypeKind kind = typeArg.getKind();
      switch (kind) {
        case TYPEVAR:
          TypeVariable typeVar = (TypeVariable) typeArg;
          TypeParameterElement typeParamElem = (TypeParameterElement) typeVar.asElement();
          TypeVariableM var = getTypeVariableM(typeParamElem);
          result.withTypeParameter(var);
          break;
        case WILDCARD:
          TypeMirror extendsBound = ((WildcardType) typeArg).getExtendsBound();
          TypeWildcardM wildcard = new TypeWildcardM();
          if (extendsBound != null) {
            TypeM bound = getTypeM(extendsBound);
            wildcard.whichExtends(bound);
          }
          TypeMirror superBound = ((WildcardType) typeArg).getSuperBound();
          if (superBound != null) {
            TypeM bound = getTypeM(superBound);
            wildcard.whichIsASupertypeOf(bound);
          }
          result.withTypeParameter(wildcard);
          break;
        default:
          TypeM typeArgElemTypeM = getTypeM(typeArg);
          result.withTypeParameter(typeArgElemTypeM);
      }
    }
    return result;
  }

  public TypeM[] getTypeMArray(List<? extends TypeParameterElement> typeParams) {
    if (typeParams == null) {
      return new TypeM[0];
    }
    TypeM[] result = new TypeM[typeParams.size()];
    for (int i = 0; i < result.length; ++i) {
      TypeParameterElement typeParameterElement = typeParams.get(i);
      TypeVariableM var = getTypeVariableM(typeParameterElement);
      result[i] = var;
    }
    return result;
  }

  private TypeVariableM getTypeVariableM(TypeParameterElement typeParameterElement) {
    TypeVariableM var = typeVarCache.get(typeParameterElement);
    if (var == null) {
      var = new TypeVariableM(typeParameterElement.getSimpleName().toString());
      typeVarCache.put(typeParameterElement, var);
      List<? extends TypeMirror> bounds = typeParameterElement.getBounds();
      for (TypeMirror bound : bounds) {
        if (!isJavaLangObject(bound)) {
          TypeM typeM = getTypeM(bound);
          var.whichExtends(typeM);
        }
      }
    }
    return var;
  }

  private boolean isJavaLangObject(TypeMirror bound) {
    if (bound.getKind() == TypeKind.DECLARED) {
      TypeElement typeElem = (TypeElement) ((DeclaredType) bound).asElement();
      String packageName = javaModelAnalyzerUtil.getPackage(typeElem);
      String classname = javaModelAnalyzerUtil.getClassname(typeElem);
      return "java.lang.Object".equals(packageName + "." + classname);
    }
    return false;
  }

}
