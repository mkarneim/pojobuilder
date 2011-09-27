package net.karneim.pojobuilder.model;

import net.karneim.pojobuilder.util.StringUtil;

public class TypeM {
	public static final TypeM BOOLEAN_TYPE = new TypeM("boolean", true);
	public static final TypeM CHAR_TYPE = new TypeM("char", true);
	public static final TypeM BYTE_TYPE = new TypeM("byte", true);
	public static final TypeM SHORT_TYPE = new TypeM("short", true);
	public static final TypeM INT_TYPE = new TypeM("int", true);
	public static final TypeM LONG_TYPE = new TypeM("long", true);
	public static final TypeM FLOAT_TYPE = new TypeM("float", true);
	public static final TypeM DOUBLE_TYPE = new TypeM("double", true);

	private static final TypeM[] primitives = new TypeM[] { BOOLEAN_TYPE,
			CHAR_TYPE, BYTE_TYPE, SHORT_TYPE, INT_TYPE, LONG_TYPE, FLOAT_TYPE,
			DOUBLE_TYPE };

	public static TypeM OBJECT = new TypeM("java.lang.Object", null);

	private final String name;
	private final boolean primitive;

	public TypeM(String classname) {
		this.name = classname;
		this.primitive = false;
	}

	public TypeM(String classname, TypeM superclass) {
		super();
		this.name = classname;
		this.primitive = false;
	}

	private TypeM(String classname, boolean isPrimitve) {
		super();
		this.name = classname;
		this.primitive = isPrimitve;
	}

	public String getName() {
		return name;
	}

	public String getImportName() {
		int idx = name.indexOf('[');
		if (idx > -1) {
			return name.substring(0, idx);
		} else {
			return name;
		}
	}

	public String getBasename() {
		return StringUtil.getBasename(name);
	}

	public String getPackage() {
		return StringUtil.getPackage(name);
	}

	public boolean isPrimitive() {
		return primitive;
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
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public static TypeM get(String typename) {
		for (TypeM p : primitives) {
			if (p.name.equals(typename)) {
				return p;
			}
		}
		if (OBJECT.name.equals(typename)) {
			return OBJECT;
		}
		return new TypeM(typename);
	}

	@Override
	public String toString() {
		return "TypeM [name=" + name + ", primitive=" + primitive + "]";
	}

}
