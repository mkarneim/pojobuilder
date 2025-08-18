package net.karneim.pojobuilder.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
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

  public PropertyListM getSortedByName() {
    ArrayList<Key> keySet = new ArrayList<Key>(elements.keySet());
    keySet.sort(new Comparator<Key>() {
      @Override
      public int compare(Key o1, Key o2) {
        int result = o1.propertyName.compareTo(o2.propertyName);
        if (result == 0) {
          result = o1.propertyType.compareTo(o2.propertyType);
        }
        return result;
      }
    });
    PropertyListM result = new PropertyListM();
    for (Key key : keySet) {
      PropertyM p = get(key);
      result.add(p);
    }
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
      propertyName = propertyName;
      propertyType = propertyType;
    }

    @Override
    public String toString() {
      return "Key [propertyName=" + propertyName + ", propertyType=" + propertyType + "]";
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + (propertyName == null ? 0 : propertyName.hashCode());
      result = prime * result + (propertyType == null ? 0 : propertyType.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      Key other = (Key) obj;
      if (!Objects.equals(propertyName, other.propertyName)) {
        return false;
      }
      if (!Objects.equals(propertyType, other.propertyType)) {
        return false;
      }
      return true;
    }
  }



}
