package net.karneim.pojobuilder.processor.with.innerclass;

import java.util.List;

public class OuterPojo {
  public static class InnerPojo {
    private String name;
    public int number;
    protected List<String> strings;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public List<String> getStrings() {
      return strings;
    }

  }
}
