package net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withBaseclass = BaseBuilderWithRawBuildMethod.class, withGenerationGap = true)
public class Pojo3 {
  public String name;

}
