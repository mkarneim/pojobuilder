package net.karneim.pojobuilder.processor.with.optionals;

import net.karneim.pojobuilder.GeneratePojoBuilder;

import static net.karneim.pojobuilder.GeneratePojoBuilder.OptionalSupport.Guava;

@GeneratePojoBuilder(withOptionals = Guava)
public class PojoWithGuavaOptional {

  public int primitiveInt;
  public Integer boxedInt;
  public int[] array;

}
