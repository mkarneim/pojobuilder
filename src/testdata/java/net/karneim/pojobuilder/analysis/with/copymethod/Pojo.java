package net.karneim.pojobuilder.analysis.with.copymethod;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withCopyMethod = true)
class Pojo {
  public String name;
  private int size;

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

}
