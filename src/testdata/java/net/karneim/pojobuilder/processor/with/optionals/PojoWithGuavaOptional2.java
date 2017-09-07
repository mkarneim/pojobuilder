package net.karneim.pojobuilder.processor.with.optionals;

import net.karneim.pojobuilder.GeneratePojoBuilder;

import com.google.common.base.Optional;

@GeneratePojoBuilder(withOptionalProperties = Optional.class)
public class PojoWithGuavaOptional2 {

  public Optional<Integer> boxedInt;
  public Optional<int[]> array;

}
