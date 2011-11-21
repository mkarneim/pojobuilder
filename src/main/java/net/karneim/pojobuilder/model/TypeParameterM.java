package net.karneim.pojobuilder.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TypeParameterM {
	private String name;

	private List<TypeM> bounds = new ArrayList<TypeM>();

	/**
	 * @param name
	 */
	public TypeParameterM(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
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

	}

}
