package net.karneim.pojobuilder.processor.with.optionals;

import net.karneim.pojobuilder.GeneratePojoBuilder;

import com.google.common.base.Optional;

@GeneratePojoBuilder(withOptionalProperties = Optional.class)
public class PojoWithGuavaOptionalBasicFieldAccess {

  public int primitiveInt;
  public Integer boxedInt;
  public int[] array;

}
