package net.karneim.pojobuilder.processor.with.optionals;

import java.util.List;

import com.google.common.base.Optional;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.processor.with.builderinterface.Builder;

@GeneratePojoBuilder(withBuilderInterface = Builder.class, withBuilderProperties = true, withOptionalProperties = Optional.class)
public class PojoWithGuavaOptionalBasicFieldAccessWithBuilderProperties {
  public int primitiveInt;
  public Integer boxedInt;
  public int[] array;
  public List<Integer> list;
}
