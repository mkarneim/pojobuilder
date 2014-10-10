package samples.dsl;

import samples.Builder;

public class LongBuilder implements Builder<Long> {

  private long nextNumber;

  public LongBuilder(long nextNumber) {
    super();
    this.nextNumber = nextNumber;
  }

  @Override
  public Long build() {
    return nextNumber++;
  }

}
