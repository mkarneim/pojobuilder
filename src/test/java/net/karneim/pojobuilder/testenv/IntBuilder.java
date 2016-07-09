package net.karneim.pojobuilder.testenv;

import java.util.Random;

/**
 * @author Adrodoc55
 */
public final class IntBuilder implements Builder<Integer> {
  private final Random generator;
  private int lowerBound = 0;
  private int upperBound = Integer.MAX_VALUE - 1;

  public IntBuilder(Random generator) {
    this.generator = generator;
  }

  /**
   * @param lowerBound inclusive
   * @param upperBound inclusive
   * @return this
   */
  public IntBuilder between(int lowerBound, int upperBound) {
    this.lowerBound = lowerBound;
    this.upperBound = upperBound;
    return this;
  }

  @Override
  public Integer build() {
    return generator.nextInt(upperBound + 1 - lowerBound) + lowerBound;
  }

}
