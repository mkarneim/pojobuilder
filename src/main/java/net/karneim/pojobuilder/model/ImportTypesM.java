package net.karneim.pojobuilder.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class ImportTypesM {
  private final Set<TypeM> types = new HashSet<>();
  private final SortedSet<String> classnames = new TreeSet<>();
  private final Set<String> simpleNames = new HashSet<>();

  public void add(Class<?> aClass) {
    add(new TypeM(aClass));
  }

  public boolean add(TypeM type) {
    if (!simpleNames.contains(type.getSimpleName())) {
      types.add(type);
      if (!type.isTypeVariable() && !type.isPrimitive()) {
        classnames.add(type.getName());
      }
      simpleNames.add(type.getSimpleName());
      return true;
    } else {
      return false;
    }
  }

  public void remove(TypeM type) {
    types.remove(type);
    classnames.remove(type.getName());
    simpleNames.remove(type.getSimpleName());
  }

  public SortedSet<String> getSortedDistinctClassnames() {
    return classnames;
  }

  public void removePackage(String packageName) {
    for (TypeM type : new ArrayList<>(types)) {
      if (type.isInPackage(packageName)) {
        remove(type);
      }
    }
  }

  public String getCompressedTypeName(TypeM type) {
    add(type);
    if (classnames.contains(type.getName())) {
      return type.getSimpleName();
    } else {
      return type.getName();
    }
  }

}
