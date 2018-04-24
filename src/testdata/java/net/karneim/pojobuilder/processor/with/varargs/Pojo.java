package net.karneim.pojobuilder.processor.with.varargs;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class Pojo {

  private String[] someStrings;
  private List<File> someFiles;
  private final List<BigDecimal> someNumbers;

  @GeneratePojoBuilder(withCopyMethod=true)
  public Pojo(BigDecimal... someNumbers) {
    this.someNumbers = Arrays.asList(someNumbers);
  }

  public String[] getSomeStrings() {
    return someStrings;
  }

  public void setSomeStrings(String... someStrings) {
    this.someStrings = someStrings;
  }

  public List<File> getSomeFiles() {
    return someFiles;
  }

  public void setSomeFiles(File... someFiles) {
    this.someFiles = Arrays.asList(someFiles);
  }

  public List<BigDecimal> getSomeNumbers() {
    return someNumbers;
  }

}
