package net.karneim.pojobuilder.processor.with.generics;

import java.io.File;
import java.util.List;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class Pojo<T> {
  public T someElement;
  public List<T> someList;
  public List<File> someFileList;
  public List<? extends T> someWildcardList;
  public Pojo<T> somePojo;
}
