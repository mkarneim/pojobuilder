package net.karneim.pojobuilder.processor.with.validator;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withValidator = BadValidator.class)
public class Pojo2 {
  public String name;
}
