package net.karneim.pojobuilder.processor.with.optionals;

import java.util.List;

import com.google.common.base.Optional;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withOptionalProperties = Optional.class)
public class PojoWithGuavaOptionalBasicSetters {
  private int primitiveInt;
  private Integer boxedInt;
  private int[] array;
  private List<Integer> list;

  public int getPrimitiveInt() {
    return primitiveInt;
  }

  public void setPrimitiveInt(int primitiveInt) {
    this.primitiveInt = primitiveInt;
  }

  public Integer getBoxedInt() {
    return boxedInt;
  }

  public void setBoxedInt(Integer boxedInt) {
    this.boxedInt = boxedInt;
  }

  public int[] getArray() {
    return array;
  }

  public void setArray(int[] array) {
    this.array = array;
  }

  public List<Integer> getList() {
    return list;
  }

  public void setList(List<Integer> list) {
    this.list = list;
  }
}
