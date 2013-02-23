package net.karneim.pojobuilder.model;

public class PropertyM {

	private final String name;
	private final String fieldname;
	private final TypeM type;
	private String setter;
	private String getter;
	private boolean accessible;
	private Integer parameterPos;
	private boolean readable;
	private boolean writable;

	public PropertyM(String name, String fieldname, TypeM type) {
		super();
		this.name = name;
		this.fieldname = fieldname;
		this.type = type;
	}

	public boolean isAccessible() {
		return accessible;
	}

	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}

	public boolean isReadable() {
		return readable;
	}

	public void setReadable(boolean readable) {
		this.readable = readable;
	}

	public boolean isWritable() {
		return writable;
	}

	public void setWritable(boolean writable) {
		this.writable = writable;
	}

	public Integer getParameterPos() {
		return parameterPos;
	}

	public void setParameterPos(Integer parameterPos) {
		this.parameterPos = parameterPos;
	}

	public String getSetter() {
		return setter;
	}

	public void setSetter(String setter) {
		this.setter = setter;
	}

	public boolean isHasSetter() {
		return setter != null;
	}

	public String getGetter() {
		return getter;
	}

	public boolean isHasGetter() {
		return getter != null;
	}

	public void setGetter(String getter) {
		this.getter = getter;
	}

	public String getName() {
		return name;
	}

	public String getFieldname() {
		return fieldname;
	}

	public String getNameUC() {
		if (name.length() <= 1) {
			return name.toUpperCase();
		} else {
			return name.toUpperCase().substring(0, 1) + name.substring(1);
		}
	}

	public TypeM getType() {
		return type;
	}

	public boolean isConstructorParameter() {
		return getParameterPos() != null;
	}

	@Override
	public String toString() {
		return "PropertyM [name=" + name + ", fieldname=" + fieldname + ", type=" + type + ", setter=" + setter
				+ ", getter=" + getter + ", accessible=" + accessible + ", parameterPos=" + parameterPos
				+ ", readable=" + readable + ", writable=" + writable + "]";
	}

}
