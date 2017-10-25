package net.karneim.pojobuilder.analysis.with.constructor;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.Visibility;

public final class ConstructorAnalyses {

  @GeneratePojoBuilder(withConstructor = Visibility.PUBLIC)
  public static class WithPublicConstructor {}

  @GeneratePojoBuilder(withConstructor = Visibility.PROTECTED)
  public static class WithProtectedConstructor {}

  @GeneratePojoBuilder(withConstructor = Visibility.PRIVATE, withFactoryMethod = "*")
  public static class WithPrivateConstructor {}

  @GeneratePojoBuilder(withConstructor = Visibility.PACKAGE)
  public static class WithPackagePrivateConstructor {}

}
