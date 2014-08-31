package net.karneim.pojobuilder.model;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("serial")
public class TypeListM extends ArrayList<TypeM> {

  public TypeListM(TypeM... types) {
    this(Arrays.asList(types));
  }

  public TypeListM(Iterable<TypeM> types) {
    for (TypeM type : types) {
      add(type);
    }
  }

  public String toArgumentString() {
    // Without Bounds
    StringBuilder result = new StringBuilder();
    for (TypeM type : this) {
      if (result.length() > 0) {
        result.append(", ");
      }
      result.append(type.getGenericTypeDeclaration());
    }
    return result.toString();
  }

  public String toParameterString() {
    // With Bounds
    StringBuilder result = new StringBuilder();
    for (TypeM type : this) {
      if (result.length() > 0) {
        result.append(", ");
      }
      result.append(type.getGenericType());
    }
    return result.toString();
  }

  public void add(Class<?> aClass) {
    add(new TypeM(aClass));
  }

  public ImportTypesM addToImportTypes(ImportTypesM result) {
    for (TypeM t : this) {
      t.addToImportTypes(result);
    }
    return result;
  }

  public TypeM[] asArray() {
    return this.toArray(new TypeM[size()]);
  }

  public TypeListM collectDistinctTypeVariablesRecursevly(TypeListM result) {
    for (TypeM type : this) {
      if (type.isTypeVariable()) {
        if (!result.contains(type)) {
          result.add(type);
        }
      } else {
        type.getTypeParameters().collectDistinctTypeVariablesRecursevly(result);
      }
    }
    return result;
  }
}
