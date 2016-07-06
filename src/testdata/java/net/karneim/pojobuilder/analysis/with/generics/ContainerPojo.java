package net.karneim.pojobuilder.analysis.with.generics;

import java.io.Serializable;
import java.util.List;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class ContainerPojo<T extends ItemPojo & Serializable> {

  public T element;

  public List<? super T> list;

}
