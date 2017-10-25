package net.karneim.pojobuilder.processor.with.optionals;

import java.util.List;

import com.google.common.base.Optional;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class PojoWithGuavaOptionalBasicFactory {
  @GeneratePojoBuilder(withOptionalProperties = Optional.class)
  public static PojoWithGuavaOptionalBasicFactory create(int primitiveInt, Integer boxedInt, int[] array,
      List<Integer> list) {
    return new PojoWithGuavaOptionalBasicFactory(primitiveInt, boxedInt, array, list);
  }

  private int primitiveInt;
  private Integer boxedInt;
  private int[] array;
  private List<Integer> list;

  private PojoWithGuavaOptionalBasicFactory(int primitiveInt, Integer boxedInt, int[] array, List<Integer> list) {
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
