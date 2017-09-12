package net.karneim.pojobuilder.processor.with.optionals;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withOptionalProperties = OtherOptional.class)
public class PojoWithOtherOptionalBasicFieldAccess {

  public int primitiveInt;
  public Integer boxedInt;
  public int[] array;

}
