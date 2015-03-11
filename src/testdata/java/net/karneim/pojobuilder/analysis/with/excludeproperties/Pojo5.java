package net.karneim.pojobuilder.analysis.with.excludeproperties;

import java.util.List;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(excludeProperties = "*:*.List<*.String>")
public class Pojo5 {
  public String firstname;
  public String surname;
  public List<String> skills;

}
