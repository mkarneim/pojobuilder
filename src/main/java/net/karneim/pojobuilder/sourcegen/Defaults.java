package net.karneim.pojobuilder.sourcegen;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Defaults {
  private static final Map<Class<?>, String> DEFAULTS;
  static {
    Map<Class<?>, String> map = new HashMap<Class<?>, String>();
    map.put(boolean.class, "false");
    map.put(char.class, "'\\0'");
    map.put(byte.class, "(byte) 0");
    map.put(short.class, "(short) 0");
    map.put(int.class, "0");
    map.put(long.class, "0L");
    map.put(float.class, "0F");
    map.put(double.class, "0D");
    DEFAULTS = Collections.unmodifiableMap(map);
  }

  /**
   * Returns a String literal representation of the default value of {@code type} as defined by JLS --- {@code 0} for
   * numbers, {@code false} for {@code boolean} and {@code '\0'} for {@code char}. For non-primitive types and
   * {@code void}, {@code "null"} is returned.
   *
   * @param type the type
   * @return a String literal representation of the default value of {@code type} as defined by JLS
   */
  public static String defaultValueAsLiteral(Class<?> type) {
    return String.valueOf(DEFAULTS.get(type));
  }
}
