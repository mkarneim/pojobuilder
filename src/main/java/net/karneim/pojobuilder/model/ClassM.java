package net.karneim.pojobuilder.model;

import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ClassM {
	private TypeM type;
	private TypeM superType;
	private boolean abstractClass;
	private Date created;

	public ClassM(TypeM aType, TypeM aSuperType, boolean abstractClass) {
		this.type = aType;
		this.superType = aSuperType;
		this.abstractClass = abstractClass;
	}
	
	public ClassM() {
		super();
	}
	
	public void setType(TypeM type) {
		this.type = type;
	}

	public void setSuperType(TypeM superType) {
		this.superType = superType;
	}

	public void setAbstractClass(boolean abstractClass) {
		this.abstractClass = abstractClass;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getCreated() {
		return created;
	}

	public String getCreatedString() {
		return DateFormat.getDateInstance().format(created);
	}

	public TypeM getType() {
		return type;
	}

	public TypeM getSuperType() {
		return superType;
	}

	public boolean isAbstractClass() {
		return abstractClass;
	}

	public final Collection<String> getImportTypes() {
		Set<String> result = new HashSet<String>();
		addToImportTypes(result);
		removeDefaultImports(result);
		return result;
	}

	private void removeDefaultImports(Set<String> typeNames) {
		Iterator<String> it = typeNames.iterator();
		while (it.hasNext()) {
			String typeName = it.next();
			if (isDefaultImport(typeName)) {
				it.remove();
			}
		}
	}

	private boolean isDefaultImport(String typeName) {
		return typeName.startsWith("java.lang.");
	}

	public void addToImportTypes(Set<String> result) {
		superType.addToImportTypes(result);
	}

	@Override
	public String toString() {
		return "ClassM [type=" + type + ", superType=" + superType
				+ ", created=" + created + "]";
	}

}
