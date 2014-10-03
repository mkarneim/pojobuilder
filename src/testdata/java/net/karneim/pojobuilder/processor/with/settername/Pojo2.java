package net.karneim.pojobuilder.processor.with.settername;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withSetterNamePattern = "*")
public class Pojo2 {
  public String name;
  public int number;
}
