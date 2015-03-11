package net.karneim.pojobuilder.analysis.with.includeproperties;

import java.util.List;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(includeProperties = "*:*.List<*.String>")
public class Pojo5 {
  public String firstname;
  public String surname;
  public List<String> skills;

}
