package net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withBaseclass = SimpleBaseBuilder.class, withGenerationGap = true)
public class Pojo1 {
  public String name;


}
