package net.karneim.pojobuilder.processor.with.settername;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withSetterNamePattern = "set*")
public class Pojo {
  public String name;
  public int number;
}
