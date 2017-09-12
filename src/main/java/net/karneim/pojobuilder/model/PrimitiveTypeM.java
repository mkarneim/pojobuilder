package net.karneim.pojobuilder.model;

import com.google.common.primitives.Primitives;

public class PrimitiveTypeM extends TypeM {
  public static final TypeM VOID = new PrimitiveTypeM(void.class);
  public static final TypeM DOUBLE = new PrimitiveTypeM(double.class);
  public static final TypeM FLOAT = new PrimitiveTypeM(float.class);
  public static final TypeM LONG = new PrimitiveTypeM(long.class);
  public static final TypeM INT = new PrimitiveTypeM(int.class);
  public static final TypeM SHORT = new PrimitiveTypeM(short.class);
  public static final TypeM BYTE = new PrimitiveTypeM(byte.class);
  public static final TypeM CHAR = new PrimitiveTypeM(char.class);
  public static final TypeM BOOLEAN = new PrimitiveTypeM(boolean.class);

  private final Class<?> type;
  private final TypeM boxType;

  public PrimitiveTypeM(Class<?> type) {
    super(type.getName());
    this.type = type;
    this.boxType = new TypeM(Primitives.wrap(type));
  }

  /**
   * Liefert den Wert von {@link #type}.
   *
   * @return den Wert von {@link #type}
   */
  public Class<?> getType() {
    return type;
  }

  /**
   * Liefert den Wert von {@link #boxType}.
   *
   * @return den Wert von {@link #boxType}
   */
  public TypeM getBoxType() {
    return boxType;
  }

  @Override
  public boolean isPrimitive() {
    return true;
  }
}
