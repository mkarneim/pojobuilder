package net.karneim.pojobuilder.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import net.karneim.pojobuilder.analysis.PropertyPattern;
import net.karneim.pojobuilder.model.WriteAccess.Type;

public class PropertyListM implements Iterable<PropertyM> {
  private LinkedHashMap<Key, PropertyM> elements = new LinkedHashMap<Key, PropertyM>();

  public PropertyListM(Iterable<PropertyM> other) {
    for (PropertyM t : other) {
      add(t);
    }
  }

  public PropertyListM(PropertyM... elems) {
    this(Arrays.asList(elems));
  }

  @Override
  public Iterator<PropertyM> iterator() {
    return elements.values().iterator();
  }

  public PropertyM get(String propertyName, TypeM propertyType) {
    return get(new Key(propertyName, propertyType));
  }

  public PropertyM get(Key key) {
    return elements.get(key);
  }

  public PropertyM getOrCreate(String propertyName, TypeM propertyType) {
    PropertyM elem = elements.get(new Key(propertyName, propertyType));
    if (elem == null) {
      elem = new PropertyM(propertyName, propertyType);
      add(elem);
    }
    return elem;
  }

  public PropertyListM add(PropertyM prop) {
    Key key = keyOf(prop);
    if (elements.containsKey(key)) {
      throw new IllegalArgumentException(String.format("Property with key %s already in list!", key));
    }
    elements.put(key, prop);
    return this;
  }

  private Key keyOf(PropertyM prop) {
    Key result = new Key(prop.getPropertyName(), prop.getPropertyType());
    return result;
  }

  public PropertyListM filterOutNonWritableProperties(TypeM accessingClass) {
    PropertyListM result = new PropertyListM();
    Iterator<PropertyM> it = this.iterator();
    while (it.hasNext()) {
      PropertyM p = it.next();
      if (p.isWritableBy(accessingClass) == false) {
        result.add(p);
        it.remove();
      }
    }
    return result;
  }

  public PropertyListM filterOutPropertiesWritableBy(Type type, TypeM accessingClass) {
    PropertyListM result = new PropertyListM();
    Iterator<PropertyM> it = this.iterator();
    while (it.hasNext()) {
      PropertyM p = it.next();
      if (p.getPreferredWriteAccessFor(accessingClass).getType() == type) {
        result.add(p);
        it.remove();
      }
    }
    return result;
  }

  public ArgumentListM toArgumentList(Type type, TypeM accessingClass) {
    ArgumentListM result = new ArgumentListM();
    for (PropertyM p : this) {
      WriteAccess writeAccess = p.getPreferredWriteAccessFor(accessingClass);
      if (writeAccess.getType() == type) {
        Positional positional = (Positional) writeAccess;
        int pos = positional.getPos();
        result.add(new ArgumentM(p, pos));
      }
    }
    return result;
  }

  public ArgumentListM filterOutPropertiesWritableViaConstructorParameter(TypeM accessingClass) {
    ArgumentListM result = new ArgumentListM();
    Iterator<PropertyM> it = this.iterator();
    while (it.hasNext()) {
      PropertyM p = it.next();
      if (p.getPreferredWriteAccessFor(accessingClass).getType() == Type.CONSTRUCTOR) {
        int pos = p.getConstructorParameter().getPos();
        result.add(new ArgumentM(p, pos));
        it.remove();
      }
    }
    return result;
  }

  public ArgumentListM filterOutPropertiesWritableViaFactoryMethodParameter(TypeM accessingClass) {
    ArgumentListM result = new ArgumentListM();
    Iterator<PropertyM> it = this.iterator();
    while (it.hasNext()) {
      PropertyM p = it.next();
      if (p.getPreferredWriteAccessFor(accessingClass).getType() == Type.FACTORY) {
        int pos = p.getFactoryMethodParameter().getPos();
        result.add(new ArgumentM(p, pos));
        it.remove();
      }
    }
    return result;
  }

  public PropertyListM filterOutPropertiesReadableViaGetterCall(TypeM accessingClass) {
    PropertyListM result = new PropertyListM();
    Iterator<PropertyM> it = this.iterator();
    while (it.hasNext()) {
      PropertyM p = it.next();
      if (p.isReadableViaGetterMethodBy(accessingClass)) {
        result.add(p);
        it.remove();
      }
    }
    return result;
  }

  public PropertyListM filterOutPropertiesReadableViaFieldAccess(TypeM accessingClass) {
    PropertyListM result = new PropertyListM();
    Iterator<PropertyM> it = this.iterator();
    while (it.hasNext()) {
      PropertyM p = it.next();
      if (p.isReadableViaFieldAccessBy(accessingClass)) {
        result.add(p);
        it.remove();
      }
    }
    return result;
  }

  public PropertyListM filterOutPropertiesReadableBy(TypeM accessingClass) {
    PropertyListM result = new PropertyListM();
    Iterator<PropertyM> it = this.iterator();
    while (it.hasNext()) {
      PropertyM p = it.next();
      if (p.isReadableViaFieldAccessBy(accessingClass) || p.isReadableViaGetterMethodBy(accessingClass)) {
        result.add(p);
        it.remove();
      }
    }
    return result;
  }

  public void retainPropertiesMatchingAnyOf(List<PropertyPattern> list) {
    Iterator<PropertyM> it = this.iterator();
    while (it.hasNext()) {
      PropertyM p = it.next();
      if (!p.matchesAnyOf(list)) {
        // We won't exclude mandatory properties
        if (!p.isWritableViaConstructor() && !p.isWritableViaFactoryMethod()) {
          it.remove();
        }
      }
    }
  }

  public void removePropertiesMatchingAnyOf(List<PropertyPattern> list) {
    if (list.isEmpty()) {
      return;
    }
    Iterator<PropertyM> it = this.iterator();
    while (it.hasNext()) {
      PropertyM p = it.next();
      if (p.matchesAnyOf(list)) {
        // We won't exclude mandatory properties
        if (!p.isWritableViaConstructor() && !p.isWritableViaFactoryMethod()) {
          it.remove();
        }
      }
    }
  }

  public boolean hasPropertiesReadablyBy(TypeM accessingClass) {
    return new PropertyListM(this).filterOutPropertiesReadableBy(accessingClass).isEmpty() == false;
  }

  public boolean isEmpty() {
    return elements.isEmpty();
  }

  public TypeListM getTypes() {
    TypeListM result = new TypeListM();
    for (PropertyM p : this) {
      result.add(p.getPropertyType());
    }
    return result;
  }

  @Override
  public String toString() {
    return "PropertyListM [elements=" + elements + "]";
  }

  public static class Key {
    String propertyName;
    String propertyType;

    public Key(String propertyName, TypeM propertyType) {
      this(propertyName, propertyType.getGenericType());
    }

    public Key(String propertyName, String propertyType) {
      this.propertyName = propertyName;
      this.propertyType = propertyType;
    }

    @Override
    public String toString() {
      return "Key [propertyName=" + propertyName + ", propertyType=" + propertyType + "]";
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((propertyName == null) ? 0 : propertyName.hashCode());
      result = prime * result + ((propertyType == null) ? 0 : propertyType.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      Key other = (Key) obj;
      if (propertyName == null) {
        if (other.propertyName != null) return false;
      } else if (!propertyName.equals(other.propertyName)) return false;
      if (propertyType == null) {
        if (other.propertyType != null) return false;
      } else if (!propertyType.equals(other.propertyType)) return false;
      return true;
    }
  }



}
