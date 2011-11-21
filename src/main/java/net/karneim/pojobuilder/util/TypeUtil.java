package net.karneim.pojobuilder.util;

import java.util.List;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;

import net.karneim.pojobuilder.model.TypeM;
import net.karneim.pojobuilder.model.TypeParameterM;

public class TypeUtil {

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

	public static TypeM getTypeM(TypeElement typeElem) {
		TypeM result = new TypeM(typeElem.getQualifiedName().toString());
		if (typeElem.getTypeParameters().isEmpty() == false) {
			for (TypeParameterElement param : typeElem.getTypeParameters()) {
				TypeParameterM tpm = new TypeParameterM(param.getSimpleName()
						.toString());
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

	public static TypeM getTypeM(DeclaredType type) {
		TypeElement typeElem = (TypeElement) type.asElement();
		TypeM result = new TypeM(typeElem.getQualifiedName().toString());
		if (type.getTypeArguments().isEmpty() == false) {
			for (TypeMirror typeArg : type.getTypeArguments()) {
				if (typeArg.getKind() == TypeKind.DECLARED) {
					DeclaredType typeArgType = (DeclaredType) typeArg;
					TypeElement typeArgElem = (TypeElement) typeArgType
							.asElement();
					TypeParameterM tpm = new TypeParameterM(typeArgElem
							.getSimpleName().toString());
					result.getTypeParameters().add(tpm);
				} else if (typeArg.getKind() == TypeKind.TYPEVAR) {
					TypeVariable typeVar = (TypeVariable) typeArg;
					TypeParameterElement typeParamElem = (TypeParameterElement) typeVar
							.asElement();
					TypeParameterM tpm = new TypeParameterM(typeParamElem
							.getSimpleName().toString());
					result.getTypeParameters().add(tpm);
				}
			}
		}
		return result;
	}

}
