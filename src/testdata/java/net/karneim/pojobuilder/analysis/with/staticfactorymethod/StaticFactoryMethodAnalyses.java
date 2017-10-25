package net.karneim.pojobuilder.analysis.with.staticfactorymethod;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.Visibility;

public final class StaticFactoryMethodAnalyses {

  @GeneratePojoBuilder
  public static class WithoutSfm {}

  @GeneratePojoBuilder(withFactoryMethod = "*")
  public static class WithDecapitalisedSfm {}

  @GeneratePojoBuilder(withFactoryMethod = "$*")
  public static class WithArbitrarySfm {}

  @GeneratePojoBuilder(withFactoryMethod = "*", withGenerationGap = true)
  public static class WithGenerationGap {}

  @GeneratePojoBuilder(withFactoryMethod = "*", withConstructor = Visibility.PRIVATE)
  public static class WithPrivateConstructor {}

  @GeneratePojoBuilder(withFactoryMethod = "*", withGenerationGap = true, withConstructor = Visibility.PRIVATE)
  public static class WithPrivateConstructorAndGenerationGap {}
}
