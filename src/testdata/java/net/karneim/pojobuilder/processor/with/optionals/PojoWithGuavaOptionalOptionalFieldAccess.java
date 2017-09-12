package net.karneim.pojobuilder.processor.with.optionals;

import java.util.List;

import com.google.common.base.Optional;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withOptionalProperties = Optional.class)
public class PojoWithGuavaOptionalOptionalFieldAccess {
  public Optional<Integer> boxedInt;
  public Optional<int[]> array;
  public Optional<List<Integer>> list;
}
