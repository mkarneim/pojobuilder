package net.karneim.pojobuilder.processor.with.baseclass;

public class BaseBuilderWithCloneMethod {

  /**
   * Returns a clone of this object.
   *
   * @return the clone
   */
  @Override
  public Object clone() {
    try {
      BaseBuilderWithCloneMethod result = (BaseBuilderWithCloneMethod) super.clone();
      return result;
    } catch (CloneNotSupportedException e) {
      throw new InternalError(e.getMessage());
    }
  }

}
