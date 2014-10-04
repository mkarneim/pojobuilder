package net.karneim.pojobuilder.processor.with.validator;

public class PojoValidator {
  public void validate(Pojo pojo) {
    if (pojo.name == null) {
      throw new IllegalArgumentException("Missing name!");
    }
  }
}
