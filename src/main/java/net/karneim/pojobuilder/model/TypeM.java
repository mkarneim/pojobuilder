package net.karneim.pojobuilder.model;

public class TypeM {
  private final String packageName;
  private final String simpleName;
  private final String simpleNames;
  private final String name;
  private final TypeListM typeParameters = new TypeListM();

  protected TypeM(String name) {
    this("", name);
  }

  public TypeM(String packageName, String simpleNames) {
    if (packageName == null) {
      packageName = "";
    }
    this.packageName = packageName;
    this.simpleNames = simpleNames;
    this.simpleName = simpleNames.substring(simpleNames.lastIndexOf('.') + 1);
    if (packageName.isEmpty()) {
      this.name = simpleNames;
    } else {
      this.name = packageName + "." + simpleNames;
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

  public String getSimpleNames() {
    return simpleNames;
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

  /**
   * Returns a String representation of the complete generics definition of this type. This can be
   * used for generic parameter definitions of classes and methods.
   * <p>
   * Named generic types are represented with bounds. To get a minimal representation used for field
   * declarations see {@link #getGenericType()}
   * <p>
   * Example Output: {@code "Map<T extends CharSequence, E extends List<? super Number>>"}
   *
   * @return the complete generics definition
   */
  public String getGenericTypeDefinition() {
    if (!isGeneric()) {
      return name;
    }
    return String.format("%s<%s>", name, typeParameters.toParameterString());
  }

  /**
   * Returns a String representation of the minimal generics definition of this type. This can be
   * used for variable, parameter or return types such as field declarations.
   * <p>
   * Named generic types are represented without bounds, because they are defined in the class or
   * method signature. To get a representation of the full definition including bounds see
   * {@link #getGenericTypeDefinition()}
   * <p>
   * Example Output: {@code "Map<T, ? extends List<? super Number>>"}
   *
   * @return the generic type
   */
  public String getGenericType() {
    if (!isGeneric()) {
      return name;
    }
    return String.format("%s<%s>", name, typeParameters.toArgumentString());
  }

  public ImportTypesM addToImportTypes(ImportTypesM result) {
    result.add(this);
    typeParameters.addToImportTypes(result);
    return result;
  }

  public boolean isInPackage(String packageName) {
    return (packageName == null && this.packageName.isEmpty())
        || packageName.equals(this.packageName);
  }

  /**
   * This {@link #hashCode()} implementation doesn't use typeParameters to avoid a potential
   * StackOverflowError. The {@link #equals(Object)} implementation does use that field.
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    TypeM other = (TypeM) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (typeParameters == null) {
      if (other.typeParameters != null)
        return false;
    } else if (!typeParameters.equals(other.typeParameters))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "TypeM [getGenericTypeDefinition()=" + getGenericTypeDefinition() + "]";
  }
}
