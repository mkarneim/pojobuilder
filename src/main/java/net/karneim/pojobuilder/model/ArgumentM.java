package net.karneim.pojobuilder.model;

public class ArgumentM {
  private final PropertyM property;
  private final int pos;

  public ArgumentM(PropertyM property, int pos) {
    this.property = property;
    this.pos = pos;
  }

  public PropertyM getProperty() {
    return property;
  }

  public int getPos() {
    return pos;
  }

  @Override
  public String toString() {
    return "ArgumentM [property=" + property + ", pos=" + pos + "]";
  }
}
