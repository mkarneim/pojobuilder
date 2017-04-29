package net.karneim.pojobuilder.processor.with.builderinterface;

import java.io.File;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class Pojo2 {
  public String name;
  private int age;
  private File file;
  
  @GeneratePojoBuilder(withBuilderInterface = Supplier.class)
  public Pojo2(File file) {
    super();
    this.file = file;
  }

  public int getAge() {
    return age;
  }
  
  public void setAge(int age) {
    this.age = age;
  }

  public File getFile() {
    return file;
  }

  public void setFile(File file) {
    this.file = file;
  }

}
