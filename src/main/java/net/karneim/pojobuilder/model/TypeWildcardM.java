package net.karneim.pojobuilder.model;

/**
 * @author Adrodoc55
 */
public class TypeWildcardM extends TypeVariableM {
  private boolean lowerBound;

  public TypeWildcardM() {
    super("?");
  }

  @Override
  public TypeWildcardM whichExtends(TypeM... types) {
    if (lowerBound) {
      bounds.clear();
    }
    lowerBound = false;
    super.whichExtends(types);
    return this;
  }

  public TypeWildcardM whichIsASupertypeOf(TypeM type) {
    bounds.clear();
    lowerBound = true;
    super.whichExtends(type);
    return this;
  }

  @Override
  public String getBoundRelation() {
    return lowerBound ? "super" : "extends";
  }

  @Override
  public String getGenericType() {
    return getGenericTypeDefinition();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + (lowerBound ? 1231 : 1237);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    TypeWildcardM other = (TypeWildcardM) obj;
    if (lowerBound != other.lowerBound)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "TypeWildcardM [getGenericTypeDefinition()=" + getGenericTypeDefinition() + "]";
  }

}
