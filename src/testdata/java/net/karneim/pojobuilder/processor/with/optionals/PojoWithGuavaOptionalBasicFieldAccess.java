package net.karneim.pojobuilder.processor.with.optionals;

import java.util.List;

import com.google.common.base.Optional;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withOptionalProperties = Optional.class)
public class PojoWithGuavaOptionalBasicFieldAccess {
  public int primitiveInt;
  public Integer boxedInt;
  public int[] array;
  public List<Integer> list;
}
