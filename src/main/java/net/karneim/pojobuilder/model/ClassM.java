package net.karneim.pojobuilder.model;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ClassM {
	private TypeM type;
	private TypeM superType;
	private boolean abstractClass;
	private Date created;
	private List<TypeM> additionalImports = new ArrayList<TypeM>();

	public ClassM() {
		super();
	}

	public void setType(TypeM type) {
		this.type = type;
	}

	public void setSuperType(TypeM superType) {
		this.superType = superType;
	}

	public void setAbstract(boolean abstractClass) {
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

	public boolean isAbstract() {
		return abstractClass;
	}

	public List<TypeM> getAdditionalImports() {
		return additionalImports;
	}

	protected void exportAdditionalImportTypes(Set<String> result) {
		for (TypeM type : additionalImports) {
			result.add(type.getQualifiedName());
		}
	}

	public final Collection<String> getImportTypes() {
		Set<String> result = new HashSet<String>();
		exportImportTypes(result);
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

	public void exportImportTypes(Set<String> result) {
		if (type.isGeneric()) {
			for (TypeParameterM typeParam : type.getTypeParameters()) {
				typeParam.exportImportTypes(result);
			}
		}

		if ( superType!=null ) {
            superType.exportImportTypes(result);
        }

		exportAdditionalImportTypes(result);
	}

	@Override
	public String toString() {
		return "ClassM[type=" + type + ",superType=" + superType + ",created=" + created + "]";
	}

}
