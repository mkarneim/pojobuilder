package net.karneim.pojobuilder.model;

public class TypeVariableM extends TypeM {
  private final TypeListM bounds = new TypeListM();

  public TypeVariableM(String name) {
    super(name);
  }

  @Override
  public boolean isTypeVariable() {
    return true;
  }

  public TypeVariableM whichExtends(TypeM... types) {
    for (TypeM bound : types) {
      bounds.add(bound);
    }
    return this;
  }

  public boolean hasBounds() {
    return this.bounds.isEmpty() == false;
  }

  public String getBoundsString() {
    StringBuilder result = new StringBuilder();
    for (TypeM bound : this.bounds) {
      if (result.length() > 0) {
        result.append(" & ");
      }
      result.append(bound.getGenericTypeDeclaration());
    }
    return result.toString();
  }

  public String getGenericType() {
    if (!hasBounds()) {
      return getName();
    }
    return String.format("%s extends %s", getName(), getBoundsString());
  }

  @Override
  public ImportTypesM addToImportTypes(ImportTypesM result) {
    if (result.add(this)) {
      bounds.addToImportTypes(result);
    }
    return result;
  }

  @Override
  public int hashCode() {
    // This hash code calculation is fast and good enough...
    int result = super.hashCode();
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!super.equals(obj)) return false;
    if (getClass() != obj.getClass()) return false;
    TypeVariableM other = (TypeVariableM) obj;
    if (bounds == null) {
      if (other.bounds != null) return false;
    } else if (!bounds.equals(other.bounds)) return false;
    return true;
  }

  @Override
  public String toString() {
    return "TypeVariableM [getGenericType()=" + getGenericType() + "]";
  }

}
