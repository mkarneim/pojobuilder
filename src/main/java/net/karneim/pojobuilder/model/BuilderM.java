package net.karneim.pojobuilder.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BuilderM extends BaseBuilderM {
	private TypeM selfType;
	private FactoryM factory;
	private final Map<String, PropertyM> properties = new HashMap<String, PropertyM>();
	private boolean isImplementingCopyMethod;

	public void setSelfType(TypeM selfType) {
		this.selfType = selfType;
	}

	public FactoryM getFactory() {
		return factory;
	}

	public void setFactory(FactoryM factory) {
		this.factory = factory;
	}

	public boolean isUsingFactory() {
		return factory != null;
	}

	public void setIsImplementingCopyMethod(boolean isImplementingCopyMethod) {
		this.isImplementingCopyMethod = isImplementingCopyMethod;
	}

	public boolean isImplementingCopyMethod() {
		return isImplementingCopyMethod;
	}

	public List<PropertyM> getProperties() {
		return new ArrayList<PropertyM>(properties.values());
	}

	public TypeM getSelfType() {
		return selfType;
	}

	public PropertyM getOrCreateProperty(String propertyName, TypeM propertyType) {
		String fieldname = computeBuilderFieldname(propertyName, propertyType.getQualifiedName());
		PropertyM result = properties.get(fieldname);
		if (result == null) {
			result = new PropertyM(propertyName, fieldname, propertyType);
			properties.put(fieldname, result);
		}
		return result;
	}

	public PropertyM getProperty(String propertyName, TypeM propertyType) {
		String fieldname = computeBuilderFieldname(propertyName, propertyType.getQualifiedName());
		PropertyM result = properties.get(fieldname);
		return result;
	}

	private static String computeBuilderFieldname(String propertyName, String propertyType) {
		String typeString = propertyType.replaceAll("\\.", "\\$");
		typeString = typeString.replaceAll("\\[\\]", "\\$");
		return propertyName + "$" + typeString;
	}

	@Override
	public void addToImportTypes(Set<String> result) {
		super.addToImportTypes(result);
		for (PropertyM prop : properties.values()) {
			prop.getType().addToImportTypes(result);
		}
		if (factory != null) {
			factory.addToImportTypes(result);
		}
	}

	public Collection<PropertyM> getPropertiesForConstructor() {
		List<PropertyM> result = getProperties();
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
		List<PropertyM> result = getProperties();
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
		List<PropertyM> result = getProperties();
		// Remove properties that have a parameter position and have setters
		Iterator<PropertyM> it = result.iterator();
		while (it.hasNext()) {
			PropertyM p = it.next();
			if (!p.isAccessible() || !p.isWritable()) {
				it.remove();
			} else if (p.getParameterPos() != null || p.isHasSetter()) {
				it.remove();
			}
		}
		return result;
	}

	public Collection<PropertyM> getPropertiesForWith() {
		List<PropertyM> result = getProperties();
		// Remove properties that are not writable, have no setters, and can't
		// be set by constructor
		Iterator<PropertyM> it = result.iterator();
		while (it.hasNext()) {
			PropertyM p = it.next();
			if (!p.isWritable() && !p.isHasSetter() && !p.isConstructorParameter()) {
				it.remove();
			}
		}
		return result;
	}

	public Collection<PropertyM> getPropertiesForCopy() {
		Collection<PropertyM> result = getPropertiesForWith();
		// Remove properties that are not readable or have no getters
		Iterator<PropertyM> it = result.iterator();
		while (it.hasNext()) {
			PropertyM p = it.next();

			if (!p.isReadable() && !p.isHasGetter()) {
				it.remove();
			}
		}
		return result;
	}

	public Collection<PropertyM> getPropertiesForCopyByGetter() {
		Collection<PropertyM> result = getPropertiesForCopy();
		// Remove properties that have no getters
		Iterator<PropertyM> it = result.iterator();
		while (it.hasNext()) {
			PropertyM p = it.next();
			if (!p.isHasGetter()) {
				it.remove();
			}
		}
		return result;
	}

	public Collection<PropertyM> getPropertiesForCopyByFieldAccess() {
		Collection<PropertyM> result = getPropertiesForCopy();
		// Remove properties that are not readable
		Iterator<PropertyM> it = result.iterator();
		while (it.hasNext()) {
			PropertyM p = it.next();
			if (p.isHasGetter()) {
				it.remove();
			} else if (!p.isReadable()) {
				it.remove();
			}
		}
		return result;
	}

}
