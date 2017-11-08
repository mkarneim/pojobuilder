package net.karneim.pojobuilder.model;

public class PrimitiveTypeM extends TypeM {
  public static final TypeM VOID = new PrimitiveTypeM(void.class, Void.class);
  public static final TypeM DOUBLE = new PrimitiveTypeM(double.class, Double.class);
  public static final TypeM FLOAT = new PrimitiveTypeM(float.class, Float.class);
  public static final TypeM LONG = new PrimitiveTypeM(long.class, Long.class);
  public static final TypeM INT = new PrimitiveTypeM(int.class, Integer.class);
  public static final TypeM SHORT = new PrimitiveTypeM(short.class, Short.class);
  public static final TypeM BYTE = new PrimitiveTypeM(byte.class, Byte.class);
  public static final TypeM CHAR = new PrimitiveTypeM(char.class, Character.class);
  public static final TypeM BOOLEAN = new PrimitiveTypeM(boolean.class, Boolean.class);

  private final Class<?> type;
  private final TypeM boxType;

  public PrimitiveTypeM(Class<?> type, Class<?> boxType) {
    super(type.getName());
    this.type = type;
    this.boxType = new TypeM(boxType);
  }

  /**
   * @return the value of {@link #type}
   */
  public Class<?> getType() {
    return type;
  }

  /**
   * @return the value of {@link #boxType}
   */
  public TypeM getBoxType() {
    return boxType;
  }

  @Override
  public boolean isPrimitive() {
    return true;
  }
}
