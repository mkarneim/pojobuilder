package net.karneim.pojobuilder.processor.with.factorymethodannotation;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class PairFactory {

  @GeneratePojoBuilder
  public static <L, R> Pair<L, R> createPair(L left, R right) {
    return new Pair<L, R>(left, right);
  }
  
  @GeneratePojoBuilder(withName="StringPairBuilder")
  public static  Pair<String, String> createStringPair(String left, String right) {
    return new Pair<String, String>(left, right);
  }
  
  @GeneratePojoBuilder(withName="TPairBuilder")
  public static  <T> Pair<T, T> createStringPair(T left, T right) {
    return new Pair<T, T>(left, right);
  }
}
