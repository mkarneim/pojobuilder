package net.karneim.pojobuilder.model;

public interface WriteAccess {

  public enum Type {
    CONSTRUCTOR, FACTORY, SETTER, FIELD
  }

  Type getType();

  boolean isVarArgs();

}
