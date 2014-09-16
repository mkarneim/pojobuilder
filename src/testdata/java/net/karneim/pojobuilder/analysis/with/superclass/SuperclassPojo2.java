package net.karneim.pojobuilder.analysis.with.superclass;

public class SuperclassPojo2 {
  @SuppressWarnings("unused")
  private float hiddenMember = 1.5F;
  protected float protectedMember = 2.0f;
  private String name;
  
  protected String getName() {
    return name;
  }
  protected void setName(String name) {
    this.name = name;
  }
  
}
