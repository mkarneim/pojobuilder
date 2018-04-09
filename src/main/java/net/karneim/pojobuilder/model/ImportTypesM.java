package net.karneim.pojobuilder.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ImportTypesM {
  private final Set<TypeM> types = new HashSet<>();
  private final Set<String> simpleNames = new HashSet<>();

  public void add(Class<?> aClass) {
    add(new TypeM(aClass));
  }

  public boolean add(TypeM e) {
    if (simpleNames.contains(e.getSimpleName())) {
      return false;
    }
    simpleNames.add(e.getSimpleName());
    return types.add(e);
  }

  public boolean canAdd(TypeM typeM) {
    return !types.contains(typeM) && !simpleNames.contains(typeM.getSimpleName());
  }

  public List<String> getSortedDistinctClassnames() {
    Set<String> resultSet = new HashSet<String>();
    for (TypeM t : this.types) {
      if (!t.isTypeVariable() && !t.isPrimitive()) {
        resultSet.add(t.getName());
      }
    }
    List<String> result = new ArrayList<String>(resultSet);
    Collections.sort(result);
    return result;
  }

  public void removePackage(String packageName) {
    Iterator<TypeM> it = this.types.iterator();
    while (it.hasNext()) {
      TypeM t = it.next();
      if (t.isInPackage(packageName)) {
        it.remove();
      }
    }
  }



}
