package net.karneim.pojobuilder.processor.with.optionals;

import java.util.List;

import com.google.common.base.Optional;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class PojoWithGuavaOptionalBasicConstructor {
  private int primitiveInt;
  private Integer boxedInt;
  private int[] array;
  private List<Integer> list;

  @GeneratePojoBuilder(withOptionalProperties = Optional.class)
  public PojoWithGuavaOptionalBasicConstructor(int primitiveInt, Integer boxedInt, int[] array,
      List<Integer> list) {
    this.primitiveInt = primitiveInt;
    this.boxedInt = boxedInt;
    this.array = array;
    this.list = list;
  }

  public int getPrimitiveInt() {
    return primitiveInt;
  }

  public Integer getBoxedInt() {
    return boxedInt;
  }

  public int[] getArray() {
    return array;
  }

  public List<Integer> getList() {
    return list;
  }
}
