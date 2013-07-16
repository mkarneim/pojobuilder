package net.karneim.pojobuilder.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TypeM {
	public static final TypeM BOOLEAN_TYPE = new TypeM("boolean", true);
	public static final TypeM CHAR_TYPE = new TypeM("char", true);
	public static final TypeM BYTE_TYPE = new TypeM("byte", true);
	public static final TypeM SHORT_TYPE = new TypeM("short", true);
	public static final TypeM INT_TYPE = new TypeM("int", true);
	public static final TypeM LONG_TYPE = new TypeM("long", true);
	public static final TypeM FLOAT_TYPE = new TypeM("float", true);
	public static final TypeM DOUBLE_TYPE = new TypeM("double", true);
	public static final TypeM VOID_TYPE = new TypeM("void", true);

	private static final TypeM[] primitives = new TypeM[] { BOOLEAN_TYPE, CHAR_TYPE, BYTE_TYPE, SHORT_TYPE, INT_TYPE,
			LONG_TYPE, FLOAT_TYPE, DOUBLE_TYPE, VOID_TYPE };

	private final String qualifiedName;
	private final boolean primitive;
	private final List<TypeParameterM> typeParameters = new ArrayList<TypeParameterM>();

	public TypeM(String aQualifiedName) {
		super();
		this.qualifiedName = aQualifiedName;
		this.primitive = false;
	}

	private TypeM(String aClassname, boolean isPrimitve) {
		super();
		this.qualifiedName = aClassname;
		this.primitive = isPrimitve;
	}

	public List<TypeParameterM> getTypeParameters() {
		return typeParameters;
	}

	public String getQualifiedName() {
		return qualifiedName;
	}

	public String getSimpleName() {
		return extractSimpleName(qualifiedName);
	}

	public String getGenericTypeSimpleName() {
		String result = getSimpleName();
		if (isGeneric()) {
			StringBuilder b = new StringBuilder();

			for (TypeParameterM param : getTypeParameters()) {
				if (b.length() > 0) {
					b.append(", ");
				}
				b.append(param.getName());
			}

			result += "<" + b.toString() + ">";
		}
		return result;
	}

	public String getGenericTypeSimpleNameWithBounds() {
		String result = getSimpleName();
		if (isGeneric()) {
			StringBuilder b = new StringBuilder();

			for (TypeParameterM param : getTypeParameters()) {
				if (b.length() > 0) {
					b.append(", ");
				}
				b.append(param.getName());
				if (param.isBounded()) {
					b.append(" extends ");
					b.append(param.getBoundsAsString());
				}
			}

			result += "<" + b.toString() + ">";
		}
		return result;
	}

	public boolean isGeneric() {
		return getTypeParameters().isEmpty() == false;
	}

	public String getPackage() {
		return extractPackage(qualifiedName);
	}

	public boolean isPrimitive() {
		return primitive;
	}

	public void addToImportTypes(Set<String> result) {
		String importName = getImportName();
		if (importName != null) {
			result.add(importName);
		}
		if (isGeneric()) {
			for (TypeParameterM typeParam : getTypeParameters()) {
				typeParam.addToImportTypes(result);
			}
		}
	}

	private String getImportName() {
		if (extractPackage(qualifiedName) == null) {
			return null;
		}
		int idx = qualifiedName.indexOf('[');
		if (idx > -1) {
			return qualifiedName.substring(0, idx);
		} else {
			return qualifiedName;
		}
	}

	private static String extractPackage(String qualifiedName) {
		if (qualifiedName == null) {
			return null;
		}
		int idx = qualifiedName.lastIndexOf('.');
		if (idx == -1) {
			return null;
		} else {
			return qualifiedName.substring(0, idx);
		}
	}

	private static String extractSimpleName(String qualifiedName) {
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

	public String getDefaultValue() {
		if (this.equals(BOOLEAN_TYPE)) {
			return "false";
		} else if (this.equals(CHAR_TYPE)) {
			return "0";
		} else if (this.equals(BYTE_TYPE)) {
			return "0";
		} else if (this.equals(SHORT_TYPE)) {
			return "0";
		} else if (this.equals(INT_TYPE)) {
			return "0";
		} else if (this.equals(LONG_TYPE)) {
			return "0";
		} else if (this.equals(FLOAT_TYPE)) {
			return "0";
		} else if (this.equals(DOUBLE_TYPE)) {
			return "0";
		} else {
			return "null";
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((qualifiedName == null) ? 0 : qualifiedName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TypeM other = (TypeM) obj;
		if (qualifiedName == null) {
			if (other.qualifiedName != null)
				return false;
		} else if (!qualifiedName.equals(other.qualifiedName))
			return false;
		return true;
	}

	public static TypeM get(String typename) {
		for (TypeM p : primitives) {
			if (p.qualifiedName.equals(typename)) {
				return p;
			}
		}
		return new TypeM(typename);
	}

	@Override
	public String toString() {
		return "TypeM [name=" + qualifiedName + ", primitive=" + primitive + "]";
	}

}
