package samples.dsl;

import samples.Builder;

public class IntegerBuilder implements Builder<Integer> {
  private int nextNumber;

  public IntegerBuilder(int nextNumber) {
    this.nextNumber = nextNumber;
  }

  @Override
  public Integer build() {
    return nextNumber++;
  }

}
