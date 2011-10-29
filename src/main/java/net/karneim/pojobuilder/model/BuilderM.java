package net.karneim.pojobuilder.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BuilderM extends ClassM {
	private final TypeM productType;
	private List<PropertyM> properties = new ArrayList<PropertyM>();

	public BuilderM(TypeM aType, TypeM aSuperType, TypeM aProductType) {
		super(aType, aSuperType);
		this.productType = aProductType;
	}

	public Collection<PropertyM> getProperties() {
		return properties;
	}

	public TypeM getProductType() {
		return productType;
	}

	public void setProperties(Collection<PropertyM> properties) {
		this.properties = new ArrayList<PropertyM>(properties);
	}

	public void addToImportTypes(Set<String> result) {
		super.addToImportTypes(result);
		for (PropertyM prop : properties) {
			prop.getType().addToImportTypes(result);
		}
		productType.addToImportTypes(result);
	}

	public Collection<PropertyM> getPropertiesForConstructor() {
		List<PropertyM> result = new ArrayList<PropertyM>(properties);
		// Remove properties that have no parameter position
		Iterator<PropertyM> it = result.iterator();
		while (it.hasNext()) {
			if (it.next().getParameterPos() == null) {
				it.remove();
			}
		}
		// sort result by parameter pos
		Collections.sort(result, new Comparator<PropertyM>() {

			@Override
			public int compare(PropertyM o1, PropertyM o2) {
				return o1.getParameterPos().compareTo(o2.getParameterPos());
			}

		});
		return result;
	}

	public Collection<PropertyM> getPropertiesForSetters() {
		List<PropertyM> result = new ArrayList<PropertyM>(properties);
		// Remove properties that have a parameter position
		Iterator<PropertyM> it = result.iterator();
		while (it.hasNext()) {
			PropertyM p = it.next();
			if (p.getParameterPos() != null || p.isHasSetter() == false) {
				it.remove();
			}
		}
		return result;
	}

	public Collection<PropertyM> getPropertiesForAssignment() {
		List<PropertyM> result = new ArrayList<PropertyM>(properties);
		// Remove properties that have a parameter position and have setters
		Iterator<PropertyM> it = result.iterator();
		while (it.hasNext()) {
			PropertyM p = it.next();
			if (p.getParameterPos() != null || p.isHasSetter()
					|| p.isAccessible() == false) {
				it.remove();
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return "BuilderM [productType=" + productType + ", properties="
				+ properties + ", getType()=" + getType() + ", getSuperType()="
				+ getSuperType() + "]";
	}

}
