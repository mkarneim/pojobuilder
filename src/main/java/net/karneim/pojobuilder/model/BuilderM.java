package net.karneim.pojobuilder.model;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BuilderM {
	private final TypeM type;
	private final TypeM productType;
	private Date created;

	private List<PropertyM> properties = new ArrayList<PropertyM>();

	public BuilderM(TypeM type, TypeM productType) {
		this.type = type;
		this.productType = productType;
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

	public Collection<PropertyM> getProperties() {
		return properties;
	}

	public void setProperties(Collection<PropertyM> properties) {
		this.properties = new ArrayList<PropertyM>(properties);
	}

	public TypeM getType() {
		return type;
	}

	public TypeM getProductType() {
		return productType;
	}

	public Collection<String> getPropertyImports() {
		Set<String> result = new HashSet<String>();
		for (PropertyM prop : properties) {
			if (!prop.getType().isPrimitive()) {
				result.add(prop.getType().getImportName());
			}
		}
		return result;
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
		return "BuilderM [type=" + type + ", productType=" + productType
				+ ", created=" + created + ", properties=" + properties + "]";
	}

}
