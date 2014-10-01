package net.karneim.pojobuilder.sourcegen;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.model.TypeM;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import static net.karneim.pojobuilder.GeneratePojoBuilder.OptionalSupport;

public class OptionalSupportHelper {

  public static final Map<OptionalSupport,TypeM> SUPPLIER_TYPES;

  static {
    EnumMap<OptionalSupport,TypeM> map = new EnumMap<OptionalSupport,TypeM>(OptionalSupport.class);
    map.put(OptionalSupport.Guava, new TypeM("com.google.common.base","Optional"));
    SUPPLIER_TYPES = Collections.unmodifiableMap(map);
    // TODO TypeM is intrinsically mutable which we should change before a mistake is made through this map.
  }

  private OptionalSupportHelper() {
    // NO OP
  }

}
