package net.karneim.pojobuilder.processor.with.validator;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withValidator = PojoValidator.class)
public class Pojo {
  public String name;
}
