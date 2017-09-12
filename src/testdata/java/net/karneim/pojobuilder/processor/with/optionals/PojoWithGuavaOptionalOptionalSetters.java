package net.karneim.pojobuilder.processor.with.optionals;

import java.util.List;

import com.google.common.base.Optional;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withOptionalProperties = Optional.class)
public class PojoWithGuavaOptionalOptionalSetters {
  private Optional<Integer> boxedInt;
  private Optional<int[]> array;
  private Optional<List<Integer>> list;

  public Optional<Integer> getBoxedInt() {
    return boxedInt;
  }

  public void setBoxedInt(Optional<Integer> boxedInt) {
    this.boxedInt = boxedInt;
  }

  public Optional<int[]> getArray() {
    return array;
  }

  public void setArray(Optional<int[]> array) {
    this.array = array;
  }

  public Optional<List<Integer>> getList() {
    return list;
  }

  public void setList(Optional<List<Integer>> list) {
    this.list = list;
  }
}
