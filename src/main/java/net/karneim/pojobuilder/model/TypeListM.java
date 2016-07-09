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

  /**
   * Returns a String representation of this type list including bounds.
   *
   * @return a String representation including bounds
   * @see TypeM#getGenericTypeDefinition()
   */
  public String toParameterString() {
    StringBuilder result = new StringBuilder();
    for (TypeM type : this) {
      if (result.length() > 0) {
        result.append(", ");
      }
      result.append(type.getGenericTypeDefinition());
    }
    return result.toString();
  }

  /**
   * Returns a String representation of this type list without bounds.
   *
   * @return a String representation without bounds
   * @see TypeM#getGenericType()
   */
  public String toArgumentString() {
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
