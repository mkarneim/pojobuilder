package net.karneim.pojobuilder.model;

public class BuildMethodM {
  private boolean overrides;
  private String name;

  public BuildMethodM() {
    name = "build";
  }

  public boolean isOverrides() {
    return overrides;
  }
  
  public String getName() {
    return name;
  }

  public BuildMethodM setOverrides(boolean value) {
    this.overrides = value;
    return this;
  }

  @Override
  public String toString() {
    return "BuildMethodM [overrides=" + overrides + ", name=" + name + "]";
  }

  public BuildMethodM setName(String name) {
    this.name = name;
    return this;
  }

}
