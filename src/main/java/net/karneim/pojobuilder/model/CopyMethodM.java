package net.karneim.pojobuilder.model;

public class CopyMethodM {
  private final String name;

  public CopyMethodM(String name) {
    super();
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "CopyMethodM [name=" + name + "]";
  }

}
