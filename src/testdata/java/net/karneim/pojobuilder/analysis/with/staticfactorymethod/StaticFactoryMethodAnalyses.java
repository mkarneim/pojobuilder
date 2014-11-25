package net.karneim.pojobuilder.analysis.with.staticfactorymethod;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public final class StaticFactoryMethodAnalyses {

  @GeneratePojoBuilder
  public static class WithoutSfm {
  }

  @GeneratePojoBuilder(withFactoryMethod = "*")
  public static class WithDecapitalisedSfm {
  }

  @GeneratePojoBuilder(withFactoryMethod = "$*")
  public static class WithArbitrarySfm {
  }

}
