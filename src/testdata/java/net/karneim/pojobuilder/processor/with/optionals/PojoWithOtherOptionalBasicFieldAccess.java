package net.karneim.pojobuilder.processor.with.optionals;

import java.util.List;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withOptionalProperties = OtherOptional.class)
public class PojoWithOtherOptionalBasicFieldAccess {
  public int primitiveInt;
  public Integer boxedInt;
  public int[] array;
  public List<Integer> list;
}
