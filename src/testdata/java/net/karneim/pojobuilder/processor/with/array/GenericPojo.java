package net.karneim.pojobuilder.processor.with.array;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class GenericPojo<T> {
  public T[] someArray;
  public T[][] someMatrix;
  public GenericPojo<T>[] somePojos;
}
