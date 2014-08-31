package net.karneim.pojobuilder.model;

public class PrimitiveTypeM extends TypeM {

  public static final TypeM VOID = new PrimitiveTypeM("void", new TypeM(Void.class));
  public static final TypeM DOUBLE = new PrimitiveTypeM("double", new TypeM(Double.class));
  public static final TypeM FLOAT = new PrimitiveTypeM("float", new TypeM(Float.class));
  public static final TypeM LONG = new PrimitiveTypeM("long", new TypeM(Long.class));
  public static final TypeM INT = new PrimitiveTypeM("int", new TypeM(Integer.class));
  public static final TypeM SHORT = new PrimitiveTypeM("short", new TypeM(Short.class));
  public static final TypeM BYTE = new PrimitiveTypeM("byte", new TypeM(Byte.class));
  public static final TypeM CHAR = new PrimitiveTypeM("char", new TypeM(Character.class));
  public static final TypeM BOOLEAN = new PrimitiveTypeM("boolean", new TypeM(Boolean.class));

  private final TypeM boxClass;

  public PrimitiveTypeM(String name, TypeM boxClass) {
    super(name);
    this.boxClass = boxClass;
  }

  public TypeM getBoxClass() {
    return boxClass;
  }

  @Override
  public boolean isPrimitive() {
    return true;
  }

}
