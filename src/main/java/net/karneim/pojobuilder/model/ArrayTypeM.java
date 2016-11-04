package net.karneim.pojobuilder.model;

public class ArrayTypeM extends TypeM {

  private final TypeM componentType;

  public ArrayTypeM(TypeM componentType) {
    super(getName(componentType));
    this.componentType = componentType;
  }

  private static String getName(TypeM componentType) {
    return componentType.getName() + "[]";
  }

  @Override
  public boolean isArrayType() {
    return true;
  }

  @Override
  public String getGenericTypeDefinition() {
    return componentType.getGenericTypeDefinition() + "[]";
  }

  @Override
  public String getGenericType() {
    return componentType.getGenericType() + "[]";
  }

  public String getGenericTypeDeclarationAsVarArgs() {
    return componentType.getGenericType() + "...";
  }

  @Override
  public ImportTypesM addToImportTypes(ImportTypesM result) {
    componentType.addToImportTypes(result);
    return result;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((componentType == null) ? 0 : componentType.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!super.equals(obj)) return false;
    if (getClass() != obj.getClass()) return false;
    ArrayTypeM other = (ArrayTypeM) obj;
    if (componentType == null) {
      if (other.componentType != null) return false;
    } else if (!componentType.equals(other.componentType)) return false;
    return true;
  }

  @Override
  public String toString() {
    return "ArrayTypeM [componentType=" + componentType + ", getGenericTypeDefinition()=" + getGenericTypeDefinition() + "]";
  }

}
