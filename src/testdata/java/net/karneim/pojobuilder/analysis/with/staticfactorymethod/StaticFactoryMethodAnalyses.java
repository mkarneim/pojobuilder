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

  @GeneratePojoBuilder(withFactoryMethod = "*", withGenerationGap = true)
  public static class WithGenerationGap {
  }

  @GeneratePojoBuilder(withFactoryMethod = "*", withPublicConstructor = false)
  public static class WithoutPublicConstructor {
  }
}
