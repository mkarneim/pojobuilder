package net.karneim.pojobuilder.processor.with.factorymethodannotation;

public class Pair<L, R> {
  private final L left;
  private final R right;

  public Pair(L left, R right) {
    super();
    this.left = left;
    this.right = right;
  }

  public L getLeft() {
    return left;
  }

  public R getRight() {
    return right;
  }

}
