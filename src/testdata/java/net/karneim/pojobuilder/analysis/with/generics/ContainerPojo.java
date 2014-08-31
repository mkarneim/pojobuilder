package net.karneim.pojobuilder.analysis.with.generics;

import java.io.Serializable;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class ContainerPojo<T extends ItemPojo & Serializable> {

  public T element;


}
