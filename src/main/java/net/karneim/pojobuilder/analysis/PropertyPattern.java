package net.karneim.pojobuilder.analysis;

import net.karneim.pojobuilder.model.PropertyM;

public class PropertyPattern {

  private final String nameRegex;
  private final String typeRegex;

  public PropertyPattern(String pattern) {
    String[] parts = pattern.split(":");
    checkIsJavaPropertyIdentifier(parts[0]);
    this.nameRegex = parts[0].replace(".", "\\.").replace("*", ".*");
    if (parts.length == 1) {
      this.typeRegex = null;
    } else if (parts.length == 2) {
      checkIsJavaTypeIdentifier(parts[1]);
      this.typeRegex = parts[1].replace(".", "\\.").replace("*", ".*").replace("[", "\\[").replace("]", "\\]");
    } else {
      throw new IllegalDirectiveException(String.format("Invalid pattern: \"%s\"", pattern));
    }
  }

  private void checkIsJavaTypeIdentifier(String pattern) {
    String text = pattern.replace("*", "");
    String[] parts = text.split("\\.");
    for (String part : parts) {
      for (char ch : part.toCharArray()) {
        if (ch == '<' || ch == '>' || ch == '[' || ch == ']' || Character.isJavaIdentifierPart(ch)) {
          continue;
        } else {
          throw new IllegalDirectiveException(String.format("%s is not part of a legal Java package identifier", text));
        }
      }
    }
  }

  private void checkIsJavaPropertyIdentifier(String pattern) throws IllegalDirectiveException {
    String text = pattern.replace("*", "");
    for (char ch : text.toCharArray()) {
      if (!Character.isJavaIdentifierPart(ch)) {
        throw new IllegalDirectiveException(String.format("%s is not part of a legal Java property identifier", text));
      }
    }
  }

  public boolean contains(PropertyM propertyM) {
    boolean matchesName = propertyM.getPropertyName().matches(nameRegex);
    if (matchesName) {
      String genericTypeDeclaration = propertyM.getPropertyType().getGenericType();
      boolean matchesType =
          typeRegex == null || genericTypeDeclaration.matches(typeRegex);
      return matchesType;
    }
    return false;
  }

}
