package net.karneim.pojobuilder.testenv;

public enum Extension {
  JAVA(".java"), JAVA_TXT(".java.txt");

  private String string;

  private Extension(String string) {
    this.string = string;
  }

  public String asString() {
    return string;
  }
}
