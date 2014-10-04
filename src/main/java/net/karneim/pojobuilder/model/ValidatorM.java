package net.karneim.pojobuilder.model;

public class ValidatorM {
  private final TypeM type;
  private final String methodName;

  public ValidatorM(TypeM type, String methodName) {
    this.type = type;
    this.methodName = methodName;
  }

  public TypeM getType() {
    return type;
  }

  public String getMethodName() {
    return methodName;
  }

  public String getFieldName() {
    return "validator";
  }

  @Override
  public String toString() {
    return "ValidatorM [type=" + type + ", methodName=" + methodName + "]";
  }

}
