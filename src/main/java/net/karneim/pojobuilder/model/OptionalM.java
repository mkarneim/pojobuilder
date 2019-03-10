package net.karneim.pojobuilder.model;

import static java.util.Objects.requireNonNull;

public class OptionalM {
  public static final String IS_PRESENT_METHOD_NAME = "isPresent";
  public static final String GET_METHOD_NAME = "get";
  public static final String OF_METHOD_NAME = "of";
  private final TypeM type;
  private final String absentMethodName;

  public OptionalM(TypeM type, String absentMethodName) {
    this.type = requireNonNull(type, "type == null!");
    this.absentMethodName = requireNonNull(absentMethodName, "absentMethodName == null!");
  }

  /**
   * @return the value of {@link #type}
   */
  public TypeM getType() {
    return type;
  }

  /**
   * @return the value of {@link #absentMethodName}
   */
  public String getAbsentMethodName() {
    return absentMethodName;
  }

  public String absent(ImportTypesM importTypes) {
    return importTypes.getCompressedTypeName(type) + "." + absentMethodName + "()";

  }

  public String of(String string, ImportTypesM importTypes) {
    return importTypes.getCompressedTypeName(type) + "." + OF_METHOD_NAME + "(" + string + ")";
  }
}
