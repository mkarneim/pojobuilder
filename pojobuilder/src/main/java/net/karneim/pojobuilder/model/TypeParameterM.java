package net.karneim.pojobuilder.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TypeParameterM {
	private TypeM type;

	private List<TypeM> bounds = new ArrayList<TypeM>();

	/**
	 * @param type
	 */
	public TypeParameterM(TypeM type) {
		super();
		this.type = type;
	}

	public TypeM getType() {
		return type;
	}

	public String getName() {
		// TODO it may be better to remove this method an let the caller use
		// getType().getGenericTypeSimpleName() or any other methode instead
		return type.getGenericTypeSimpleName();
	}

	public boolean isBounded() {
		return bounds.isEmpty() == false;
	}

	public List<TypeM> getBounds() {
		return bounds;
	}

	public String getBoundsAsString() {
		StringBuilder b = new StringBuilder();
		for (TypeM bound : bounds) {
			if (b.length() > 0) {
				b.append(" & ");
			}
			b.append(bound.getGenericTypeSimpleName());
		}
		return b.toString();
	}

	public void addToImportTypes(Set<String> result) {
		if (isBounded()) {
			for (TypeM bound : bounds) {
				bound.addToImportTypes(result);
			}
		}
		type.addToImportTypes(result);

	}

	@Override
	public String toString() {
		return "TypeParameterM [type=" + type + ", bounds=" + bounds + "]";
	}

}
