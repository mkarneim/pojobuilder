package net.karneim.pojobuilder;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;

import net.karneim.pojobuilder.model.TypeM;
import net.karneim.pojobuilder.model.TypeParameterM;

public class TypeMUtils {

	public List<TypeParameterM> getTypeParameters(TypeElement typeElement) {
		List<TypeParameterM> result = new ArrayList<TypeParameterM>();
		List<? extends TypeParameterElement> typeParams = typeElement.getTypeParameters();
		for (TypeParameterElement typeParam : typeParams) {
			TypeM paramTypeM = getTypeM(typeParam.asType());
			TypeParameterM tpm = new TypeParameterM(paramTypeM);
			List<? extends TypeMirror> bounds = typeParam.getBounds();
			for (TypeMirror bound : bounds) {
				TypeM boundTypeM = TypeM.get(bound.toString());
				tpm.getBounds().add(boundTypeM);
			}
			result.add(tpm);
		}
		return result;
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
			DeclaredType dt = (DeclaredType) typeMirror;
			return getTypeM(dt);
		case VOID:
			return TypeM.VOID_TYPE;
		case ARRAY:
			return TypeM.get(typeMirror.toString());
		case TYPEVAR:
			if (typeMirror instanceof TypeVariable) {
				TypeVariable typeVar = (TypeVariable) typeMirror;
				TypeM result = TypeM.get(typeMirror.toString()); // Ugly, since
																	// this
																	// can't see
																	// the
																	// actual
																	// type-var's
																	// value
				// TODO: ??
				TypeMirror upperBound = typeVar.getUpperBound();
				TypeMirror lowerBound = typeVar.getUpperBound();
				return result;
			} else {
				throw new IllegalStateException(String.format("Expected TypeVariable for %s", typeMirror));
			}
		default:
			throw new UnsupportedOperationException(String.format("Unexpected kind %s for typeMirror %s", kind,
					typeMirror.getClass().getName()));
		}
	}
	
	public TypeM getTypeM(TypeElement typeElem) {
		TypeM result = new TypeM(typeElem.getQualifiedName().toString());
		if (typeElem.getTypeParameters().isEmpty() == false) {
			for (TypeParameterElement param : typeElem.getTypeParameters()) {
				// TypeParameterM tpm = new
				// TypeParameterM(param.getSimpleName().toString());
				TypeM paramTypeM = getTypeM(param.asType()); // PATCHED
																// 19.9.2012, mk
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
		TypeElement typeElem = (TypeElement) type.asElement();
		TypeM result = new TypeM(typeElem.getQualifiedName().toString());
		if (type.getTypeArguments().isEmpty() == false) {
			for (TypeMirror typeArg : type.getTypeArguments()) {
				if (typeArg.getKind() == TypeKind.DECLARED) {
					TypeM typeArgElemTypeM = getTypeM(typeArg);
					TypeParameterM tpm = new TypeParameterM(typeArgElemTypeM);
					result.getTypeParameters().add(tpm);
				} else if (typeArg.getKind() == TypeKind.TYPEVAR) {
					TypeVariable typeVar = (TypeVariable) typeArg;
					TypeParameterElement typeParamElem = (TypeParameterElement) typeVar.asElement();
					TypeM typeParamElemTypeM = getTypeM(typeParamElem.asType());
					TypeParameterM tpm = new TypeParameterM(typeParamElemTypeM);
					result.getTypeParameters().add(tpm);
				}
			}
		}
		return result;
	}

	
}
