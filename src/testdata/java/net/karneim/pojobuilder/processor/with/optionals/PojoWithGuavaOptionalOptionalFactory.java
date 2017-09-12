package net.karneim.pojobuilder.processor.with.optionals;

import java.util.List;

import com.google.common.base.Optional;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class PojoWithGuavaOptionalOptionalFactory {
  @GeneratePojoBuilder(withOptionalProperties = Optional.class)
  public static PojoWithGuavaOptionalOptionalFactory create(Optional<Integer> boxedInt, Optional<int[]> array,
      Optional<List<Integer>> list) {
    return new PojoWithGuavaOptionalOptionalFactory(boxedInt, array, list);
  }

  private Optional<Integer> boxedInt;
  private Optional<int[]> array;
  private Optional<List<Integer>> list;

  private PojoWithGuavaOptionalOptionalFactory(Optional<Integer> boxedInt, Optional<int[]> array,
      Optional<List<Integer>> list) {
    this.boxedInt = boxedInt;
    this.array = array;
    this.list = list;
  }

  public Optional<Integer> getBoxedInt() {
    return boxedInt;
  }

  public Optional<int[]> getArray() {
    return array;
  }

  public Optional<List<Integer>> getList() {
    return list;
  }
}
