package net.karneim.pojobuilder.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@SuppressWarnings("serial")
public class ImportTypesM extends HashSet<TypeM> {

  public void add(Class<?> aClass) {
    add(new TypeM(aClass));
  }

  public List<String> getSortedDistinctClassnames() {
    Set<String> resultSet = new HashSet<String>();
    for (TypeM t : this) {
      if (!t.isTypeVariable() && !t.isPrimitive()) {
        resultSet.add(t.getName());
      }
    }
    List<String> result = new ArrayList<String>(resultSet);
    Collections.sort(result);
    return result;
  }

  public void removePackage(String packageName) {
    Iterator<TypeM> it = this.iterator();
    while (it.hasNext()) {
      TypeM t = it.next();
      if (t.isInPackage(packageName)) {
        it.remove();
      }
    }
  }

}
