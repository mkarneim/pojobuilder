package net.karneim.pojobuilder.processor.with.array;

import java.io.File;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class Pojo {
  public File[] someArray;
  public String[][] someMatrix;
  public Pojo[] pojoArray;
}
