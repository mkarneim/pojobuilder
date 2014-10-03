package net.karneim.pojobuilder.model;


public class TypeM {
  private final String packageName;
  private final String simpleName;
  private final String name;
  private final TypeListM typeParameters = new TypeListM();

  protected TypeM(String name) {
    this("", name);
  }

  public TypeM(String packageName, String simpleName) {
    if (packageName == null) {
      this.packageName = "";
    } else {
      this.packageName = packageName;
    }
    this.simpleName = simpleName;
    if (this.packageName.isEmpty()) {
      this.name = simpleName;
    } else {
      this.name = this.packageName + "." + this.simpleName;
    }
  }

  public TypeM(Class<?> aClass) {
    this(aClass.getPackage().getName(), aClass.getSimpleName());
  }

  public TypeM withTypeParameter(TypeM... params) {
    for (TypeM p : params) {
      typeParameters.add(p);
    }
    return this;
  }

  public TypeListM getTypeParameters() {
    return typeParameters;
  }

  public String getSimpleName() {
    return simpleName;
  }

  public String getName() {
    return name;
  }

  public boolean isPrimitive() {
    return false;
  }

  public boolean isTypeVariable() {
    return false;
  }

  public boolean isArrayType() {
    return false;
  }

  public String getPackageName() {
    return packageName;
  }

  public boolean isGeneric() {
    return typeParameters.isEmpty() == false;
  }

  public String getGenericType() {
    // With Bounds
    if (!isGeneric()) {
      return name;
    }
    return String.format("%s<%s>", name, typeParameters.toParameterString());
  }

  public String getGenericTypeDeclaration() {
    // Without Bounds
    if (!isGeneric()) {
      return name;
    }
    return String.format("%s<%s>", name, typeParameters.toArgumentString());
  }

  public ImportTypesM addToImportTypes(ImportTypesM result) {
    if (result.add(this)) {
      typeParameters.addToImportTypes(result);
    }
    return result;
  }

  public boolean isInPackage(String packageName) {
    return (packageName == null && this.packageName.isEmpty()) || packageName.equals(this.packageName);
  }

  @Override
  public String toString() {
    return "TypeM [getGenericType()=" + getGenericType() + "]";
  }

  @Override
  public int hashCode() {
    // This hash code calculation is fast and good enough...
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    TypeM other = (TypeM) obj;
    if (name == null) {
      if (other.name != null) return false;
    } else if (!name.equals(other.name)) return false;
    if (packageName == null) {
      if (other.packageName != null) return false;
    } else if (!packageName.equals(other.packageName)) return false;
    if (simpleName == null) {
      if (other.simpleName != null) return false;
    } else if (!simpleName.equals(other.simpleName)) return false;
    if (typeParameters == null) {
      if (other.typeParameters != null) return false;
    } else if (!typeParameters.equals(other.typeParameters)) return false;
    return true;
  }

}
