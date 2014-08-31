package net.karneim.pojobuilder.processor.with.baseclass.andgenerationgap;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withBaseclass = BaseBuilderWithGenericBuildMethod.class, withGenerationGap = true)
public class Pojo2 {
  public String name;

}
